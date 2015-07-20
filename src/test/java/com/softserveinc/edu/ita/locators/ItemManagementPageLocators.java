package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.locators.interfaces.ILocator;
import org.openqa.selenium.By;

public enum ItemManagementPageLocators implements ILocator {

    ADD_PRODUCT_LINK(
            "Add product link",
            LocatorsType.BY_XPATH,
            ".//a[@href='addItem.htm']"),
    SUPERVISOR_APPOINTED_LABEL(
            "Supervisor appointed Info label",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/h2"),
    SUPERVISOR_FILTER_LABEL(
            "Filter label",
            LocatorsType.BY_XPATH,
            ".//*[@id='searchForm']/label"),
    SELECTED_FILTER(
            "Selected filter",
            LocatorsType.BY_XPATH,
            ".//*[@id='field']//option[@selected='selected']"),
    SEARCH_FIELD(
            "Search field",
            LocatorsType.BY_XPATH,
            ".//*[@id='searchField']"),
    FOUND_PRODUCTS_NUMBER(
            "Found products number",
            LocatorsType.BY_XPATH,
            ".//*[@id='recordsFound']"),
    LAST_BUTTON(
            "Last Button",
            LocatorsType.BY_XPATH,
            ".//*[@id='last']"),
    LAST_PRODUCT_NAME(
            "Last product name",
            LocatorsType.BY_XPATH,
            ".//tbody//tr[last()]//td[1]"),
    LAST_PRODUCT_DESCRIPTION(
            "Last product description",
            LocatorsType.BY_XPATH,
            ".//tbody//tr[last()]//td[2]"),
    LAST_PRODUCT_PRICE(
            "Last product price",
            LocatorsType.BY_XPATH,
            ".//tbody//tr[last()]//td[3]"),
    TABLE_ROW(
            "Products table row",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr"),
    TABLE_NAME_CELL(
            "Products table name cell",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[1]"),
    TABLE_DESCRIPTION_CELL(
            "Products table description cell",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[2]"),
    TABLE_PRICE_CELL(
            "Products table price cell",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[3]"),
    TABLE_EDIT_LINK(
            "Products table edit link",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[4]/a"),
    TABLE_DELETE_LINK(
            "Products table delete link",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr[%s]/td[5]/a"),
    CREATE_REPORT_LINK(
            "Create report link",
            LocatorsType.BY_XPATH,
            ".//*[@href='reportItems.htm']"),
    SAVE_REPORT_LINK(
            "Save report link",
            LocatorsType.BY_XPATH,
            ".//*[@href='getItemReport.htm']"),
    TABLE_COLUMN(
            "Table column",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/thead/tr/th/a[contains(text(), '%s')]"),
    PRODUCTS_LIST_RESIZE_LINK(
            "Products list resize link",
            LocatorsType.BY_XPATH,
            ".//*[@href='itemManagement/resizeItemList.htm']"),
    COLUMNS_ELEMENTS(
            "Column's elements",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr/td[%s]"),
    PRODUCTS_TABLE_CELL(
            "Product's table cell",
            LocatorsType.BY_XPATH,
            ".//td[%s][contains(text(), '%s')]"),
    SEARCH_CONDITION(
            "Search condition",
            LocatorsType.BY_XPATH,
            ".//option[%s]"),
    TABLES_GRID(
            "Table's grid",
            LocatorsType.BY_XPATH,
            ".//*[@id='table']/tbody/tr/td"),
    CURRENT_PAGE_NUMBER(
            "Current page number",
            LocatorsType.BY_XPATH,
            ".//*[@id='pageNumber']"),
    PREVIOUS_BUTTON(
            "Previous button",
            LocatorsType.BY_XPATH,
            ".//*[@id='previous']"),
    FIRST_BUTTON(
            "First button",
            LocatorsType.BY_XPATH,
            ".//*[@id='first']"),
    QUANTITY_OF_TABLE_PAGES(
            "Quantity of table pages",
            LocatorsType.BY_XPATH,
            ".//*[@id='pageCount']"),
    NEXT_BUTTON(
            "Next button",
            LocatorsType.BY_XPATH,
            ".//input[@id='next']"),
    TABLE_CELLS_WITH_TEXT(
            "Table cells with text",
            LocatorsType.BY_XPATH,
            ".//table/tbody/tr/td[contains(text(), '%s')]");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    ItemManagementPageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
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
     * Modifies the locator by inserting the given string.
     *
     * @param parameter - modifier that will be inserted into the locator.
     */
    public ItemManagementPageLocators modify(final String parameter) {
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
