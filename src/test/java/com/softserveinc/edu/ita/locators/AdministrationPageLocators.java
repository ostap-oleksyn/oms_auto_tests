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
            LocatorsType.BY_XPATH,
            ".//a[@href='addUser.htm']"),
    QUANTITY_OF_TABLE_PAGES(
            "Quantity of table pages",
            LocatorsType.BY_XPATH,
            ".//*[@id='pageCount']"),
    TABLE_ROWS(
            "Table rows",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']//tr"),
    ROW_CELLS(
            "Row cells",
            LocatorsType.BY_TAG_NAME,
            "td"),
    EDIT_USER_LINK(
            "Edit user link",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[6]/a"),
    FIRST_BUTTON(
            "First button",
            LocatorsType.BY_XPATH,
            ".//*[@id='first']"),
    NEXT_BUTTON(
            "Next button",
            LocatorsType.BY_XPATH,
            ".//input[@id='next']"),
    LAST_BUTTON(
            "Last Button",
            LocatorsType.BY_XPATH,
            ".//*[@id='last']"),
    USERS_TABLE(
            "Users table",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']"),
    USERS_TABLE_ROWS(
            "Users table rows",
            LocatorsType.BY_XPATH,
            ".//tbody/tr"),
    ADMINISTRATOR_APPOINTED_LABEL(
            "Admin appointed Info label",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/h2"),
    FILTER_LABEL(
            "Filter label",
            LocatorsType.BY_XPATH,
            ".//*[@id='searchForm']/label"),
    FOUND_USERS_NUMBER(
            "Number of found users",
            LocatorsType.BY_XPATH,
            ".//*[@id='usersFound']"),
    USERS_LIST_RESIZE_LINK(
            "Users list resize link",
            LocatorsType.BY_XPATH,
            ".//a[@href='resizeUsersList.htm']"),
    CURRENT_PAGE_NUMBER(
            "Current page number",
            LocatorsType.BY_XPATH,
            ".//*[@id='pageNumber']"),
    BACKWARD_BUTTON(
            "Backward button",
            LocatorsType.BY_XPATH,
            ".//*[@id='previous']"),
    FILTER_SELECT(
            "Filter select",
            LocatorsType.BY_XPATH,
            ".//fieldset/form/select[1]"),
    CONDITION_SELECT(
            "Condition select",
            LocatorsType.BY_XPATH,
            ".//fieldset/form/select[2]"),
    SEARCH_FIELD(
            "Search field",
            LocatorsType.BY_XPATH,
            ".//*[@id='searchField']"),
    SEARCH_BUTTON(
            "Search button",
            LocatorsType.BY_XPATH,
            ".//*[@id='searchForm']/input[2]"),
    CREATE_REPORT_LINK(
            "Create report link",
            LocatorsType.BY_XPATH,
            ".//a[@href='reportUsers.htm']"),
    SAVE_REPORT_LINK(
            "Save report link",
            LocatorsType.BY_XPATH,
            ".//a[@href='getReport.htm']"),
    TABLE_COLUMN(
            "Table column",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/thead/tr/th/a[contains(text(), '%s')]"),
    DELETE_LINK(
            "Delete link",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[7]/a"),
    LOGIN_CELL(
            "Login cell",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[3]"),
    SELECTED_FILTER(
            "Selected filter",
            LocatorsType.BY_XPATH,
            ".//*[@id='field']//*[@selected='selected']"),
    SELECTED_CONDITION(
            "Selected condition",
            LocatorsType.BY_XPATH,
            ".//*[@id='condition']//*[@selected='selected']");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    AdministrationPageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
        this.name = name;
        this.locatorsType = locatorsType;
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

    /**
     * This method prepares locator using additional parameter
     * by means of so called "string-format" method.
     */
    public AdministrationPageLocators modify(final String parameter) {
        this.modifiedLocator = String.format(this.rawLocator, parameter);
        return this;
    }

    @Override
    //This method converts locator into "By" format.
    public By getBy() {
        //This block of code is used to leave raw locator intact giving a possibility to use parameterized locator again.
        if (this.modifiedLocator == null) {
            this.byLocator = this.locatorsType.getBy(this.rawLocator);
        } else {
            this.byLocator = this.locatorsType.getBy(this.modifiedLocator);
        }
        return this.byLocator;
    }
}
