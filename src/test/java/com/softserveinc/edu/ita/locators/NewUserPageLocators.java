package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * This enum includes two type of locators:
 * the first type locators are used without preliminary preparation;
 * the second type locators can be used after advance modification.
 */
public enum NewUserPageLocators implements ILocator {

    LOGIN_NAME_INPUT(
            "Login name input",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='login']"),
    FIRST_NAME_INPUT(
            "First name input",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='firstName']"),
    LAST_NAME_INPUT(
            "Last name input",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='lastName']"),
    PASSWORD_INPUT(
            "Password input",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='password']"),
    CONFIRM_PASSWORD_INPUT(
            "Confirm password input",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='confirmPassword']"),
    EMAIL_INPUT(
            "Email input",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='email']"),
    REGION_SELECT(
            "Region select",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='regionID']"),
    CREATE_BUTTON(
            "Create button",
            SeleniumByMethods.BY_XPATH,
            ".//input[@type='submit'][@value='Create']"),
    LOGIN_NAME_ERROR_LABEL(
            "Login name error label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='nameError']"),
    FIRST_NAME_ERROR_LABEL(
            "First name error label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='firstNameError']"),
    LAST_NAME_ERROR_LABEL(
            "Last name error label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='lastNameError']"),
    PASSWORD_ERROR_LABEL(
            "Password error label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='passwordError']"),
    CONFIRM_PASSWORD_ERROR_LABEL(
            "Confirm password error label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='confirmError']"),
    EMAIL_ERROR_LABEL(
            "Email error label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='emailError']"),
    ROLE_SELECT(
            "",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='roleID%s']");

    private String name;
    private SeleniumByMethods seleniumByMethod;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    NewUserPageLocators(String name, SeleniumByMethods seleniumByMethod, String rawLocator) {
        this.name = name;
        this.seleniumByMethod = seleniumByMethod;
        this.rawLocator = rawLocator;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //This method prepares locator using additional parameter by means of so called "string-format" method.
    public NewUserPageLocators modify(String parameter) {
        this.name = parameter;
        this.modifiedLocator = String.format(this.rawLocator, parameter);
        return this;
    }

    @Override
    //This method converts locator into "By" format.
    public By getBy() {
        //This block of code is used to leave raw locator intact giving a possibility to use parameterized locator again.
        if (this.modifiedLocator == null) {
            this.byLocator = this.seleniumByMethod.getBy(this.rawLocator);
        } else {
            this.byLocator = this.seleniumByMethod.getBy(this.modifiedLocator);
        }
        return this.byLocator;
    }
}