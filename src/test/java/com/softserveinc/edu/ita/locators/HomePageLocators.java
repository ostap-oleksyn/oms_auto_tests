package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.locators.interfaces.ILocator;
import org.openqa.selenium.By;

public enum HomePageLocators implements ILocator {

    LOGIN_USER_INPUT(
            "Login input field",
            LocatorsType.BY_XPATH,
            ".//*[@id='edit']//input[@name = 'j_username']"),
    LOGIN_PASSWORD_INPUT(
            "Password input field",
            LocatorsType.BY_XPATH,
            ".//*[@id='edit']//input[@name = 'j_password']"),
    LOGIN_SUBMIT_BUTTON(
            "Login submit button",
            LocatorsType.BY_XPATH,
            ".//*[@id='edit']//input[@name = 'submit']"),
    LOGIN_ERROR_MESSAGE(
            "Login error message",
            LocatorsType.BY_XPATH,
            ".//*[@id='edit']/fieldset/font");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    HomePageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
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
    public HomePageLocators modify(final String parameter) {
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
