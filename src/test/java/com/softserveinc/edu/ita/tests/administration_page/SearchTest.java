package com.softserveinc.edu.ita.tests.administration_page;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.domains.UserFromView;
import com.softserveinc.edu.ita.enums.SearchConditions;
import com.softserveinc.edu.ita.enums.SearchFilters;
import com.softserveinc.edu.ita.pageobjects.AdministrationPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.softserveinc.edu.ita.enums.SearchFilters.*;

public class SearchTest extends TestRunner {
    List<UserFromView> usersListFromView;
    List<User> usersListFromDB;
    List<User> filteredListFromDB;

    @Test(dataProvider = "getSearchTerms", dataProviderClass = DataProviders.class)
    public void testSearch(String searchTerm) throws DAOException {
        HomePage homePage = new HomePage(driver);
        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        for (SearchFilters filters : SearchFilters.values()) {
            for (SearchConditions conditions : SearchConditions.values()) {
            // skip ALL COLUMNS filter, because we have testAllColumns separately
                if (filters == ALL_COLUMNS) {
                    continue;
                } else {
                    administrationPage.setFilters(filters.getFilterName())
                            .setCondition(conditions)
                            .fillSearchField(searchTerm)
                            .clickSearchButton();

                    usersListFromView = administrationPage.getTableFromView();
                    administrationPage.clearSearchField();

                    usersListFromDB = DBUtility.getUserDao().getAllUsers();
                    filteredListFromDB = getFilteredList(usersListFromDB, filters, conditions, searchTerm);

                    Comparator<User> userComparator = Comparator.comparing(User::getLogin);
                    Comparator<UserFromView> userFromViewComparator = Comparator.comparing(UserFromView::getLogin);

                    filteredListFromDB.sort(userComparator);
                    usersListFromView.sort(userFromViewComparator);

                    loggingSoftAssert.assertTrue(areListsEqual(usersListFromView, filteredListFromDB), filters + " " + conditions + " " + searchTerm);
                    loggingSoftAssert.assertAll();
                }
            }
        }
        administrationPage.clickLogOutButton();
    }

    @Test(dataProvider = "getSearchTerms", dataProviderClass = DataProviders.class)
    public void testAllColumns(String searchTerm) throws DAOException {
        final String filter = ALL_COLUMNS.getFilterName();
        HomePage homePage = new HomePage(driver);
        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        for (SearchConditions conditions : SearchConditions.values()) {
            administrationPage.setFilters(filter)
                    .setCondition(conditions)
                    .fillSearchField(searchTerm)
                    .clickSearchButton();

            usersListFromView = administrationPage.getTableFromView();
            administrationPage.clearSearchField();

            usersListFromDB = DBUtility.getUserDao().getFilteredUsers(conditions, searchTerm);

            Comparator<User> userComparator = Comparator.comparing(User::getLogin);
            Comparator<UserFromView> userFromViewComparator = Comparator.comparing(UserFromView::getLogin);

            usersListFromDB.sort(userComparator);
            usersListFromView.sort(userFromViewComparator);

            loggingSoftAssert.assertTrue(areListsEqual(usersListFromView, usersListFromDB), filter + " " + conditions + " " + searchTerm);
            loggingSoftAssert.assertAll();
        }
        administrationPage.clickLogOutButton();
    }


    /**
     * compares two list of users
     *
     * @param userlist1
     * @param userList2
     * @return
     */
    private boolean areListsEqual(List<UserFromView> userlist1, List<User> userList2) {
        if (userlist1.isEmpty() && userList2.isEmpty()) {
            return true;
        }
        for (UserFromView user1 : userlist1) {
            for (User user2 : userList2) {
                if (user1.getFirstName().equalsIgnoreCase(user2.getFirstName()) &&
                        user1.getLastName().equalsIgnoreCase(user2.getLastName()) &&
                        user1.getLogin().equalsIgnoreCase(user2.getLogin()) &&
                        user1.getRole().equalsIgnoreCase(user2.getRoleName()) &&
                        user1.getRegion().equalsIgnoreCase(user2.getRegionName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * searches users from users which were found from data base via parameters
     *
     * @param usersList
     * @param filters
     * @param conditions
     * @param searchTerm
     * @return
     */
    private List<User> getFilteredList(List<User> usersList, SearchFilters filters, SearchConditions conditions, String searchTerm) {
        Map<SearchFilters, ISearchFilters> searchConditionMap = new HashMap<>();
        searchConditionMap.put(FIRST_NAME, User::getFirstName);
        searchConditionMap.put(LAST_NAME, User::getLastName);
        searchConditionMap.put(LOGIN_NAME, User::getLogin);
        searchConditionMap.put(ROLE, User::getRoleName);

        switch (conditions) {

            case EQUALS:
                return usersList.stream()
                        .filter(user -> searchConditionMap.get(filters)
                                .callMethod(user).equals(searchTerm))
                        .collect(Collectors.toList());
            case NOT_EQUALS_TO:
                return usersList.stream()
                        .filter(user -> !searchConditionMap.get(filters)
                                .callMethod(user).equals(searchTerm))
                        .collect(Collectors.toList());
            case CONTAINS:
                return usersList.stream()
                        .filter(user -> searchConditionMap.get(filters)
                                .callMethod(user).contains(searchTerm))
                        .collect(Collectors.toList());
            case DOES_NOT_CONTAINS:
                return usersList.stream()
                        .filter(user -> !searchConditionMap.get(filters)
                                .callMethod(user).contains(searchTerm))
                        .collect(Collectors.toList());
            case STARTS_WITH:
                return usersList.stream()
                        .filter(user -> searchConditionMap.get(filters)
                                .callMethod(user).startsWith(searchTerm))
                        .collect(Collectors.toList());
            default:
                return usersList;
        }
    }

    private interface ISearchFilters {
        String callMethod(User user);
    }
}
