package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;
import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.enums.UsersTable;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by student on 6/11/2015.
 */
public class AdministrationPageSortingTest extends TestRunner {
    //Test sorting by First Name
    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void testFirstNameColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        List<UserFromView> tableFromView = administrationPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(UserFromView::getFirstName));

        administrationPage.clickAdministrationTableColumn(UsersTable.FIRST_NAME);
        List<UserFromView> sortedTableByFirstNameAsc = administrationPage.getTableFromView();
        administrationPage.clickAdministrationTableColumn(UsersTable.FIRST_NAME);
        List<UserFromView> sortedTableByFirstNameDesc = administrationPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            Assert.assertTrue(sortedTableByFirstNameAsc.get(i).getFirstName().equals(tableFromView.get(i).getFirstName()),
                    "Sorting by first name doesn't work.");
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            Assert.assertTrue(sortedTableByFirstNameDesc.get(i).getFirstName().equals(tableFromView.get(j).getFirstName()),
                    "Sorting by first name doesn't work.");
        }

    }

//    @AfterMethod
//    void logOut() {
//        OrderingPage orderingPage = new OrderingPage(driver);
//        orderingPage.clickLogOutButton();
//    }

}

