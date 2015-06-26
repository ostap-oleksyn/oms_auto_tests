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
            By.xpath("//tr[1]/th[8]")),
    FILTER_SELECT(
            "Filter select",
            By.xpath(".//*[@id='filterBy']")),
    FILTER_VALUE_SELECT(
            "Filter value select",
            By.xpath(".//*[@id='filterValue']")),
    SEARCH_CONDITION_SELECT(
            "Search condition select",
            By.xpath(".//*[@id='search']")),
    SEARCH_FIELD(
            "Search field",
            By.xpath(".//*[@id='searchValue']")),
    SELECTED_FILTER(
            "Selected filter",
            By.xpath(".//*[@id='filterBy']//*[@selected='selected']")),
    SELECTED_FILTER_VALUE(
            "Selected filter value",
            By.xpath(".//*[@id='filterValue']//*[@selected='selected']")),
    SELECTED_SEARCH_CONDITION(
            "Selected search condition",
            By.xpath(".//*[@id='search']//*[@selected='selected']")),
    APPLY_BUTTON(
            "Apply button",
            By.xpath(".//input[@name='Apply']")),
    ORDER_NAME_COLUMN(
            "Order name",
            By.xpath("//div[2]/table/tbody/tr/td[1]")),
    ORDER_STATUS_COLUMN(
            "Order status",
            By.xpath("//div[2]/table/tbody/tr/td[5]")),
    ORDER_ASSIGNEE_COLUMN(
            "Order assignee",
            By.xpath("//div[2]/table/tbody/tr/td[6]")),
    ROLE_COLUMN(
            "Role",
            By.xpath("//div[2]/table/tbody/tr/td[7]"));

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
