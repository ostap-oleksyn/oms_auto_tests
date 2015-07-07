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
            LocatorsType.BY_XPATH,
            ".//*[@id='login']"),
    FIRST_NAME_INPUT(
            "First name input",
            LocatorsType.BY_XPATH,
            ".//*[@id='firstName']"),
    LAST_NAME_INPUT(
            "Last name input",
            LocatorsType.BY_XPATH,
            ".//*[@id='lastName']"),
    PASSWORD_INPUT(
            "Password input",
            LocatorsType.BY_XPATH,
            ".//*[@id='password']"),
    CONFIRM_PASSWORD_INPUT(
            "Confirm password input",
            LocatorsType.BY_XPATH,
            ".//*[@id='confirmPassword']"),
    EMAIL_INPUT(
            "Email input",
            LocatorsType.BY_XPATH,
            ".//*[@id='email']"),
    REGION_SELECT(
            "Region select",
            LocatorsType.BY_XPATH,
            ".//*[@id='regionID']"),
    CREATE_BUTTON(
            "Create button",
            LocatorsType.BY_XPATH,
            ".//input[@type='submit'][@value='Create']"),
    LOGIN_NAME_ERROR_LABEL(
            "Login name error label",
            LocatorsType.BY_XPATH,
            ".//*[@id='nameError']"),
    FIRST_NAME_ERROR_LABEL(
            "First name error label",
            LocatorsType.BY_XPATH,
            ".//*[@id='firstNameError']"),
    LAST_NAME_ERROR_LABEL(
            "Last name error label",
            LocatorsType.BY_XPATH,
            ".//*[@id='lastNameError']"),
    PASSWORD_ERROR_LABEL(
            "Password error label",
            LocatorsType.BY_XPATH,
            ".//*[@id='passwordError']"),
    CONFIRM_PASSWORD_ERROR_LABEL(
            "Confirm password error label",
            LocatorsType.BY_XPATH,
            ".//*[@id='confirmError']"),
    EMAIL_ERROR_LABEL(
            "Email error label",
            LocatorsType.BY_XPATH,
            ".//*[@id='emailError']"),
    ROLE_SELECT(
            "Role select",
            LocatorsType.BY_XPATH,
            ".//*[@id='roleID%s']");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    NewUserPageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
        this.name = name;
        this.locatorsType = locatorsType;
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

    /**
     * Modifies the locator by inserting the given string.
     *
     * @param parameter - modifier that will be inserted into the locator.
     */
    public NewUserPageLocators modify(final String parameter) {
        this.modifiedLocator = String.format(this.rawLocator, parameter);
        return this;
    }

    @Override
    //This method converts locator into "By" format.
    public By getBy() {
        //This block of code is used to leave raw locator intact giving a possibility to use parameterized locator again.
        if (this.modifiedLocator == null) {
            this.byLocator = this.locatorsType.getBy(this.rawLocator);
        } else {
            this.byLocator = this.locatorsType.getBy(this.modifiedLocator);
        }
        return this.byLocator;
    }
}