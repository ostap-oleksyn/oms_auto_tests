package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.domains.Order;
import com.softserveinc.edu.ita.enums.OrdersTableColumns;
import com.softserveinc.edu.ita.locators.OrderingPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

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
    public List<Order> getTableFromView() {
        List<Order> table = new LinkedList<>();
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
                    table.add(Order.newBuilder()
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

}

