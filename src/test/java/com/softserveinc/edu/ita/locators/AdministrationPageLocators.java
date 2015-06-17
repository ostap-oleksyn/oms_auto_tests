package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AdministrationPageLocators implements ILocator {

    CREATE_NEW_USER_LINK(
            "Create new user link",
            By.xpath(".//*[@id='list']/a[contains(text(), 'Create New User')]")),
    QUANTITY_OF_TABLE_PAGES(
            "Quantity of table pages",
            By.xpath(".//*[@id='pageCount']")),
    TABLE_ROWS(
            "Table rows",
            By.xpath(".//*[@id='table']//tr")),
    ROW_CELLS(
            "Row cells",
            By.tagName("td")),
    FIRST_BUTTON(
            "First button",
            By.xpath(".//*[@id='first']")),
    NEXT_BUTTON(
            "Next button",
            By.xpath(".//input[@id='next']"));
    public static final String TABLE_COLUMN = ".//*[@id='table']/thead/tr/th/a[contains(text(), '%s')]";

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
