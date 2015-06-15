package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import com.softserveinc.edu.ita.page_object.*;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.Map.Entry;

import static com.softserveinc.edu.ita.utils.StringsGenerator.generateString;
import static org.openqa.selenium.Keys.HOME;

/**
 * Test of new User creating (Ticket IFAA-9)
 */
public class CreateUserTest extends TestRunner {

    /**
     * Test new User creating with valid data
     *
     * @param newUser new valid user data from dataprovider
     */
    @Test(dataProvider = "generatedValidUserData", dataProviderClass = DataProviders.class, enabled = false)
    public void testValidUserCreating(User newUser) {

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.fillUserData(newUser);
        administrationPage = newUserPage.clickCreateButton();

        User lastUser = DBUtility.getLastUser();
        Assert.assertEquals(newUser.getLogin(), lastUser.getLogin());

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating with empty data
     */
    @Test(enabled = false)
    public void testEmptyUserCreate() {

        final String ERROR_ALERT_MESSAGE = "check all fields for valid data";
        final String CANNOT_BE_BLANK_MESSAGE = " cannot be blank";

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        LogOutBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();
        newUserPage.clickOnElement(NewUserPageLocators.CREATE_BUTTON);

        // test application shows alert
        Assert.assertEquals(newUserPage.getAlertText(), ERROR_ALERT_MESSAGE);
        newUserPage.closeAlert();

        // test fields returns error message for empty data
        Assert.assertEquals("Login name" + CANNOT_BE_BLANK_MESSAGE,
                newUserPage.getElementText(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));
        Assert.assertEquals("First name" + CANNOT_BE_BLANK_MESSAGE,
                newUserPage.getElementText(NewUserPageLocators.FIRST_NAME_ERROR_LABEL));
        Assert.assertEquals("Last name" + CANNOT_BE_BLANK_MESSAGE,
                newUserPage.getElementText(NewUserPageLocators.LAST_NAME_ERROR_LABEL));
        Assert.assertEquals("Password" + CANNOT_BE_BLANK_MESSAGE,
                newUserPage.getElementText(NewUserPageLocators.PASSWORD_ERROR_LABEL));
        Assert.assertEquals("Email" + CANNOT_BE_BLANK_MESSAGE,
                newUserPage.getElementText(NewUserPageLocators.EMAIL_ERROR_LABEL));

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating not valid data
     */
    @Test(enabled = true)
    public void testNotValidUserCreate() {

        final String CANNOT_CONTAIN_DIGITS_MESSAGE = " cannot contain digits";
        final String IS_TOO_LONG_MESSAGE = " is too long";
        final String PASSWORD_LENGTH_MESSAGE = "Password cannot be shorter than 4 and longer than 10 characters";
        final String CONFIRM_PASSWORD_MESSAGE = "Confirm password has to be equal to password";
        final String EMAIL_MESSAGE = "You should use valid email address";

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        LogOutBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        // Test Login Name, First Name and Last Name don't contain digits
        newUserPage.fillNamesWithDigits();
        Assert.assertEquals(newUserPage.getElementText(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL),
                "Login name" + CANNOT_CONTAIN_DIGITS_MESSAGE);
        Assert.assertEquals(newUserPage.getElementText(NewUserPageLocators.FIRST_NAME_ERROR_LABEL),
                "First name" + CANNOT_CONTAIN_DIGITS_MESSAGE);
        Assert.assertEquals(newUserPage.getElementText(NewUserPageLocators.LAST_NAME_ERROR_LABEL),
                "Last name" + CANNOT_CONTAIN_DIGITS_MESSAGE);


        System.out.println(driver.findElement(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL).isDisplayed());

        // Test Login Name, First Name and Last Name' length isn't more 13 symbols
        newUserPage.fillNamesWithLongStrings();
        Assert.assertEquals(newUserPage.getElementText(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL),
                "Login name" + IS_TOO_LONG_MESSAGE);
        Assert.assertEquals(newUserPage.getElementText(NewUserPageLocators.FIRST_NAME_ERROR_LABEL),
                "First name" + IS_TOO_LONG_MESSAGE);
        Assert.assertEquals(newUserPage.getElementText(NewUserPageLocators.LAST_NAME_ERROR_LABEL),
                "Last name" + IS_TOO_LONG_MESSAGE);

        // Test Password length is less than 4 symbols
        newUserPage.fillPasswordWithShortString();
        Assert.assertEquals(
                newUserPage.getElementText(NewUserPageLocators.PASSWORD_ERROR_LABEL),
                PASSWORD_LENGTH_MESSAGE
        );

        // Test Password length is more than 13 symbols
        newUserPage.fillPasswordWithLongString();
        Assert.assertEquals(
                newUserPage.getElementText(NewUserPageLocators.PASSWORD_ERROR_LABEL),
                PASSWORD_LENGTH_MESSAGE
        );

        // Test Confirm Password returns error message if passwords are not equal
        newUserPage.fillPasswordWithNotEqual();
//        newUserPage.sendTextToElement(generateString("password_symbols", 1, 13)
//                + String.valueOf(HOME), NewUserPageLocators.CONFIRM_PASSWORD_INPUT);
        Assert.assertEquals(
                newUserPage.getElementText(NewUserPageLocators.CONFIRM_PASSWORD_ERROR_LABEL),
                CONFIRM_PASSWORD_MESSAGE
        );

        // Test Email
        newUserPage.fillEmail();
        Assert.assertEquals(newUserPage.getElementText(NewUserPageLocators.EMAIL_ERROR_LABEL), EMAIL_MESSAGE);

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating over existing user
     */
    @Test(enabled=false)
    public void testExistingUserCreate() {
        final String ALREADY_IN_USE = " already in use";

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        LogOutBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();

        String login = administrationPage.getRadomLoginFromView();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.fillLogin(login);
        Assert.assertEquals(login + ALREADY_IN_USE,
                newUserPage.getElementText(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));

        newUserPage.clickLogOutButton();
    }
}
