package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * This enum includes two type of locators:
 * the first type locators are used without preliminary preparation;
 * the second type locators can be used after advance modification.
 */
public enum ItemManagementPageLocators implements ILocator {
    //TODO refactor into not using text label inside the locator
    ADD_PRODUCT_LINK(
            "Add product link",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/a[contains(text(), 'Add Product')]"),
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
            ".//*[@id='table']/tbody/tr/td[%s]");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    ItemManagementPageLocators(String name, LocatorsType locatorsType, String rawLocator) {
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
    public ItemManagementPageLocators modify(String parameter) {
        this.name = parameter;
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
