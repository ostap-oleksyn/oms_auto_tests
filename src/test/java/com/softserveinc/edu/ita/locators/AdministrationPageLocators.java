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
            ".//*[@id='table']/tbody/tr[%s]/td[3]");

    private String name;
    private String locatorsType;
    private String locator;
    private By byLocator;

    AdministrationPageLocators(String name, String locatorsType, String locator) {
        this.name = name;
        this.locatorsType = locatorsType;
        this.locator = locator;
    }

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
        return this.byLocator;
    }

    public AdministrationPageLocators setByWithoutParameter() {
        if (this.locator.contains("%s")) {
            return null;
        } else {
            switch (this.locatorsType) {
                case ("className"):
                    this.byLocator = By.className(this.locator);
                    break;
                case ("cssSelector"):
                    this.byLocator = By.cssSelector(this.locator);
                    break;
                case ("id"):
                    this.byLocator = By.id(this.locator);
                    break;
                case ("name"):
                    this.byLocator = By.name(this.locator);
                    break;
                case ("tagName"):
                    this.byLocator = By.tagName(this.locator);
                    break;
                case ("xpath"):
                    this.byLocator = By.xpath(this.locator);
                    break;
                default:
                    System.out.println("Locator's type is incorrect.");
                    break;
            }
            return this;
        }
    }

    public AdministrationPageLocators setByWithParameter(String parameter) {
        if (this.locator.contains("%s")) {
            this.name = parameter;
            switch (this.locatorsType) {
                case ("className"):
                    this.byLocator = By.className(String.format(this.locator, parameter));
                    break;
                case ("cssSelector"):
                    this.byLocator = By.cssSelector(String.format(this.locator, parameter));
                    break;
                case ("id"):
                    this.byLocator = By.id(String.format(this.locator, parameter));
                    break;
                case ("name"):
                    this.byLocator = By.name(String.format(this.locator, parameter));
                    break;
                case ("tagName"):
                    this.byLocator = By.tagName(String.format(this.locator, parameter));
                    break;
                case ("xpath"):
                    this.byLocator = By.xpath(String.format(this.locator, parameter));
                    break;
                default:
                    System.out.println("Locator's type is incorrect.");
                    break;
            }
            return this;
        } else {
            return null;
        }
    }
}