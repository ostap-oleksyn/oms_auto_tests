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

        editUserPage.fillFirstNameField("Hey")
                .fillLastNameField("Joe")
                .fillPasswordFields("qwerty")
                .selectRegion(2)
                .selectRole(Roles.CUSTOMER);

        administrationPage = editUserPage.clickCreateButton();
        homePage = administrationPage.clickLogOutButton();

        User editUser = DBUtility.getByLogin(editUserLogin);

        loggingAssert.assertEquals(editUser.getFirstName(), "Hey", "User first name is changed in database");
        loggingAssert.assertEquals(editUser.getLastName(), "Joe", "User last name is changed in database");
        loggingAssert.assertEquals(editUser.getRegionReference(), Regions.SOUTH, "User region is changed in database");
        loggingAssert.assertEquals(editUser.getRoleReference(), Roles.CUSTOMER, "User role is changed in database");

        userInfoPage = homePage.logIn(editUser.getLogin(), editUser.getPassword());

        loggingAssert.assertTrue(homePage.getElementText(UserInfoPageLocators.FIRST_NAME_LABEL)
                .contains("Hey"), "User first name is changed");
        loggingAssert.assertTrue(homePage.getElementText(UserInfoPageLocators.LAST_NAME_LABEL)
                .contains("Joe"), "User last name is changed");
        loggingAssert.assertTrue(homePage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .contains("Customer"), "User role is changed");

        userInfoPage.clickLogOutButton();
    }
}
