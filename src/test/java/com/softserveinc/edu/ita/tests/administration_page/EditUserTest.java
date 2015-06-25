package com.softserveinc.edu.ita.tests.administration_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Regions;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.pageobjects.AdministrationPage;
import com.softserveinc.edu.ita.pageobjects.EditUserPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.testng.annotations.Test;

import java.io.IOException;

public class EditUserTest extends TestRunner {

    @Test
    public void testUserEditing() throws IOException {

        HomePage homePage = new HomePage(driver);
        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        EditUserPage editUserPage = administrationPage.clickRandomEditButton();
        String editUserLogin = editUserPage.getEditUserLogin();

        editUserPage.changeFirstName("Hey");
        editUserPage.changeLastName("Joe");
        editUserPage.fillPasswordFields("qwerty");
        editUserPage.selectRegion(Regions.NORTH);
        editUserPage.changeRole(Roles.CUSTOMER);

        administrationPage = editUserPage.clickCreateButton();
        homePage = administrationPage.clickLogOutButton();

        User editUser = DBUtility.getByLogin(editUserLogin);

        loggingAssert.assertEquals(editUser.getFirstName(), "Hey", "User first name is changed in database");
        loggingAssert.assertEquals(editUser.getLastName(), "Joe", "User last name is changed in database");
        loggingAssert.assertEquals(editUser.getRegionReference(), "North", "User region is changed in database");
        loggingAssert.assertEquals(editUser.getRoleReference(), "Customer", "User role is changed in database");

        userInfoPage = homePage.logIn(editUser.getLogin(), editUser.getPassword());

        loggingAssert.assertTrue(driver.findElement(UserInfoPageLocators.FIRST_NAME_LABEL.getBy())
                .getText()
                .toLowerCase()
                .contains("Hey"));
        loggingAssert.assertTrue(driver.findElement(UserInfoPageLocators.LAST_NAME_LABEL.getBy())
                .getText()
                .toLowerCase()
                .contains("Joe"));
        loggingAssert.assertTrue(driver.findElement(UserInfoPageLocators.USER_ROLE_LABEL.getBy())
                .getText()
                .toLowerCase()
                .contains("Customer"));

        userInfoPage.clickLogOutButton();
    }
}
