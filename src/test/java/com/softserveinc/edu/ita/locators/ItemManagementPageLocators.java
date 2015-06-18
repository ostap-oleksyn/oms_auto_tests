package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum ItemManagementPageLocators implements ILocator {

    ADD_PRODUCT_LINK(
            "Add product link",
            By.xpath(".//*[@id='list']/a[contains(text(), 'Add Product')]")),
    SUPERVISOR_APPOINTED_TEXT(
            "Supervisor appointed Info text",
            By.xpath(".//*[@id='list']/h2")),
    SUPERVISOR_FIELD_FILTER_TEXT(
            "Field filter text",
            By.xpath(".//*[@id='searchForm']/label"));

    ItemManagementPageLocators(String name, By locator) {
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
