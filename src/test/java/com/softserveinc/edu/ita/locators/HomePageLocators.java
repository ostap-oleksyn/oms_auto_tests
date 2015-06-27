package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * This enum includes two type of locators:
 * the first type locators are used without preliminary preparation;
 * the second type locators can be used after advance modification.
 */
public enum HomePageLocators implements ILocator {

    LOGIN_USER_INPUT(
            "Login input field",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='edit']//input[@name = 'j_username']"),
    LOGIN_PASSWORD_INPUT(
            "Password input field",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='edit']//input[@name = 'j_password']"),
    LOGIN_SUBMIT_BUTTON(
            "Login submit button",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='edit']//input[@name = 'submit']"),
    LOGIN_ERROR_MESSAGE(
            "Login error message",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='edit']/fieldset/font");

    private String name;
    private SeleniumByMethods seleniumByMethod;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    HomePageLocators(String name, SeleniumByMethods seleniumByMethod, String rawLocator) {
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
    public HomePageLocators modify(String parameter) {
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
