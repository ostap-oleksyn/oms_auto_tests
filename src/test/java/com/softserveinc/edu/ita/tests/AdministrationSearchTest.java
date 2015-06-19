package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
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

import static com.softserveinc.edu.ita.enums.AdministrationTabFilters.*;
import static com.softserveinc.edu.ita.enums.AdministrationTabFilters.ALL_COLUMNS;


public class AdministrationSearchTest extends TestRunner {
    List<UserFromView> usersListFromView;
    List<User> usersListFromDB;
    private String searchTerm;

    @Test()
    public void testEquals() throws DAOException {
        HomePage homePage = new HomePage(driver);
        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        administrationPage.setFilters(ALL_COLUMNS.getFilterName())
                .setConditions(AdministrationTabConditions.EQUALS)
                .fillSearchField("")
                .clickSearchButton();

        usersListFromView = administrationPage.getTableFromView();
        administrationPage.clearSearchField();

        usersListFromDB = DBUtility.getUserDao().getAllUsersFromDB();
        for (User user : usersListFromDB) {
            System.out.println(user);
        }
        loggingAssert.assertEquals(usersListFromView.size(), usersListFromDB.size());

        administrationPage.clickLogOutButton();
    }


    private interface SearchFilters {
        String callMethod(User user);
    }

    private List<User> getFilter(List<User> userList, AdministrationTabConditions conditions,String searchTerm ) {
        final Map<AdministrationTabFilters, SearchFilters> searchConditionMap = new HashMap<>();
        searchConditionMap.put(FIRST_NAME, User::getFirstName);
        searchConditionMap.put(LAST_NAME, User::getLastName);
        searchConditionMap.put(LOGIN_NAME, User::getLogin);
        searchConditionMap.put(ROLE, User::getRoleName);


       return userList.stream().filter(user -> searchConditionMap.get(conditions).callMethod(User).equals(searchTerm));
    }

    //    private boolean equalsUsersList(List<User> usersListFromView, List<User> usersListFromDB) {
//        if (usersListFromDB == null && usersListFromView == null) {
//            return true;
//        }
//        if ((usersListFromDB == null && usersListFromView == null)
//                || usersListFromDB != null && usersListFromView != null
//                || usersListFromDB.size() != usersListFromView.size()) {
//            return false;
//        }
//        usersListFromDB = new ArrayList<>();
//        usersListFromView = new ArrayList<>();
//
//
//        return true;
//    }
}



