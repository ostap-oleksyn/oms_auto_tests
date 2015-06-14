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
    @Test(dataProvider = "generatedValidUserData", dataProviderClass = DataProviders.class)
    public void testValidUserCreating(User newUser) {

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        // create new user
        newUserPage.sendTextToElement(newUser.getLogin(), NewUserPageLocators.LOGIN_NAME_INPUT);
        newUserPage.sendTextToElement(newUser.getFirstName(), NewUserPageLocators.FIRST_NAME_INPUT);
        newUserPage.sendTextToElement(newUser.getLastName(), NewUserPageLocators.LAST_NAME_INPUT);
        newUserPage.sendTextToElement(newUser.getPassword(), NewUserPageLocators.PASSWORD_INPUT);
        newUserPage.sendTextToElement(newUser.getPassword(), NewUserPageLocators.CONFIRM_PASSWORD_INPUT);
        newUserPage.sendTextToElement(newUser.getEmail(), NewUserPageLocators.EMAIL_INPUT);
        newUserPage.selectRegion(newUser.getRegionName());
        newUserPage.clickOnElement(By.xpath(String.format(NewUserPageLocators.ROLE_SELECT, newUser.getRoleName())));
        administrationPage = newUserPage.clickCreateButton();

        // and check it in database
        User lastUser = DBUtility.getLastUser();
        Assert.assertEquals(newUser.getLogin(), lastUser.getLogin());

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating with empty data
     */
    @Test
    public void testEmptyUserCreate() {

        final String ERROR_ALERT_MESSAGE = "check all fields for valid data";
        final String CANNOT_BE_BLANK_MESSAGE = " cannot be blank";

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        LogOutBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();
        newUserPage.clickOnElement(NewUserPageLocators.CREATE_BUTTON);

        Assert.assertEquals(newUserPage.getAlertTextAndClose(), ERROR_ALERT_MESSAGE);

        // test fields returns error message for empty data
        Map<String, String> notEmptyFields = new HashMap<String, String>() {{
            put("Login name", newUserPage.getElementText(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));
            put("First name", newUserPage.getElementText(NewUserPageLocators.FIRST_NAME_ERROR_LABEL));
            put("Last name", newUserPage.getElementText(NewUserPageLocators.LAST_NAME_ERROR_LABEL));
            put("Password", newUserPage.getElementText(NewUserPageLocators.PASSWORD_ERROR_LABEL));
            put("Email", newUserPage.getElementText(NewUserPageLocators.EMAIL_ERROR_LABEL));
        }};

        for (Entry<String, String> field : notEmptyFields.entrySet()) {
            Assert.assertEquals(field.getKey() + CANNOT_BE_BLANK_MESSAGE, field.getValue().toString());
        }

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating not valid data
     */
    @Test
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

        List<By> nameInputs = new ArrayList<By>() {{
            add(NewUserPageLocators.LOGIN_NAME_INPUT);
            add(NewUserPageLocators.FIRST_NAME_INPUT);
            add(NewUserPageLocators.LAST_NAME_INPUT);
        }};

        List<By> errorLabels = new ArrayList<By>() {{
            add(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL);
            add(NewUserPageLocators.FIRST_NAME_ERROR_LABEL);
            add(NewUserPageLocators.LAST_NAME_ERROR_LABEL);
        }};


        // Test Login Name, First Name and Last Name don't contain digits
        List<String> errorMessages = new ArrayList<String>() {{
            add("Login name" + CANNOT_CONTAIN_DIGITS_MESSAGE);
            add("First name" + CANNOT_CONTAIN_DIGITS_MESSAGE);
            add("Last name" + CANNOT_CONTAIN_DIGITS_MESSAGE);
        }};

        for (int i = 0; i < 3; i++) {
            newUserPage.sendTextToElement(generateString("digits", 1, 13) + String.valueOf(HOME), nameInputs.get(i));
            Assert.assertEquals(newUserPage.getElementText(errorLabels.get(i)), errorMessages.get(i));
        }

        // Test Login Name, First Name and Last Name' length isn't more 13 symbols
        errorMessages = new ArrayList<String>() {{
            add("Login name" + IS_TOO_LONG_MESSAGE);
            add("First name" + IS_TOO_LONG_MESSAGE);
            add("Last name" + IS_TOO_LONG_MESSAGE);
        }};

        for (int i = 0; i < 3; i++) {
            newUserPage.clearElementText(nameInputs.get(i));
            newUserPage.sendTextToElement(generateString("name_symbols", 14, 20)
                    + String.valueOf(HOME), nameInputs.get(i));
            Assert.assertEquals(newUserPage.getElementText(errorLabels.get(i)), errorMessages.get(i));
        }

        // Test Password length is between 4 and 10 symbols, Confirm Password
        newUserPage.sendTextToElement(generateString("password_symbols", 1, 3)
                + String.valueOf(HOME), NewUserPageLocators.PASSWORD_INPUT);
        Assert.assertEquals(
                newUserPage.getElementText(NewUserPageLocators.PASSWORD_ERROR_LABEL),
                PASSWORD_LENGTH_MESSAGE
        );

        newUserPage.sendTextToElement(generateString("password_symbols", 14, 20)
                + String.valueOf(HOME), NewUserPageLocators.PASSWORD_INPUT);
        Assert.assertEquals(
                newUserPage.getElementText(NewUserPageLocators.PASSWORD_ERROR_LABEL),
                PASSWORD_LENGTH_MESSAGE
        );

        // Test Confirm Password returns error message if passwords are not equal
        newUserPage.sendTextToElement(generateString("password_symbols", 1, 13)
                + String.valueOf(HOME), NewUserPageLocators.CONFIRM_PASSWORD_INPUT);
        Assert.assertEquals(
                newUserPage.getElementText(NewUserPageLocators.CONFIRM_PASSWORD_ERROR_LABEL),
                CONFIRM_PASSWORD_MESSAGE
        );

        // Test Email
        newUserPage.sendTextToElement(generateString("digits", 1, 10)
                + String.valueOf(HOME), NewUserPageLocators.EMAIL_INPUT);
        Assert.assertEquals(newUserPage.getElementText(NewUserPageLocators.EMAIL_ERROR_LABEL), EMAIL_MESSAGE);

        administrationPage.clickLogOutButton();
    }

    /**
     * Test new User creating over existing user
     */
    @Test
    public void testExistingUserCreate() {
        final String ALREADY_IN_USE = " already in use";

        HomePage homePage = new HomePage(driver);

        User admin = DBUtility.getAdmin();
        LogOutBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();

        // get random User from table
        Random randomGenerator = new Random();
        int randomLoginRow = randomGenerator.nextInt(4) + 1;

        String login = administrationPage.getElementText(
                By.xpath(String.format(AdministrationPageLocators.LOGIN_CELL, randomLoginRow)));

        // and try to create new User with same login
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.sendTextToElement(login + HOME, NewUserPageLocators.LOGIN_NAME_INPUT);
        Assert.assertEquals(login + ALREADY_IN_USE,
                newUserPage.getElementText(NewUserPageLocators.LOGIN_NAME_ERROR_LABEL));

        newUserPage.clickLogOutButton();
    }
}
