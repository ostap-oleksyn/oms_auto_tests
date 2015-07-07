package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AddItemPageLocators implements ILocator {

    ITEMS_TABLE_ROWS(
            "Items table rows",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr"),
    ITEMS_ROW_CELL(
            "Items row cell",
            LocatorsType.BY_XPATH,
            "td"),
    QUANTITY_INPUT(
            "Quantity input",
            LocatorsType.BY_XPATH,
            ".//*[@id='doneForm']//input[@id='quantity']"),
    SEARCH_CONDITION_SELECT(
            "Search by select",
            LocatorsType.BY_XPATH,
            ".//select[@id='searchProperty']"),
    DIMENSION_SELECT(
            "Dimension select",
            LocatorsType.BY_XPATH,
            ".//*[@id='doneForm']//select[@id='dimension']"),
    DONE_BUTTON(
            "Done button",
            LocatorsType.BY_XPATH,
            ".//*[@id='content']//input[@type='submit'][@value='Done']"),
    SEARCH_INPUT(
            "Search input",
            LocatorsType.BY_XPATH,
            ".//input[@id='searchValue']"),
    SEARCH_BUTTON(
            "Search button",
            LocatorsType.BY_XPATH,
            ".//input[@value='Search']"),
    SORT_BY_NAME_HEADER_LINK(
            "Sort by name header link",
            LocatorsType.BY_XPATH,
            ".//*[@id='sortName']/a"),
    SORT_BY_DESCRIPTION_HEADER_LINK(
            "Sort by description header link",
            LocatorsType.BY_XPATH,
            ".//*[@id='sortDescription']/a"),
    SELECT_ITEM_LINK(
            "Select item link",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']//tbody/tr[%s]/td[3]//a");

    AddItemPageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
        this.name = name;
        this.locatorsType = locatorsType;
        this.rawLocator = rawLocator;
    }

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public AddItemPageLocators modify(final String parameter) {
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
