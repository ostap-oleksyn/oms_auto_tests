package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * This enum includes two type of locators:
 * the first type locators are used without preliminary preparation;
 * the second type locators can be used after advance modification.
 */
public enum AdministrationPageLocators implements ILocator {

    CREATE_NEW_USER_LINK(
            "Create new user link",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='list']/a[contains(text(), 'Create New User')]"),
    QUANTITY_OF_TABLE_PAGES(
            "Quantity of table pages",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='pageCount']"),
    TABLE_ROWS(
            "Table rows",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='table']//tr"),
    ROW_CELLS(
            "Row cells",
            SeleniumByMethods.BY_TAG_NAME,
            "td"),
    FIRST_BUTTON(
            "First button",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='first']"),
    NEXT_BUTTON(
            "Next button",
            SeleniumByMethods.BY_XPATH,
            ".//input[@id='next']"),
    LAST_BUTTON(
            "Last Button",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='last']"),
    USERS_TABLE(
            "Users table",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='table']"),
    USERS_TABLE_ROWS(
            "Users table rows",
            SeleniumByMethods.BY_XPATH,
            ".//tbody/tr"),
    ADMINISTRATOR_APPOINTED_LABEL(
            "Admin appointed Info label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='list']/h2"),
    FILTER_LABEL(
            "Filter label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='searchForm']/label"),
    FOUND_USERS_NUMBER(
            "Number of found users",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='usersFound']"),
    USERS_LIST_RESIZE_LINK(
            "Users list resize link",
            SeleniumByMethods.BY_XPATH,
            ".//a[@href='resizeUsersList.htm']"),
    CURRENT_PAGE_NUMBER(
            "Current page number",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='pageNumber']"),
    BACKWARD_BUTTON(
            "Backward button",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='previous']"),
    FILTER_SELECT(
            "Filter select",
            SeleniumByMethods.BY_XPATH,
            ".//fieldset/form/select[1]"),
    CONDITION_SELECT(
            "Condition select",
            SeleniumByMethods.BY_XPATH,
            ".//fieldset/form/select[2]"),
    SEARCH_FIELD(
            "Search field",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='searchField']"),
    SEARCH_BUTTON(
            "Search button",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='searchForm']/input[2]"),
    TABLE_COLUMN(
            "",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='table']/thead/tr/th/a[contains(text(), '%s')]"),
    DELETE_LINK(
            "",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[7]/a"),
    LOGIN_CELL(
            "",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[3]"),
    SELECTED_FILTER(
            "Selected filter",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='field']//*[@selected='selected']"),
    SELECTED_CONDITION(
            "Selected condition",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='condition']//*[@selected='selected']");

    private String name;
    private SeleniumByMethods seleniumByMethod;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    AdministrationPageLocators(String name, SeleniumByMethods seleniumByMethod, String rawLocator) {
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
    public AdministrationPageLocators modify(String parameter) {
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