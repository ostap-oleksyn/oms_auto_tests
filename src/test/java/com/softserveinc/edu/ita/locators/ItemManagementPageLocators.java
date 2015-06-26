package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum ItemManagementPageLocators implements ILocator {
    //TODO refactor into not using text label inside the locator
    ADD_PRODUCT_LINK(
            "Add product link",
            "xpath",
            ".//*[@id='list']/a[contains(text(), 'Add Product')]"),
    SUPERVISOR_APPOINTED_LABEL(
            "Supervisor appointed Info label",
            "xpath",
            ".//*[@id='list']/h2"),
    SUPERVISOR_FILTER_LABEL(
            "Filter label",
            "xpath",
            ".//*[@id='searchForm']/label"),
    SELECTED_FILTER(
            "Selected filter",
            "xpath",
            ".//*[@id='field']//option[@selected='selected']"),
    SEARCH_FIELD(
            "Search field",
            "xpath",
            ".//*[@id='searchField']");

    private String name;
    private String locatorsType;
    private String rowLocator;
    private String modifiedLocator;
    private By byLocator;

    ItemManagementPageLocators(String name, String locatorsType, String rowLocator) {
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

    public ItemManagementPageLocators modify(String parameter) {
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
