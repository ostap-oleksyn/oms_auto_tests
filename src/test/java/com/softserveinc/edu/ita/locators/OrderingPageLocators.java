package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * This enum includes two type of locators:
 * the first type locators are used without preliminary preparation;
 * the second type locators can be used after advance modification.
 */
public enum OrderingPageLocators implements ILocator {

    CREATE_NEW_ORDER_LINK(
            "Create new order link",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='content']/a[contains(text(), 'Create new order')]"),
    QUANTITY_OF_ROWS(
            "Quantity of rows",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='allFoundAndFiltered']"),
    QUANTITY_OF_ROWS_PER_PAGE(
            "Quantity of rows per page",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr/td[1]"),
    CLICK_NEXT_BUTTON(
            "Next button",
            SeleniumByMethods.BY_XPATH,
            ".//*[@name='nextPage']"),
    CLICK_FIRST_BUTTON(
            "First button",
            SeleniumByMethods.BY_XPATH,
            ".//*[@name='firstPage']"),
    SEARCH_FILTER_LABEL(
            "Search filter title label",
            SeleniumByMethods.BY_XPATH,
            "//tr[2]/td[1]"),
    EDIT_TABLE_LABEL(
            "Edit table label",
            SeleniumByMethods.BY_XPATH,
            "//tr[1]/th[8]"),
    FILTER_SELECT(
            "Filter select",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='filterBy']"),
    FILTER_VALUE_SELECT(
            "Filter value select",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='filterValue']"),
    SEARCH_CONDITION_SELECT(
            "Search condition select",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='search']"),
    SEARCH_FIELD(
            "Search field",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='searchValue']"),
    SELECTED_FILTER(
            "Selected filter",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='filterBy']//*[@selected='selected']"),
    SELECTED_FILTER_VALUE(
            "Selected filter value",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='filterValue']//*[@selected='selected']"),
    SELECTED_SEARCH_CONDITION(
            "Selected search condition",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='search']//*[@selected='selected']"),
    APPLY_BUTTON(
            "Apply button",
            SeleniumByMethods.BY_XPATH,
            ".//input[@name='Apply']"),
    TABLE_ROW_CELL(
            "",
            SeleniumByMethods.BY_XPATH,
            ".//div[@id='list']/table/tbody/tr[%s]/td[1]"),
    TABLE_ROW(
            "",
            SeleniumByMethods.BY_XPATH,
            ".//div[@id='list']/table/tbody/tr[%s]/td"),
    TABLE_COLUMN(
            "",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr[1]/th/a[contains(text(), '%s')]");

    private String name;
    private SeleniumByMethods seleniumByMethod;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    OrderingPageLocators(String name, SeleniumByMethods seleniumByMethod, String rawLocator) {
        this.name = name;
        this.seleniumByMethod = seleniumByMethod;
        this.rawLocator = rawLocator;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //This method prepares locator using additional parameter by means of so called "string-format" method.
    public OrderingPageLocators modify(String parameter) {
        this.name = parameter;
        this.modifiedLocator = String.format(this.rawLocator, parameter);
        return this;
    }

    @Override
    //This method converts locator into "By" format.
    public By getBy() {
        //This block of code is used to leave raw locator intact giving a possibility to use parameterized locator again.
        if (this.modifiedLocator == null) {
            this.byLocator = this.seleniumByMethod.getBy(this.rawLocator);
        } else {
            this.byLocator = this.seleniumByMethod.getBy(this.modifiedLocator);
        }
        return this.byLocator;
    }
}
