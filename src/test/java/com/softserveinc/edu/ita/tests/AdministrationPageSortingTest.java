package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.enums.UsersTable;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

/**
 * This class is used to test sorting actions in 'Administration' table of 'Administration' page.
 */
public class AdministrationPageSortingTest extends TestRunner {

    HomePage homePage;
    UserInfoPage userInfoPage;
    AdministrationPage administrationPage;
    List<UserFromView> tableFromView;
    List<UserFromView> tableFromViewSortedAsc;
    List<UserFromView> tableFromViewSortedDesc;

    //Test sorting by clicking "First Name" column.
    @Test
    public void testFirstNameColumn() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn("iva", "qwerty");
        administrationPage = userInfoPage.clickAdministrationTab();

        tableFromView = administrationPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(UserFromView::getFirstName));

        administrationPage.clickAdministrationTableColumn(UsersTable.FIRST_NAME);
        tableFromViewSortedAsc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedAsc, UsersTable.FIRST_NAME),
                "Table isn't sorted correctly by clicking 'First Name' column.");

        tableFromView.sort(Comparator.comparing(UserFromView::getFirstName).reversed());

        administrationPage.clickAdministrationTableColumn(UsersTable.FIRST_NAME);
        tableFromViewSortedDesc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedDesc, UsersTable.FIRST_NAME),
                "Table isn't sorted correctly by clicking 'First Name' column.");
    }

    //Test sorting by by clicking "Last Name" column.
    @Test
    public void testLastNameColumn() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        tableFromView.sort(Comparator.comparing(UserFromView::getLastName));

        administrationPage.clickAdministrationTableColumn(UsersTable.LAST_NAME);
        tableFromViewSortedAsc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedAsc, UsersTable.LAST_NAME),
                "Table isn't sorted correctly by clicking 'Last Name' column.");

        tableFromView.sort(Comparator.comparing(UserFromView::getLastName).reversed());

        administrationPage.clickAdministrationTableColumn(UsersTable.LAST_NAME);
        tableFromViewSortedDesc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedDesc, UsersTable.LAST_NAME),
                "Table isn't sorted correctly by clicking 'Last Name' column.");
    }

    //Test sorting by clicking "Login" column.
    @Test
    public void testLoginColumn() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        tableFromView.sort(Comparator.comparing(UserFromView::getLogin));

        administrationPage.clickAdministrationTableColumn(UsersTable.LOGIN);
        tableFromViewSortedAsc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedAsc, UsersTable.LOGIN),
                "Table isn't sorted correctly by clicking 'Login' column.");

        tableFromView.sort(Comparator.comparing(UserFromView::getLogin).reversed());

        administrationPage.clickAdministrationTableColumn(UsersTable.LOGIN);
        tableFromViewSortedDesc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedDesc, UsersTable.LOGIN),
                "Table isn't sorted correctly by clicking 'Login' column.");
    }

    //Test sorting by clicking "Role" column.
    @Test
    public void testRoleColumn() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        tableFromView.sort(Comparator.comparing(UserFromView::getRole));

        administrationPage.clickAdministrationTableColumn(UsersTable.ROLE);
        List<UserFromView> tableFromViewSortedAsc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedAsc, UsersTable.ROLE),
                "Table isn't sorted correctly by clicking 'Role' column.");

        tableFromView.sort(Comparator.comparing(UserFromView::getRole).reversed());

        administrationPage.clickAdministrationTableColumn(UsersTable.ROLE);
        List<UserFromView> tableFromViewSortedDesc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedDesc, UsersTable.ROLE),
                "Table isn't sorted correctly by clicking 'Role' column.");
    }

    //Test sorting by clicking "Region" column.
    @Test
    public void testRegionColumn() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        tableFromView.sort(Comparator.comparing(UserFromView::getRegion));

        administrationPage.clickAdministrationTableColumn(UsersTable.REGION);
        List<UserFromView> tableFromViewSortedAsc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedAsc, UsersTable.REGION),
                "Table isn't sorted correctly by clicking 'Region' column.");

        tableFromView.sort(Comparator.comparing(UserFromView::getRegion).reversed());

        administrationPage.clickAdministrationTableColumn(UsersTable.REGION);
        List<UserFromView> tableFromViewSortedDesc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(tableFromView, tableFromViewSortedDesc, UsersTable.REGION),
                "Table isn't sorted correctly by clicking 'Region' column.");

        administrationPage.clickLogOutButton();
    }

}

