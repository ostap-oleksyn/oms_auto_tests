package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum NewOrderPageLocators implements ILocator {

    ADD_ITEM_BUTTON(
            "Add item button",
            By.xpath(".//fieldset//input[@value='Add Item']")),
    NAVIGATION_FIRST_BUTTON(
            "First button",
            By.xpath(".//fieldset//input[@value='First']")),
    NAVIGATION_BACKWARD_BUTTON(
            "Backward button",
            By.xpath(".//fieldset//input[@value='Backward']")),
    NAVIGATION_FORWARD_BUTTON(
            "Forward button",
            By.xpath(".//fieldset//input[@value='Forward']")),
    NAVIGATION_LAST_BUTTON(
            "LAst button",
            By.xpath(".//fieldset//input[@value='Last']")),
    SHOW_LINES_COUNT_SELECT(
            "Show lines count select",
            By.xpath(".//fieldset//select[@id='rowsNumber']")),
    ITEMS_LIST(
            "Items list",
            By.xpath(".//fieldset//div[@id='list']")),
    ASSIGNEE_SELECT(
            "Assignee select",
            By.xpath(".//*[@id='assignee']")),
    CREDIT_CARD_NUMBER_INPUT(
            "Credit card number input",
            By.xpath(".//*[@id='cardNum']")),
    CVV2_INPUT(
            "CVV2 input",
            By.xpath(".//*[@id='cvv2']")),
    PREFERABLE_DELIVERY_DATE_INPUT(
            "Preferable delivery date input",
            By.xpath(".//*[@id='dateDays']")),
    SAVE_BUTTON(
            "Save Button",
            By.xpath(".//*[@id='save']")),
    ORDER_NUMBER_INPUT(
            "Order number input",
            By.xpath(".//*[@id='orderNum']")),
    ITEMS_TABLE_ROWS(
            "Items table rows",
            By.xpath(".//*[@id='list']/table/tbody/tr")),
    ITEMS_ROW_CELL(
            "Items row cell",
            By.xpath("td")),
    TOTAL_PRICE_CELL(
            "Total price cell",
            By.xpath(".//*[@id='saveButton']/table/tbody/tr[3]/td[2]")),
    // TODO redo versatile after update to new locators type
    EDIT_ITEM_LINK(
            "Total price cell",
            By.xpath(".//*[@id='edit0']/a"));

    NewOrderPageLocators(String name, By locator) {
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
