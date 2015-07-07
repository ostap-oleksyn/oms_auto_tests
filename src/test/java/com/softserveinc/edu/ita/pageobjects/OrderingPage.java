package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.domains.OrderingsTableRow;
import com.softserveinc.edu.ita.enums.ordering_page.OrdersTableColumns;
import com.softserveinc.edu.ita.enums.ordering_page.OrderFilter;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;

import com.softserveinc.edu.ita.locators.OrderingPageLocators;

import com.softserveinc.edu.ita.enums.ordering_page.*;
import com.softserveinc.edu.ita.enums.ordering_page.OrdersTableColumns;
import com.softserveinc.edu.ita.enums.ordering_page.OrderFilter;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.locators.OrderingPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import static com.softserveinc.edu.ita.locators.OrderingPageLocators.*;

/**
 * This class describes "Ordering" page according to "Page Object" pattern.
 */
public class OrderingPage extends LogOutBase {

    public OrderingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * There is method to get "Ordering" table from "Ordering" page of web-application.
     */
    public List<OrderingsTableRow> getTableFromView() {
        List<OrderingsTableRow> table = new LinkedList<>();
        //This line is needed to find out quantity of orders per table.
        int quantityOfOrdersPerTable = Integer.parseInt(driver.findElement(OrderingPageLocators.QUANTITY_OF_ROWS.getBy()).getAttribute("value"));
        //This line is needed to find out quantity of orders per page.
        int quantityOfOrdersPerPage = driver.findElements(OrderingPageLocators.QUANTITY_OF_ROWS_PER_PAGE.getBy()).size();
        if (quantityOfOrdersPerPage <= 1) {
            return table;
        }
        //There is calculating size of pagination.
        double iteration = new BigDecimal(quantityOfOrdersPerTable / quantityOfOrdersPerPage).setScale(0, RoundingMode.UP).doubleValue();
        //Iteration through table pages.
        for (int i = 1; i <= iteration; i++) {
            //Iteration through table rows (per page).
            for (int j = 2; j <= quantityOfOrdersPerPage + 1; j++) {
                //There is checking of row displaying.
                if (isElementDisplayed(OrderingPageLocators.TABLE_ROW_CELL.modify(String.valueOf(j)))) {
                    //Recording displayed row.
                    List<WebElement> ordersFields = driver.findElements(OrderingPageLocators.TABLE_ROW.modify(String.valueOf(j)).getBy());
                    //There is used StepBuilderPattern.
                    table.add(OrderingsTableRow.newBuilder()
                            .setOrderName(ordersFields.get(0).getText())
                            .setTotalPrice(ordersFields.get(1).getText())
                            .setMaxDiscount(ordersFields.get(2).getText())
                            .setDeliveryDate(ordersFields.get(3).getText())
                            .setStatus(ordersFields.get(4).getText())
                            .setAssignee(ordersFields.get(5).getText())
                            .setRole(ordersFields.get(6).getText())
                            .build());
                }
            }
            //If true, we can continue iteration through the table then.
            if (i != iteration) {
                clickNextButton();

                //We stop iteration through the table and return to first table page.
            } else {
                clickFirstButton();
            }
        }
        return table;
    }

    /**
     * There is method to click "First" button below "Ordering" table of "Ordering" page.
     */
    public void clickFirstButton() {
        click(OrderingPageLocators.CLICK_FIRST_BUTTON);
    }

    /**
     * There is method to click "Next" button below "Ordering" table of "Ordering" page.
     */
    public void clickNextButton() {
        click(OrderingPageLocators.CLICK_NEXT_BUTTON);
    }

    /**
     * There is method to click one of "Ordering" table headers to make sorting actions in the table.
     */
    public void clickOrdersTableColumn(OrdersTableColumns tableColumn) {
        click(OrderingPageLocators.TABLE_COLUMN.modify(tableColumn.toString()));
    }

    public OrderingPage setFilter(OrderFilter filter) {
        Select fieldSelect = new Select(driver.findElement(OrderingPageLocators.FILTER_SELECT.getBy()));
        fieldSelect.selectByVisibleText(filter.getFilterName());
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected filter - <b>'%s'</b>", filter.getFilterName()));
        return this;
    }

    public OrderingPage setFilterValue(Enum filterValue) {
        Select fieldSelect = new Select(driver.findElement(OrderingPageLocators.FILTER_VALUE_SELECT.getBy()));
        fieldSelect.selectByVisibleText(filterValue.toString());
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected filter value - <b>'%s'</b>", filterValue.toString()));
        return this;
    }

    public OrderingPage setSearchCondition(OrderSearchCondition searchCondition) {
        Select fieldSelect = new Select(driver.findElement(OrderingPageLocators.SEARCH_CONDITION_SELECT.getBy()));
        fieldSelect.selectByVisibleText(searchCondition.getSearchCondition());
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected search condition - <b>'%s'</b>", searchCondition.getSearchCondition()));
        return this;
    }

    public OrderingPage setShownElementsNumber(ShownElementsNumber number) {
        Select select = new Select(driver.findElement(SHOW_NUMBER_OF_ELEMENTS_LINK.getBy()));
        select.selectByVisibleText(number.getName());
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected  - <b>'%s'</b>", SHOW_NUMBER_OF_ELEMENTS_LINK.getName()));
        return this;
    }

    public OrderingPage setItemOrderStatus(ItemsOrderStatus status) {
        Select select = new Select(driver.findElement(ITEM_ORDER_STATUS.getBy()));
        select.selectByValue(String.valueOf(status));
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected  - <b>'%s'</b>", status.getStatusName()));
        return this;
    }

    public OrderingPage clickApplyButton() {
        click(OrderingPageLocators.APPLY_BUTTON);
        return this;
    }

    public OrderingPage fillSearchField(String searchTerm) {
        sendKeys(OrderingPageLocators.SEARCH_FIELD, searchTerm);
        return this;
    }

    public OrderingPage clearSearchField() {
        driver.findElement(OrderingPageLocators.SEARCH_FIELD.getBy()).clear();
        return this;
    }

    public String getSearchFieldText() {
        return getElementAttribute(OrderingPageLocators.SEARCH_FIELD, "value");
    }

    public OrderingPage clickEditLink() {
        click(EDIT_LINK);
        return this;
    }

    public OrderingPage clickItemNextPageButton() {
        click(ITEM_NEXT_PAGE_BUTTON);
        return this;
    }

    public OrderingPage clickItemCancelButton() {
        click(ITEM_CANCEL_BUTTON);
        return this;
    }

    public OrderingPage clickItemSaveButton() {
        click(ITEM_SAVE_BUTTON);
        return this;
    }

    public OrderingPage clickItemErrorShowButton() {
        click(ITEM_ERROR_SHOW_BUTTON);
        return this;
    }

    public OrderingPage clickItemGoToHomeButton() {
        click(ITEM_GO_TO_HOME_BUTTON);
        return this;
    }


    public NewOrderPage clickCreateNewOrderLink() {
        click(OrderingPageLocators.CREATE_NEW_ORDER_LINK);
        return new NewOrderPage(driver);
    }

    public OrderingPage clickDeleteOrderButton() {
        click(OrderingPageLocators.DELETE_ORDER_BUTTON);
        return this;
    }

    public String getFirstOrderName() {
        return getElementText(OrderingPageLocators.FIRST_ORDER);
    }
}

