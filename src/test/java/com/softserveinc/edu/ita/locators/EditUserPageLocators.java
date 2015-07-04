package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum EditUserPageLocators implements ILocator {

    LOGIN_INPUT(
            "Login input",
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
    EMAIL_NAME_INPUT(
            "Email name input",
            LocatorsType.BY_XPATH,
            ".//*[@id='email']"),
    PASSWORD_INPUT(
            "Password input",
            LocatorsType.BY_XPATH,
            ".//*[@id='password']"),
    CONFIRM_PASSWORD_INPUT(
            "Confirm password input",
            LocatorsType.BY_XPATH,
            ".//*[@id='confirmPassword']"),
    REGION_SELECT(
            "Region select",
            LocatorsType.BY_XPATH,
            ".//*[@id='regionID']"),
    ROLE_MERCHANDISER_SELECT(
            "Role customer select",
            LocatorsType.BY_XPATH,
            ".//*[@id='roleID2']"),
    CREATE_BUTTON(
            "Create button",
            LocatorsType.BY_XPATH,
            ".//input[@type='submit'][@value='Create']");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    EditUserPageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
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
     * This method prepares locator using additional parameter
     * by means of so called "string-format" method.
     */
    public EditUserPageLocators modify(final String parameter) {
        this.name = parameter;
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