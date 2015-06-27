package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * This enum includes two type of locators:
 * the first type locators are used without preliminary preparation;
 * the second type locators can be used after advance modification.
 */
public enum CommonLocators implements ILocator {

    USER_INFO_TAB(
            "User info tab",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='nav']//a[@href='/OMS/userInfo.htm']"),
    ITEM_MANAGEMENT_TAB(
            "Item management tab",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='nav']//a[@href='/OMS/itemManagement.htm']"),
    ADMINISTRATION_TAB(
            "Administration tab",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='nav']//a[@href='/OMS/users.htm']"),
    ORDERING_TAB(
            "Ordering tab",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='nav']//a[@href='/OMS/order.htm']"),
    LOG_OUT_BUTTON(
            "Logout button",
            SeleniumByMethods.BY_XPATH,
            "//img[@alt='logout']"),
    ACTIVE_TAB(
            "Active tab",
            SeleniumByMethods.BY_XPATH,
            ".//a[parent::li[@class='cur']]"),
    EN_LINK(
            "English language link",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='content']/div/a[1]"),
    UA_LINK(
            "Ukrainian language link",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='content']/div/a[2]");

    private String name;
    private SeleniumByMethods seleniumByMethod;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    CommonLocators(String name, SeleniumByMethods seleniumByMethod, String rawLocator) {
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
    public CommonLocators modify(String parameter) {
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