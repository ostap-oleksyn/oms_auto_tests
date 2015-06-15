package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import com.softserveinc.edu.ita.page_object.*;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test of new User creating (Ticket IFAA-9)
 */
public class CreateUserTest extends TestRunner {

    /**
     * Test new User creating with valid data
     *
     * @param newUser new valid User data from dataprovider
     */
    @Test(dataProvider = "generatedValidUserData", dataProviderClass = DataProviders.class, enabled = true)
    public void testValidUserCreating(User newUser) {

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.fillAllUserData(newUser);
        administrationPage = newUserPage.clickCreateButtonForValidData();

        User lastUser = DBUtility.getLastUser();
        Assert.assertEquals(newUser.getLogin(), lastUser.getLogin());

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating with empty data
     */
    @Test(enabled = true)
    public void testEmptyUserCreate() {

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        LogOutBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();
        newUserPage.clickCreateButtonForNotValidData();

        newUserPage.closeAlert();

        // test fields returns error message for empty data
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.FIRST_NAME_ERROR_LABEL));
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.LAST_NAME_ERROR_LABEL));
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.PASSWORD_ERROR_LABEL));
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.EMAIL_ERROR_LABEL));

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating not valid data
     */
    @Test(dataProvider = "generatedNotValidUserData", dataProviderClass = DataProviders.class, enabled = true)
    public void testNotValidUserCreate(User newUser) {

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        LogOutBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.fillUserDataInput(NewUserPageLocators.LOGIN_NAME_INPUT, newUser.getLogin());
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));

        newUserPage.fillUserDataInput(NewUserPageLocators.FIRST_NAME_INPUT, newUser.getFirstName());
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.FIRST_NAME_ERROR_LABEL));

        newUserPage.fillUserDataInput(NewUserPageLocators.LAST_NAME_INPUT, newUser.getLastName());
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.LAST_NAME_ERROR_LABEL));

        newUserPage.fillUserDataInput(NewUserPageLocators.PASSWORD_INPUT, newUser.getPassword());
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.PASSWORD_ERROR_LABEL));

        newUserPage.fillUserDataInput(NewUserPageLocators.CONFIRM_PASSWORD_INPUT, newUser.getPassword() + "_");
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.CONFIRM_PASSWORD_ERROR_LABEL));

        newUserPage.fillUserDataInput(NewUserPageLocators.EMAIL_INPUT, newUser.getEmail());
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.EMAIL_ERROR_LABEL));

        newUserPage.clickCreateButtonForNotValidData();
        newUserPage.closeAlert();

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating over existing user
     */
    @Test(enabled = true)
    public void testExistingUserCreate() {
        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        LogOutBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();

        String login = administrationPage.getRadomLoginFromView();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.fillUserDataInput(NewUserPageLocators.LOGIN_NAME_INPUT, login);
        Assert.assertTrue(newUserPage.isErrorDisplayed(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));

        newUserPage.clickLogOutButton();
    }
}
