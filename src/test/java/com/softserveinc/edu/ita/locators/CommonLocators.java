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
    private String rowLocator;
    private String modifiedLocator;
    private By byLocator;

    CommonLocators(String name, String locatorsType, String rowLocator) {
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

    public CommonLocators modify(String parameter) {
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

