package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum CommonLocators implements ILocator {

    USER_INFO_TAB(
            "User info tab",
            By.xpath(".//*[@id='nav']//a[contains(text(), 'User Info')]")),
    ITEM_MANAGEMENT_TAB(
            "Item management tab",
            By.xpath(".//*[@id='nav']//a[contains(text(), 'Item Management')]")),
    ADMINISTRATION_TAB(
            "Administration tab",
            By.xpath(".//*[@id='nav']//a[contains(text(), 'Administration')]")),
    ORDERING_TAB(
            "Ordering tab",
            By.xpath(".//*[@id='nav']//a[contains(text(), 'Ordering')]")),
    LOG_OUT_BUTTON(
            "Logout button",
            By.xpath("//img[@alt='logout']")),
    ACTIVE_TAB(
            "Active tab",
            By.xpath(".//a[parent::li[@class='cur']]")),
    TABLE_LABEL(
            "common table label",
            By.xpath(".//tbody/tr")
    );

    CommonLocators(String name, By locator) {
        this.name = name;
        this.locator = locator;
    }

    private String name;
    private By locator;

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
        return this.locator;
    }
}
