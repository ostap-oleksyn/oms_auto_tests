package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum OrderingPageLocators implements ILocator {

    CREATE_NEW_ORDER_LINK(
            "Create new order link",
            By.xpath(".//*[@id='content']/a[contains(text(), 'Create new order')]")),
    QUANTITY_OF_ROWS(
            "Quantity of rows",
            By.xpath(".//*[@id='allFoundAndFiltered']")),
    QUANTITY_OF_ROWS_PER_PAGE(
            "Quantity of rows per page",
            By.xpath(".//*[@id='list']/table/tbody/tr/td[1]")),
    CLICK_NEXT_BUTTON(
            "Next button",
            By.xpath(".//*[@name='nextPage']")),
    CLICK_FIRST_BUTTON(
            "First button",
            By.xpath(".//*[@name='firstPage']")),
    SEARCH_FILTER_LABEL(
            "Search filter title label",
            By.xpath("//tr[2]/td[1]")),
    EDIT_TABLE_LABEL(
            "Edit table label",
            By.xpath("//tr[1]/th[8]")),;

    // TODO redo to enum
    public static final String TABLE_ROW_CELL = ".//div[@id='list']/table/tbody/tr[%s]/td[1]";
    public static final String TABLE_ROW = ".//div[@id='list']/table/tbody/tr[%s]/td";
    public static final String TABLE_COLUMN = ".//*[@id='list']/table/tbody/tr[1]/th/a[contains(text(), '%s')]";

    OrderingPageLocators(String name, By locator) {
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
