package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum CommonLocators implements ILocator {

    USER_INFO_TAB(
            "User info tab",
            "xpath",
            ".//*[@id='nav']//a[@href='/OMS/userInfo.htm']"),
    ITEM_MANAGEMENT_TAB(
            "Item management tab",
            "xpath",
            ".//*[@id='nav']//a[@href='/OMS/itemManagement.htm']"),
    ADMINISTRATION_TAB(
            "Administration tab",
            "xpath",
            ".//*[@id='nav']//a[@href='/OMS/users.htm']"),
    ORDERING_TAB(
            "Ordering tab",
            "xpath",
            ".//*[@id='nav']//a[@href='/OMS/order.htm']"),
    LOG_OUT_BUTTON(
            "Logout button",
            "xpath",
            "//img[@alt='logout']"),
    ACTIVE_TAB(
            "Active tab",
            "xpath",
            ".//a[parent::li[@class='cur']]"),
    EN_LINK(
            "English language link",
            "xpath",
            ".//*[@id='content']/div/a[1]"),
    UA_LINK(
            "Ukrainian language link",
            "xpath",
            ".//*[@id='content']/div/a[2]");

    private String name;
    private String locatorsType;
    private String locator;
    private By byLocator;

    CommonLocators(String name, String locatorsType, String locator) {
        this.name = name;
        this.locatorsType = locatorsType;
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

    @Override
    public By getBy() {
        return this.byLocator;
    }

    public CommonLocators setByWithoutParameter() {
        if (this.locator.contains("%s")) {
            return null;
        } else {
            switch (this.locatorsType) {
                case ("className"):
                    this.byLocator = By.className(this.locator);
                    break;
                case ("cssSelector"):
                    this.byLocator = By.cssSelector(this.locator);
                    break;
                case ("id"):
                    this.byLocator = By.id(this.locator);
                    break;
                case ("name"):
                    this.byLocator = By.name(this.locator);
                    break;
                case ("tagName"):
                    this.byLocator = By.tagName(this.locator);
                    break;
                case ("xpath"):
                    this.byLocator = By.xpath(this.locator);
                    break;
                default:
                    System.out.println("Locator's type is incorrect.");
                    break;
            }
            return this;
        }
    }

    public CommonLocators setByWithParameter(String parameter) {
        if (this.locator.contains("%s")) {
            this.name = parameter;
            switch (this.locatorsType) {
                case ("className"):
                    this.byLocator = By.className(String.format(this.locator, parameter));
                    break;
                case ("cssSelector"):
                    this.byLocator = By.cssSelector(String.format(this.locator, parameter));
                    break;
                case ("id"):
                    this.byLocator = By.id(String.format(this.locator, parameter));
                    break;
                case ("name"):
                    this.byLocator = By.name(String.format(this.locator, parameter));
                    break;
                case ("tagName"):
                    this.byLocator = By.tagName(String.format(this.locator, parameter));
                    break;
                case ("xpath"):
                    this.byLocator = By.xpath(String.format(this.locator, parameter));
                    break;
                default:
                    System.out.println("Locator's type is incorrect.");
                    break;
            }
            return this;
        } else {
            return null;
        }
    }
}

