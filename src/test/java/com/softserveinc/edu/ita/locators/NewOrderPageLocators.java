package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum NewOrderPageLocators implements ILocator {

    ADD_ITEM_BUTTON(
            "Add item button",
            LocatorsType.BY_XPATH,
            ".//fieldset//input[@value='Add Item']"),
    NAVIGATION_FIRST_BUTTON(
            "First button",
            LocatorsType.BY_XPATH,
            ".//fieldset//input[@value='First']"),
    NAVIGATION_BACKWARD_BUTTON(
            "Backward button",
            LocatorsType.BY_XPATH,
            ".//fieldset//input[@value='Backward']"),
    NAVIGATION_FORWARD_BUTTON(
            "Forward button",
            LocatorsType.BY_XPATH,
            ".//fieldset//input[@value='Forward']"),
    NAVIGATION_LAST_BUTTON(
            "LAst button",
            LocatorsType.BY_XPATH,
            ".//fieldset//input[@value='Last']"),
    SHOW_LINES_COUNT_SELECT(
            "Show lines count select",
            LocatorsType.BY_XPATH,
            ".//fieldset//select[@id='rowsNumber']"),
    ITEMS_LIST(
            "Items list",
            LocatorsType.BY_XPATH,
            ".//fieldset//div[@id='list']"),
    ASSIGNEE_SELECT(
            "Assignee select",
            LocatorsType.BY_XPATH,
            ".//*[@id='assignee']"),
    CREDIT_CARD_NUMBER_INPUT(
            "Credit card number input",
            LocatorsType.BY_XPATH,
            ".//*[@id='cardNum']"),
    CVV2_INPUT(
            "CVV2 input",
            LocatorsType.BY_XPATH,
            ".//*[@id='cvv2']"),
    PREFERABLE_DELIVERY_DATE_INPUT(
            "Preferable delivery date input",
            LocatorsType.BY_XPATH,
            ".//*[@id='dateDays']"),
    SAVE_BUTTON(
            "Save Button",
            LocatorsType.BY_XPATH,
            ".//*[@id='save']"),
    ORDER_NUMBER_INPUT(
            "Order number input",
            LocatorsType.BY_XPATH,
            ".//*[@id='orderNum']"),
    ITEMS_TABLE_ROWS(
            "Items table rows",
            LocatorsType.BY_XPATH,
            ".//*[@id='list']/table/tbody/tr"),
    ITEMS_ROW_CELL(
            "Items row cell",
            LocatorsType.BY_XPATH,
            "td"),
    TOTAL_PRICE_CELL(
            "Total price cell",
            LocatorsType.BY_XPATH,
            ".//*[@id='saveButton']/table/tbody/tr[3]/td[2]"),
    EDIT_ITEM_LINK(
            "Total price cell",
            LocatorsType.BY_XPATH,
            ".//*[@id='edit0']/a");

    NewOrderPageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
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

    public NewOrderPageLocators modify(final String parameter) {
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
