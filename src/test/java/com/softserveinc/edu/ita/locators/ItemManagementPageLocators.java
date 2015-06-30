package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum ItemManagementPageLocators implements ILocator {
    //TODO refactor into not using text label inside the locator
    ADD_PRODUCT_LINK(
            "Add product link",
            By.xpath(".//*[@id='list']/a[contains(text(), 'Add Product')]")),
    SUPERVISOR_APPOINTED_LABEL(
            "Supervisor appointed Info label",
            By.xpath(".//*[@id='list']/h2")),
    SUPERVISOR_FILTER_LABEL(
            "Filter label",
            By.xpath(".//*[@id='searchForm']/label")),
    SELECTED_FILTER(
            "Selected filter",
            By.xpath(".//*[@id='field']//option[@selected='selected']")),
    SEARCH_FIELD(
            "Search field",
            By.xpath(".//*[@id='searchField']")),
    FOUND_PRODUCTS_NUMBER(
            "Found products number",
            By.xpath(".//*[@id='recordsFound']")),
    LAST_BUTTON(
            "Last Button",
            By.xpath(".//*[@id='last']")),
    LAST_PRODUCT_NAME(
            "Last product name",
            By.xpath(".//tbody//tr[last()]//td[1]")),
    LAST_PRODUCT_DESCRIPTION(
            "Last product description",
            By.xpath(".//tbody//tr[last()]//td[2]")),
    LAST_PRODUCT_PRICE(
            "Last product price",
            By.xpath(".//tbody//tr[last()]//td[3]"))
    ;

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
