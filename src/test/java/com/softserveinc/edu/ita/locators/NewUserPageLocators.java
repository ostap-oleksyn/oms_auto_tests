package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum NewUserPageLocators implements ILocator {

    LOGIN_NAME_INPUT(
            "Login name input",
            "xpath",
            ".//*[@id='login']"),

    FIRST_NAME_INPUT(
            "First name input",
            "xpath",
            ".//*[@id='firstName']"),

    LAST_NAME_INPUT(
            "Last name input",
            "xpath",
            ".//*[@id='lastName']"),

    PASSWORD_INPUT(
            "Password input",
            "xpath",
            ".//*[@id='password']"),

    CONFIRM_PASSWORD_INPUT(
            "Confirm password input",
            "xpath",
            ".//*[@id='confirmPassword']"),

    EMAIL_INPUT(
            "Email input",
            "xpath",
            ".//*[@id='email']"),

    REGION_SELECT(
            "Region select",
            "xpath",
            ".//*[@id='regionID']"),

    CREATE_BUTTON(
            "Create button",
            "xpath",
            ".//input[@type='submit'][@value='Create']"),

    LOGIN_NAME_ERROR_LABEL(
            "Login name error label",
            "xpath",
            ".//*[@id='nameError']"),

    FIRST_NAME_ERROR_LABEL(
            "First name error label",
            "xpath",
            ".//*[@id='firstNameError']"),

    LAST_NAME_ERROR_LABEL(
            "Last name error label",
            "xpath",
            ".//*[@id='lastNameError']"),

    PASSWORD_ERROR_LABEL(
            "Password error label",
            "xpath",
            ".//*[@id='passwordError']"),

    CONFIRM_PASSWORD_ERROR_LABEL(
            "Confirm password error label",
            "xpath",
            ".//*[@id='confirmError']"),

    EMAIL_ERROR_LABEL(
            "Email error label",
            "xpath",
            ".//*[@id='emailError']"),

    ROLE_SELECT(
            "",
            "xpath",
            ".//*[@id='roleID%s']");

    private String name;
    private String locatorsType;
    private String rowLocator;
    private String modifiedLocator;
    private By byLocator;

    NewUserPageLocators(String name, String locatorsType, String rowLocator) {
        this.name = name;
        this.locatorsType = locatorsType;
        this.rowLocator = rowLocator;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public NewUserPageLocators modify(String parameter) {
        this.name = parameter;
        this.modifiedLocator = String.format(this.rowLocator, parameter);
        return this;
    }

    @Override
    public By getBy() {
        String locator;
        if (this.modifiedLocator == null) {
            locator = this.rowLocator;
        } else {
            locator = this.modifiedLocator;
        }
        switch (this.locatorsType) {
            case ("className"):
                this.byLocator = By.className(locator);
                break;
            case ("cssSelector"):
                this.byLocator = By.cssSelector(locator);
                break;
            case ("id"):
                this.byLocator = By.id(locator);
                break;
            case ("name"):
                this.byLocator = By.name(locator);
                break;
            case ("tagName"):
                this.byLocator = By.tagName(locator);
                break;
            case ("xpath"):
                this.byLocator = By.xpath(locator);
                break;
            default:
                System.out.println("Locator's type is incorrect.");
                break;
        }
        return this.byLocator;
    }
}