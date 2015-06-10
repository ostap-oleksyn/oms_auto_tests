package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.dao_jdbc.domains.Orders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import com.softserveinc.edu.ita.locators.OrderingPageLocators;

public class OrderingPage extends LogOutBase {

    public OrderingPage(WebDriver driver) {
        super(driver);
    }

    public List<Orders> getTable() {
        List<Orders> table = new LinkedList<>();
        //Next two lines are used for finding out quantity of iteration which are needed to build table.
        int ordersQuantity = Integer.parseInt(driver.findElement(OrderingPageLocators.QUANTITY_OF_ROWS).getAttribute("value"));
        int rowsQuantity = driver.findElements(By.xpath(".//*[@id='list']/table/tbody/tr/td[1]")).size();
        double iteration = new BigDecimal(ordersQuantity / 2).setScale(0, RoundingMode.UP).doubleValue();
        for (int i = 1; i <= iteration; i++) {
            for (int j = 2; j <= rowsQuantity + 1; j++) {
                //There is checking of row displaying.
                if (isElementDisplayed(By.xpath(String.format(OrderingPageLocators.TABLE_ROW_CELL, j)))) {
                    //Recording displayed row.
                    List<WebElement> webElements = driver.findElements(By.xpath(String.format(OrderingPageLocators.TABLE_ROW, j)));
                    table.add(new Orders.OrdersStepBuilder().withOrderName(webElements.get(0).getText()).withTotalPrice(webElements.get(1).getText())
                            .withMaxDiscount(webElements.get(2).getText()).withDeliveryDate(webElements.get(3).getText())
                            .withStatus(webElements.get(4).getText()).withAssignee(webElements.get(5).getText())
                            .withRole(webElements.get(6).getText()).build());
                }

            }
            if (i != ordersQuantity / 2) {
                clickButton(OrderingPageLocators.CLICK_NEXT_BUTTON);
            } else {
                clickButton(OrderingPageLocators.CLICK_FIRST_BUTTON);
            }
        }
        return table;
    }

    public void clickButton(By by) {
        driver.findElement(by).click();
    }

    public void clickTableColumn(String column) {
        driver.findElement(By.xpath(String.format(OrderingPageLocators.TABLE_COLUMN, column))).click();
    }
}
