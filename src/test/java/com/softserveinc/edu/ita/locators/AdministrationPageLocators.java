package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AdministrationPageLocators implements ILocator {

    CREATE_NEW_USER_LINK(
            "Create new user link",
            "xpath",
            ".//*[@id='list']/a[contains(text(), 'Create New User')]"),
    QUANTITY_OF_TABLE_PAGES(
            "Quantity of table pages",
            "xpath",
            ".//*[@id='pageCount']"),
    TABLE_ROWS(
            "Table rows",
            "xpath",
            ".//*[@id='table']//tr"),
    ROW_CELLS(
            "Row cells",
            "tagName",
            "td"),
    FIRST_BUTTON(
            "First button",
            "xpath",
            ".//*[@id='first']"),
    NEXT_BUTTON(
            "Next button",
            "xpath",
            ".//input[@id='next']"),
    LAST_BUTTON(
            "Last Button",
            "xpath",
            ".//*[@id='last']"),
    USERS_TABLE(
            "Users table",
            "xpath",
            ".//*[@id='table']"),
    USERS_TABLE_ROWS(
            "Users table rows",
            "xpath",
            ".//tbody/tr"),
    ADMINISTRATOR_APPOINTED_LABEL(
            "Admin appointed Info label",
            "xpath",
            ".//*[@id='list']/h2"),
    FILTER_LABEL(
            "Filter label",
            "xpath",
            ".//*[@id='searchForm']/label"),
    FOUND_USERS_NUMBER(
            "Number of found users",
            "xpath",
            ".//*[@id='usersFound']"),
    USERS_LIST_RESIZE_LINK(
            "Users list resize link",
            "xpath",
            ".//a[@href='resizeUsersList.htm']"),
    CURRENT_PAGE_NUMBER(
            "Current page number",
            "xpath",
            ".//*[@id='pageNumber']"),
    BACKWARD_BUTTON(
            "Backward button",
            "xpath",
            ".//*[@id='previous']"),
    FILTER_SELECT(
            "Filter select",
            "xpath",
            ".//fieldset/form/select[1]"),

    CONDITION_SELECT(
            "Condition select",
            "xpath",
            ".//fieldset/form/select[2]"),

    SEARCH_FIELD(
            "Search field",
            "xpath",
            ".//*[@id='searchField']"),

    SEARCH_BUTTON(
            "Search button",
            "xpath",
            ".//*[@id='searchForm']/input[2]"),

    TABLE_COLUMN(
            "",
            "xpath",
            ".//*[@id='table']/thead/tr/th/a[contains(text(), '%s')]"),

    DELETE_LINK(
            "",
            "xpath",
            ".//*[@id='table']/tbody/tr[%s]/td[7]/a"),

    LOGIN_CELL(
            "",
            "xpath",
            ".//*[@id='table']/tbody/tr[%s]/td[3]"),

    SELECTED_FILTER(
            "Selected filter",
            "xpath",
            ".//*[@id='field']//*[@selected='selected']"),

    SELECTED_CONDITION(
            "Selected condition",
            "xpath",
            ".//*[@id='condition']//*[@selected='selected']");

    private String name;
    private String locatorsType;
    private String rowLocator;
    private String modifiedLocator;
    private By byLocator;

    AdministrationPageLocators(String name, String locatorsType, String rowLocator) {
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

    public AdministrationPageLocators modify(String parameter) {
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