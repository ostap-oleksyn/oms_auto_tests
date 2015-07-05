package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AddItemPageLocators implements ILocator {

    ITEMS_TABLE_ROWS(
            "Items table rows",
            By.xpath(".//*[@id='list']/table/tbody/tr")),
    ITEMS_ROW_CELL(
            "Items row cell",
            By.xpath("td")),
    QUANTITY_INPUT(
            "Quantity input",
            By.xpath(".//*[@id='doneForm']//input[@id='quantity']")),
    SEARCH_METHOD_SELECT(
            "Search by select",
            By.xpath(".//select[@id='searchProperty']")),
    DIMENSION_SELECT(
            "Dimension select",
            By.xpath(".//*[@id='doneForm']//select[@id='dimension']")),
    DONE_BUTTON(
            "Done button",
            By.xpath(".//*[@id='content']//input[@type='submit'][@value='Done']")),
    SEARCH_INPUT(
            "Search input",
            By.xpath(".//input[@id='searchValue']")),
    SEARCH_BUTTON(
            "Search button",
            By.xpath(".//input[@value='Search']")),
    SORT_BY_NAME_HEADER_LINK(
            "Sort by name header link",
            By.xpath(".//*[@id='sortName']/a")),
    SORT_BY_DESCRIPTION_HEADER_LINK(
            "Sort by description header link",
            By.xpath(".//*[@id='sortDescription']/a"));

    AddItemPageLocators(String name, By locator) {
        this.name = name;
        this.locator = locator;
    }

    final public static String SELECT_ITEM_LINK = ".//*[@id='list']//tbody/tr[%s]/td[3]//a";

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
