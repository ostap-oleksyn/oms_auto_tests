package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.domains.UserFromView;
import com.softserveinc.edu.ita.enums.SearchConditions;
import com.softserveinc.edu.ita.enums.SearchFilters;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.UserComparator;
import com.softserveinc.edu.ita.utils.UserFromViewComparator;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.softserveinc.edu.ita.enums.SearchFilters.*;


public class AdministrationSearchTest extends TestRunner {
    List<UserFromView> usersListFromView;
    List<User> usersListFromDB;
    List<User> filteredListFromDB;
    private String searchTerm;


    @Test(dataProvider = "getSearchFilters", dataProviderClass = DataProviders.class)
    public void testSearch(SearchFilters filters) throws DAOException {
        HomePage homePage = new HomePage(driver);
        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        //TODO move out to dataprovider
       searchTerm = "ivanka";
        for (SearchConditions conditions : SearchConditions.values()) {
            administrationPage.setFilters(filters.getFilterName())
                    .setCondition(conditions)
                    .fillSearchField(searchTerm)
                    .clickSearchButton();

            usersListFromView = administrationPage.getTableFromView();
            administrationPage.clearSearchField();

            usersListFromDB = DBUtility.getUserDao().getAllUsersFromDB();
            filteredListFromDB = getFilteredList(usersListFromDB, filters, conditions, searchTerm);

            filteredListFromDB.sort(new UserComparator());
            usersListFromView.sort(new UserFromViewComparator());

            loggingSoftAssert.assertTrue(equalsList(usersListFromView, filteredListFromDB), filters + " " + conditions + " " + searchTerm);
            loggingSoftAssert.assertAll();
        }
        administrationPage.clickLogOutButton();

    }

    @Test(dataProvider = "getSearchCondition", dataProviderClass = DataProviders.class)
    public void testAllColumns(SearchConditions conditions) throws DAOException {
        HomePage homePage = new HomePage(driver);
        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        //TODO move out to dataprovider
        searchTerm = "ivanka";

            administrationPage.setFilters(ALL_COLUMNS.getFilterName())
                    .setCondition(conditions)
                    .fillSearchField(searchTerm)
                    .clickSearchButton();

            usersListFromView = administrationPage.getTableFromView();
            administrationPage.clearSearchField();

        administrationPage.clickLogOutButton();
        usersListFromDB = DBUtility.getUserDao().getFilteredUsersFromDB(conditions, searchTerm);

        usersListFromDB.sort(new UserComparator());
        usersListFromView.sort(new UserFromViewComparator());

        loggingSoftAssert.assertTrue(equalsList(usersListFromView, usersListFromDB), ALL_COLUMNS + " " + conditions + " " + searchTerm);
        loggingSoftAssert.assertAll();


    }


    /**
     * compares two list of users
     *
     * @param userlist1
     * @param userList2
     * @return
     */
    private boolean equalsList(List<UserFromView> userlist1, List<User> userList2) {
        if (userlist1.size() == 0 && userList2.size() == 0) {
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



