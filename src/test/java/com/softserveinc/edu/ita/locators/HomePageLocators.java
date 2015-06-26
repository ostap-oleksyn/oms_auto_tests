package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum HomePageLocators implements ILocator {
    LOGIN_USER_INPUT(
            "Login input field",
            "xpath",
            ".//*[@id='edit']//input[@name = 'j_username']"),
    LOGIN_PASSWORD_INPUT(
            "Password input field",
            "xpath",
            ".//*[@id='edit']//input[@name = 'j_password']"),
    LOGIN_SUBMIT_BUTTON(
            "Login submit button",
            "xpath",
            ".//*[@id='edit']//input[@name = 'submit']"),
    LOGIN_ERROR_MESSAGE(
            "Login error message",
            "xpath",
            ".//*[@id='edit']/fieldset/font");

    private String name;
    private String locatorsType;
    private String rowLocator;
    private String modifiedLocator;
    private By byLocator;

    HomePageLocators(String name, String locatorsType, String rowLocator) {
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

    public HomePageLocators modify(String parameter) {
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
