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
            LocatorsType.BY_XPATH,
            ".//*[@id='content']/a[contains(text(), 'Create new order')]"),
    QUANTITY_OF_ROWS(
            "Quantity of rows",
            LocatorsType.BY_XPATH,
            ".//*[@id='allFoundAndFiltered']"),
    QUANTITY_OF_ROWS_PER_PAGE(
            "Quantity of rows per page",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr/td[1]"),
    CLICK_NEXT_BUTTON(
            "Next button",
            LocatorsType.BY_XPATH,
            ".//*[@name='nextPage']"),
    CLICK_FIRST_BUTTON(
            "First button",
            LocatorsType.BY_XPATH,
            ".//*[@name='firstPage']"),
    SEARCH_FILTER_LABEL(
            "Search filter title label",
            LocatorsType.BY_XPATH,
            "//tr[2]/td[1]"),
    EDIT_TABLE_LABEL(
            "Edit table label",
            LocatorsType.BY_XPATH,
            "//tr[1]/th[8]"),
    FILTER_SELECT(
            "Filter select",
            LocatorsType.BY_XPATH,
            ".//*[@id='filterBy']"),
    FILTER_VALUE_SELECT(
            "Filter value select",
            LocatorsType.BY_XPATH,
            ".//*[@id='filterValue']"),
    SEARCH_CONDITION_SELECT(
            "Search condition select",
            LocatorsType.BY_XPATH,
            ".//*[@id='search']"),
    SEARCH_FIELD(
            "Search field",
            LocatorsType.BY_XPATH,
            ".//*[@id='searchValue']"),
    SELECTED_FILTER(
            "Selected filter",
            LocatorsType.BY_XPATH,
            ".//*[@id='filterBy']//*[@selected='selected']"),
    SELECTED_FILTER_VALUE(
            "Selected filter value",
            LocatorsType.BY_XPATH,
            ".//*[@id='filterValue']//*[@selected='selected']"),
    SELECTED_SEARCH_CONDITION(
            "Selected search condition",
            LocatorsType.BY_XPATH,
            ".//*[@id='search']//*[@selected='selected']"),
    APPLY_BUTTON(
            "Apply button",
            LocatorsType.BY_XPATH,
            ".//input[@name='Apply']"),
    TABLE_ROW_CELL(
            "Table row cell",
            LocatorsType.BY_XPATH,
            ".//div[@id='list']/table/tbody/tr[%s]/td[1]"),
    TABLE_ROW(
            "Table row",
            LocatorsType.BY_XPATH,
            ".//div[@id='list']/table/tbody/tr[%s]/td"),
    TABLE_COLUMN(
            "Table column",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr[1]/th/a[contains(text(), '%s')]"),
    ORDER_NAME_COLUMN(
            "Order name",
            LocatorsType.BY_XPATH,
            "//div[2]/table/tbody/tr/td[1]"),
    ORDER_STATUS_COLUMN(
            "Order status",
            LocatorsType.BY_XPATH,
            "//div[2]/table/tbody/tr/td[5]"),
    ORDER_ASSIGNEE_COLUMN(
            "Order assignee",
            LocatorsType.BY_XPATH,
            "//div[2]/table/tbody/tr/td[6]"),
    ROLE_COLUMN(
            "Role",
            LocatorsType.BY_XPATH,
            "//div[2]/table/tbody/tr/td[7]"),
    EDIT_LINK(
            "Edit link",
            LocatorsType.BY_XPATH,
            "//tr[2]/td[8]/a"),
    SHOW_NUMBER_OF_ELEMENTS_LINK(
            "Show number of elements link",
            LocatorsType.BY_XPATH,
            "//div[1]/select"),
    PRICE_COLUMN(
            "Price column",
            LocatorsType.BY_XPATH,
            "//table/tbody/tr/td[5]"),
    QUANTITY_COLUMN(
            "Quantity column",
            LocatorsType.BY_XPATH,
            "//table/tbody/tr/td[6]"),
    PRICE_PER_LINE(
            "Price per line",
            LocatorsType.BY_XPATH,
            "//table/tbody/tr/td[7]"),
    ITEM_FIRST_PAGE_BUTTON(
            "Item first page button",
            LocatorsType.BY_XPATH,
            ".//*[@id='pageable']//form[1]/input"),
    ITEM_PREVIOUS_PAGE_BUTTON(
            "Item previous page button",
            LocatorsType.BY_XPATH,
            ".//*[@id='pageable']//form[2]/input"),
    ITEM_NEXT_PAGE_BUTTON(
            "Item next page button",
            LocatorsType.BY_XPATH,
            ".//*[@id='pageable']//form[3]/input"),
    ITEM_LAST_PAGE_BUTTON(
            "Item next page button",
            LocatorsType.BY_XPATH,
            ".//*[@id='pageable']//form[4]/input"),
    ITEMS_TABLE(
            "Items table",
            LocatorsType.BY_XPATH,
            "//div[2]/table"),
    ITEM_ORDER_STATUS(
            "Item order status",
            LocatorsType.BY_XPATH,
            "//tbody/tr[1]/td[2]/select"),
    ITEM_SAVE_BUTTON(
            "Item save button",
            LocatorsType.BY_XPATH,
            "//div[2]/div/form[1]/input"),
    ITEM_CANCEL_BUTTON(
            "Item cancel button",
            LocatorsType.BY_XPATH,
            "//div[2]/div/form[2]/input"),
    ORDER_TABLE(
            "Order table",
            LocatorsType.BY_XPATH,
            "//div[2]/table"),
    ITEM_ERROR_MESSAGE(
            "Item error message",
            LocatorsType.BY_XPATH,
            "//div[3]//p"),
    ITEM_ERROR_SHOW_BUTTON(
            "Item error show button",
            LocatorsType.BY_XPATH,
            "//div[1]/input"),
    ITEM_GO_TO_HOME_BUTTON(
            "Item go to home button",
            LocatorsType.BY_XPATH,
            "//table[2]//input"),
    ITEM_DETAILS_ABOUT_ERROR(
            "Item details about error",
            LocatorsType.BY_XPATH,
            "//div[2]/div"),
    DELETE_ORDER_BUTTON(
            "Delete order button",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr[2]/td[9]/a"),
    FIRST_ORDER(
            "First order in the table",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr[2]/td[1]");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    OrderingPageLocators(String name, LocatorsType locatorsType, String rawLocator) {
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

    //This method prepares locator using additional parameter by means of so called "string-format" method.
    public OrderingPageLocators modify(String parameter) {
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
