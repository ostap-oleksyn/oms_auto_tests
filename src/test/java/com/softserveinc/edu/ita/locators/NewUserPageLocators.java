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
            "string",
            ".//*[@id='roleID%s']");

    private String name;
    private String locatorType;
    private String locator;
    private By byLocator;

    NewUserPageLocators(String name, String locatorType, String locator) {
        this.name = name;
        this.locatorType = locatorType;
        this.locator = locator;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getLocatorType() {
        return this.locatorType;
    }

    public String getLocator() {
        return this.locator;
    }

    @Override
    public By getBy() {
        if (this.getLocator().contains("%s")) {
            return null;
        } else {
            switch (this.getLocatorType()) {
                case ("className"):
                    this.byLocator = By.className(this.getLocator());
                    break;
                case ("cssSelector"):
                    this.byLocator = By.cssSelector(this.getLocator());
                    break;
                case ("id"):
                    this.byLocator = By.id(this.getLocator());
                    break;
                case ("name"):
                    this.byLocator = By.name(this.getLocator());
                    break;
                case ("tagName"):
                    this.byLocator = By.tagName(this.getLocator());
                    break;
                case ("xpath"):
                    this.byLocator = By.xpath(this.getLocator());
                    break;
                default:
                    System.out.println("Locator's type is incorrect.");
                    break;
            }
            return this.byLocator;
        }
    }

    public By getBy(String parameter) {
        if (this.getLocator().contains("%s")) {
            switch (this.getLocatorType()) {
                case ("className"):
                    this.byLocator = By.className(String.format(this.getLocator(), parameter));
                    break;
                case ("cssSelector"):
                    this.byLocator = By.cssSelector(String.format(this.getLocator(), parameter));
                    break;
                case ("id"):
                    this.byLocator = By.id(String.format(this.getLocator(), parameter));
                    break;
                case ("name"):
                    this.byLocator = By.name(String.format(this.getLocator(), parameter));
                    break;
                case ("tagName"):
                    this.byLocator = By.tagName(String.format(this.getLocator(), parameter));
                    break;
                case ("xpath"):
                    this.byLocator = By.xpath(String.format(this.getLocator(), parameter));
                    break;
                default:
                    System.out.println("Locator's type is incorrect.");
                    break;
            }
            return this.byLocator;
        } else {
            return null;
        }
    }
}