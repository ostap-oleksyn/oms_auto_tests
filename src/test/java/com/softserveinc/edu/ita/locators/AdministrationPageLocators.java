package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AdministrationPageLocators implements ILocator {

    CREATE_NEW_USER_LINK(
            "Create new user link",
            By.xpath(".//*[@id='list']/a[contains(text(), 'Create New User')]")),
    ADMINISTRATOR_APPOINTED_TEXT(
            "Admin appointed Info text",
            By.xpath(".//*[@id='list']/h2")),
    FIELD_FILTER_TEXT(
            "Field filter text",
            By.xpath(".//*[@id='searchForm']/label"));

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
