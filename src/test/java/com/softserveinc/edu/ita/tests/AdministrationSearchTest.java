package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.domains.UserFromView;
import com.softserveinc.edu.ita.enums.AdministrationTabConditions;
import com.softserveinc.edu.ita.enums.AdministrationTabFilters;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.softserveinc.edu.ita.enums.AdministrationTabFilters.*;


public class AdministrationSearchTest extends TestRunner {
    List<UserFromView> usersListFromView;
    List<User> usersListFromDB;
    private String searchTerm;

    @Test(dataProvider = "getSearchFilters", dataProviderClass = DataProviders.class)
    public void testEquals(AdministrationTabFilters filters) throws DAOException {
        HomePage homePage = new HomePage(driver);
        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        searchTerm = "ivanka";

        for (AdministrationTabConditions conditions : AdministrationTabConditions.values()) {
            administrationPage.setFilters(filters.getFilterName())
                    .setConditions(conditions)
                    .fillSearchField(searchTerm)
                    .clickSearchButton();

            usersListFromView = administrationPage.getTableFromView();
            administrationPage.clearSearchField();

            usersListFromDB = DBUtility.getUserDao().getAllUsersFromDB();

            List<User> usersList = searchUsersInList(usersListFromDB, filters, conditions, searchTerm);

            loggingAssert.assertEquals(usersListFromView.size(), usersList.size(), filters + " " + conditions + " " + searchTerm);
        }
        administrationPage.clickLogOutButton();

    }

    /**
     * searches users from users which were found from data base via parameters
     * @param usersList
     * @param filters
     * @param conditions
     * @param searchTerm
     * @return
     */
    private List<User> searchUsersInList(List<User> usersList, AdministrationTabFilters filters, AdministrationTabConditions conditions, String searchTerm){
        Map<AdministrationTabFilters, SearchFilters> searchConditionMap = new HashMap<>();
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

    private interface SearchFilters {
        String callMethod(User user);
    }

}



