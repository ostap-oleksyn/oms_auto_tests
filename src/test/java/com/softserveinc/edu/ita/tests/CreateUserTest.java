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
    @Test(dataProvider = "generatedValidUserData", dataProviderClass = DataProviders.class)
    public void testValidUserCreating(User newUser) {

        final HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        final UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        final NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.fillAllFields(newUser);
        // TODO try to resolve for valid and not valid data ?
        administrationPage = newUserPage.clickCreateButton();

        final User lastUser = DBUtility.getLastUser();
        Assert.assertEquals(newUser.getLogin(), lastUser.getLogin());

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating with empty data
     */
    @Test
    public void testEmptyUserCreate() {

        final HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        final UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        final NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.clickCreateButton();
        newUserPage.acceptAlert();

        // test fields returns error message for empty data
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.FIRST_NAME_ERROR_LABEL));
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LAST_NAME_ERROR_LABEL));
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.PASSWORD_ERROR_LABEL));
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.EMAIL_ERROR_LABEL));

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating not valid data
     */
    @Test(dataProvider = "generatedNotValidUserData", dataProviderClass = DataProviders.class)
    public void testNotValidUserCreate(User newUser) {

        final HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        final UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        final NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.fillLoginNameField(newUser.getLogin());
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));

        newUserPage.fillFirstNameField(newUser.getFirstName());
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.FIRST_NAME_ERROR_LABEL));

        newUserPage.fillLastNameField(newUser.getLastName());
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LAST_NAME_ERROR_LABEL));

        newUserPage.fillPasswordField(newUser.getPassword());
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.PASSWORD_ERROR_LABEL));

        newUserPage.fillConfirmPasswordField(newUser.getPassword());
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.CONFIRM_PASSWORD_ERROR_LABEL));

        newUserPage.fillEmailField(newUser.getEmail());
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.EMAIL_ERROR_LABEL));

        newUserPage.clickCreateButton();
        newUserPage.acceptAlert();

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating over existing user
     */
    @Test
    public void testExistingUserCreate() {

        final HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        final UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        final String login = administrationPage.getRandomLoginFromView();
        final NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.fillUserDataInput(NewUserPageLocators.LOGIN_NAME_INPUT, login);
        Assert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));

        newUserPage.clickLogOutButton();
    }
}
