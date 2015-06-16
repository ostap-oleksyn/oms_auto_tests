package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AdministrationPageLocators implements ILocator {

    CREATE_NEW_USER_LINK(
            "Create new user link",
            By.xpath(".//*[@id='list']/a[contains(text(), 'Create New User')]")),

    USERS_TABLE(
            "Users table",
            By.xpath(".//*[@id='table']")),

    USERS_TABLE_ROWS(
            "Users table rows",
            By.xpath(".//tbody/tr")),

    LAST_BUTTON(
            "Last Button",
            By.xpath(".//*[@id='last']"));

    public static final String DELETE_LINK = ".//*[@id='table']/tbody/tr[%s]/td[7]/a";
    public static final String LOGIN_CELL = ".//*[@id='table']/tbody/tr[%s]/td[3]";

    AdministrationPageLocators(String name, By locator) {
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
