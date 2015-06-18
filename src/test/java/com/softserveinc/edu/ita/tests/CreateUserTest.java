package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.NewUserPage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import com.softserveinc.edu.ita.utils.DBUtility;
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
        loggingAssert.assertEquals(newUser.getLogin(), lastUser.getLogin(), "New user adding.");

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
        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL),
                NewUserPageLocators.LOGIN_NAME_ERROR_LABEL.getName() + " displayed");

        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.FIRST_NAME_ERROR_LABEL),
                NewUserPageLocators.FIRST_NAME_ERROR_LABEL.getName() + " displayed");

        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LAST_NAME_ERROR_LABEL),
                NewUserPageLocators.LAST_NAME_ERROR_LABEL.getName() + " displayed");

        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.PASSWORD_ERROR_LABEL),
                NewUserPageLocators.PASSWORD_ERROR_LABEL.getName() + " displayed");

        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.EMAIL_ERROR_LABEL),
                NewUserPageLocators.EMAIL_ERROR_LABEL.getName() + " displayed");

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
        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL),
                NewUserPageLocators.LOGIN_NAME_ERROR_LABEL.getName() + " displayed");

        newUserPage.fillFirstNameField(newUser.getFirstName());
        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.FIRST_NAME_ERROR_LABEL),
                NewUserPageLocators.FIRST_NAME_ERROR_LABEL.getName() + " displayed");

        newUserPage.fillLastNameField(newUser.getLastName());
        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LAST_NAME_ERROR_LABEL),
                NewUserPageLocators.LAST_NAME_ERROR_LABEL.getName() + " displayed");

        newUserPage.fillPasswordField(newUser.getPassword());
        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.PASSWORD_ERROR_LABEL),
                NewUserPageLocators.PASSWORD_ERROR_LABEL.getName() + " displayed");

        newUserPage.fillConfirmPasswordField(newUser.getPassword() + "_");
        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.CONFIRM_PASSWORD_ERROR_LABEL),
                NewUserPageLocators.CONFIRM_PASSWORD_ERROR_LABEL.getName() + " displayed");

        newUserPage.fillEmailField(newUser.getEmail());
        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.EMAIL_ERROR_LABEL),
                NewUserPageLocators.EMAIL_ERROR_LABEL.getName() + " displayed");

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

        newUserPage.fillLoginNameField(login);
        loggingAssert.assertTrue(newUserPage.isElementDisplayed(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL),
                NewUserPageLocators.LOGIN_NAME_ERROR_LABEL.getName() + " displayed");

        newUserPage.clickLogOutButton();
    }
}
