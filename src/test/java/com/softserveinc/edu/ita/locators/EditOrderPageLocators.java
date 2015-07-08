package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.locators.interfaces.ILocator;
import org.openqa.selenium.By;

public enum EditOrderPageLocators implements ILocator {

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
            "//div[2]/div");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    EditOrderPageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
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
    public EditOrderPageLocators modify(final String parameter) {
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
