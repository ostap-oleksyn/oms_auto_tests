package com.softserveinc.edu.ita.tests.administration_page;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.domains.AdministrationsTableRow;
import com.softserveinc.edu.ita.enums.Regions;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.enums.administration_page.SearchConditions;
import com.softserveinc.edu.ita.enums.administration_page.SearchFilters;
import com.softserveinc.edu.ita.pageobjects.AdministrationPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.softserveinc.edu.ita.enums.administration_page.SearchFilters.*;

/**
 * Class to test searching in users table on administration page.
 */
public class SearchTest extends TestRunner {
    private List<AdministrationsTableRow> usersListFromView;
    private List<User> usersListFromDB;
    private List<User> filteredListFromDB;

    @Test(dataProvider = "getSearchTerms", dataProviderClass = DataProviders.class)
    public void testSearch(final String searchTerm) throws DAOException {
        final HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        final UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        for (final SearchFilters filter : SearchFilters.values()) {
            for (final SearchConditions condition : SearchConditions.values()) {
                // skip ALL COLUMNS filter, because we have testAllColumns separately
                if (filter != ALL_COLUMNS) {
                    administrationPage.setFilters(filter)
                            .setCondition(condition)
                            .fillSearchField(searchTerm)
                            .clickSearchButton();

                    usersListFromView = administrationPage.getTableFromView();
                    administrationPage.clearSearchField();

                    usersListFromDB = DBUtility.getAllUsers();
                    filteredListFromDB = getFilteredList(usersListFromDB, filter, condition, searchTerm);

                    filteredListFromDB.sort(Comparator.comparing(User::getLogin));
                    usersListFromView.sort(Comparator.comparing(AdministrationsTableRow::getLogin));

                    loggingSoftAssert.assertTrue(areListsEqual(usersListFromView, filteredListFromDB), filter + " " + condition + " " + searchTerm);
                    loggingSoftAssert.assertAll();
                }
            }
        }
        administrationPage.clickLogOutButton();
    }

    @Test(dataProvider = "getSearchTerms", dataProviderClass = DataProviders.class)
    public void testAllColumns(final String searchTerm) throws DAOException {
        final SearchFilters filter = ALL_COLUMNS;
        final HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        final UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        for (final SearchConditions condition : SearchConditions.values()) {
            administrationPage.setFilters(filter)
                    .setCondition(condition)
                    .fillSearchField(searchTerm)
                    .clickSearchButton();

            usersListFromView = administrationPage.getTableFromView();
            administrationPage.clearSearchField();


            usersListFromDB = DBUtility.getFilteredUsers(condition, searchTerm);

            final Comparator<User> userComparator = Comparator.comparing(User::getLogin);
            final Comparator<AdministrationsTableRow> userFromViewComparator = Comparator.comparing(AdministrationsTableRow::getLogin);

            usersListFromDB.sort(userComparator);
            usersListFromView.sort(userFromViewComparator);

            loggingSoftAssert.assertTrue(areListsEqual(usersListFromView, usersListFromDB), filter + " " + condition + " " + searchTerm);
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
    private boolean areListsEqual(final List<AdministrationsTableRow> userlist1, final List<User> userList2) {
        if (userlist1.isEmpty() && userList2.isEmpty()) {
            return true;
        }
        for (final AdministrationsTableRow user1 : userlist1) {
            for (final User user2 : userList2) {
                if (user1.getFirstName().equalsIgnoreCase(user2.getFirstName()) &&
                        user1.getLastName().equalsIgnoreCase(user2.getLastName()) &&
                        user1.getLogin().equalsIgnoreCase(user2.getLogin()) &&
                        user1.getRole().equalsIgnoreCase(Roles.getRoleName(user2.getRoleReference())) &&
                        user1.getRegion().equalsIgnoreCase(Regions.getRegionName(user2.getRegionReference()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Searches users from users which
     * were found from data base via parameters
     *
     * @param usersList
     * @param filter
     * @param condition
     * @param searchTerm
     * @return
     */
    private List<User> getFilteredList(final List<User> usersList, final SearchFilters filter, final SearchConditions condition, final String searchTerm) {
        final Map<SearchFilters, ISearchFilters> searchConditionMap = new HashMap<>();
        searchConditionMap.put(FIRST_NAME, User::getFirstName);
        searchConditionMap.put(LAST_NAME, User::getLastName);
        searchConditionMap.put(LOGIN_NAME, User::getLogin);
        searchConditionMap.put(ROLE, User::getRoleName);

        switch (condition) {

            case EQUALS:
                return usersList.stream()
                        .filter(user -> searchConditionMap.get(filter)
                                .callMethod(user).equals(searchTerm))
                        .collect(Collectors.toList());
            case NOT_EQUALS_TO:
                return usersList.stream()
                        .filter(user -> !searchConditionMap.get(filter)
                                .callMethod(user).equals(searchTerm))
                        .collect(Collectors.toList());
            case CONTAINS:
                return usersList.stream()
                        .filter(user -> searchConditionMap.get(filter)
                                .callMethod(user).contains(searchTerm))
                        .collect(Collectors.toList());
            case DOES_NOT_CONTAINS:
                return usersList.stream()
                        .filter(user -> !searchConditionMap.get(filter)
                                .callMethod(user).contains(searchTerm))
                        .collect(Collectors.toList());
            case STARTS_WITH:
                return usersList.stream()
                        .filter(user -> searchConditionMap.get(filter)
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
