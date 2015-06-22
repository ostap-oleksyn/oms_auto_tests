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
            By.xpath(".//input[@id='next']")),
    LAST_BUTTON(
            "Last Button",
            By.xpath(".//*[@id='last']")),
    USERS_TABLE(
            "Users table",
            By.xpath(".//*[@id='table']")),
    USERS_TABLE_ROWS(
            "Users table rows",
            By.xpath(".//tbody/tr")),
    ADMINISTRATOR_APPOINTED_LABEL(
            "Admin appointed Info label",
            By.xpath(".//*[@id='list']/h2")),
    FILTER_LABEL(
            "Filter label",
            By.xpath(".//*[@id='searchForm']/label")),
    FOUND_USERS_NUMBER(
            "Number of found users",
            By.xpath(".//*[@id='usersFound']")),
    USERS_LIST_RESIZE_LINK(
            "Users list resize link",
            By.xpath(".//a[@href=\"resizeUsersList.htm\"]")),
    CURRENT_PAGE_NUMBER(
            "Current page number",
            By.xpath(".//*[@id='pageNumber']")),
    BACKWARD_BUTTON(
            "Backward button",
            By.xpath(".//*[@id='previous']")),
    FILTER_SELECT(
            "Filter select",
            By.xpath(".//fieldset/form/select[1]")),

    CONDITION_SELECT(
            "Condition select",
            By.xpath(".//fieldset/form/select[2]")),

    SHOW_10_ITEMS_LINK(
            "Show 10 items",
            By.xpath(".//*[@id='list']/p/a")),

    SEARCH_FIELD(
            "Search field",
            By.xpath(".//*[@id='searchField']")),

    SEARCH_BUTTON(
            "Search button",
            By.xpath(".//*[@id='searchForm']/input[2]"));

    // TODO redo to enum
    public static final String TABLE_COLUMN = ".//*[@id='table']/thead/tr/th/a[contains(text(), '%s')]";
    public static final String DELETE_LINK = ".//*[@id='table']/tbody/tr[%s]/td[7]/a";
    public static final String LOGIN_CELL = ".//*[@id='table']/tbody/tr[%s]/td[3]";

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
