package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum OrderingPageLocators implements ILocator {

    CREATE_NEW_ORDER_LINK(
            "Create new order link",
            "xapth",
            ".//*[@id='content']/a[contains(text(), 'Create new order')]"),
    QUANTITY_OF_ROWS(
            "Quantity of rows",
            "xpath",
            ".//*[@id='allFoundAndFiltered']"),
    QUANTITY_OF_ROWS_PER_PAGE(
            "Quantity of rows per page",
            "xpath",
            ".//*[@id='list']/table/tbody/tr/td[1]"),
    CLICK_NEXT_BUTTON(
            "Next button",
            "xpath",
            ".//*[@name='nextPage']"),
    CLICK_FIRST_BUTTON(
            "First button",
            "xpath",
            ".//*[@name='firstPage']"),
    SEARCH_FILTER_LABEL(
            "Search filter title label",
            "xpath",
            "//tr[2]/td[1]"),
    EDIT_TABLE_LABEL(
            "Edit table label",
            "xpath",
            "//tr[1]/th[8]"),
    FILTER_SELECT(
            "Filter select",
            "xpath",
            ".//*[@id='filterBy']"),
    FILTER_VALUE_SELECT(
            "Filter value select",
            "xpath",
            ".//*[@id='filterValue']"),
    SEARCH_CONDITION_SELECT(
            "Search condition select",
            "xpath",
            ".//*[@id='search']"),
    SEARCH_FIELD(
            "Search field",
            "xpath",
            ".//*[@id='searchValue']"),
    SELECTED_FILTER(
            "Selected filter",
            "xpath",
            ".//*[@id='filterBy']//*[@selected='selected']"),
    SELECTED_FILTER_VALUE(
            "Selected filter value",
            "xpath",
            ".//*[@id='filterValue']//*[@selected='selected']"),
    SELECTED_SEARCH_CONDITION(
            "Selected search condition",
            "xpath",
            ".//*[@id='search']//*[@selected='selected']"),
    APPLY_BUTTON(
            "Apply button",
            "xpath",
            ".//input[@name='Apply']"),
    TABLE_ROW_CELL(
            "",
            "xpath",
            ".//div[@id='list']/table/tbody/tr[%s]/td[1]"),
    TABLE_ROW(
            "",
            "xpath",
            ".//div[@id='list']/table/tbody/tr[%s]/td"),
    TABLE_COLUMN(
            "",
            "xpath",
            ".//*[@id='list']/table/tbody/tr[1]/th/a[contains(text(), '%s')]");

    private String name;
    private String locatorsType;
    private String rowLocator;
    private String modifiedLocator;
    private By byLocator;

    OrderingPageLocators(String name, String locatorsType, String rowLocator) {
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

    public OrderingPageLocators modify(String parameter) {
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
