package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.enums.Orders;
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

    public List<OrderingTableRow> getTable() {
        List<OrderingTableRow> table = new LinkedList<>();
        int ordersQuantity = Integer.parseInt(driver.findElement(OrderingPageLocators.QUANTITY_OF_ROWS).getAttribute("value"));
        double iteration = new BigDecimal(ordersQuantity / 2).setScale(0, RoundingMode.UP).doubleValue();
        for (int i = 1; i <= iteration; i++) {
            for (int j = 2; j <= 3; j++) {
                //There is checking of row displaying.
                if (driver.findElement(By.xpath(".//div[@id='list']/table/tbody/tr[" + j + "]/td[1]")).isDisplayed()) {
                    //Recording displayed row.
                    List<WebElement> webElements = driver.findElements(By.xpath(".//div[@id='list']/table/tbody/tr[" + j + "]/td"));
                    table.add(new OrderingTableRow(webElements.get(0).getText(), webElements.get(1).getText(), webElements.get(2).getText(),
                            webElements.get(3).getText(), webElements.get(4).getText(), webElements.get(5).getText(),
                            webElements.get(6).getText(), webElements.get(7).getText(), webElements.get(8).getText()));
                }

            }
            if (i != ordersQuantity / 2) {
                driver.findElement(OrderingPageLocators.CLICK_NEXT).click();
            } else {
                driver.findElement(OrderingPageLocators.CLICK_FIRST).click();
            }
        }
        return table;
    }

    public List<OrderingTableRow> getSortedTableBy(Orders text) {
        //There driver clicks column to sort the table.
        driver.findElement(By.xpath(".//*[@id='list']/table/tbody/tr[1]/th/a[contains(text(),'" + text.toString() + "')]")).click();
        List<OrderingTableRow> table = new LinkedList<>();
        int ordersQuantity = Integer.parseInt(driver.findElement(OrderingPageLocators.QUANTITY_OF_ROWS).getAttribute("value"));
        double iteration = new BigDecimal(ordersQuantity / 2).setScale(0, RoundingMode.UP).doubleValue();
        for (int i = 1; i <= iteration; i++) {
            for (int j = 2; j <= 3; j++) {
                //There is checking of row displaying.
                if (driver.findElement(By.xpath(".//div[@id='list']/table/tbody/tr[" + j + "]/td[1]")).isDisplayed()) {
                    //Recording displayed row.
                    List<WebElement> webElements = driver.findElements(By.xpath(".//div[@id='list']/table/tbody/tr[" + j + "]/td"));
                    table.add(new OrderingTableRow(webElements.get(0).getText(), webElements.get(1).getText(), webElements.get(2).getText(),
                            webElements.get(3).getText(), webElements.get(4).getText(), webElements.get(5).getText(),
                            webElements.get(6).getText(), webElements.get(7).getText(), webElements.get(8).getText()));
                }

            }
            if (i != ordersQuantity / 2) {
                driver.findElement(OrderingPageLocators.CLICK_NEXT).click();
            } else {
                driver.findElement(OrderingPageLocators.CLICK_FIRST).click();
            }
        }
        return table;
    }
}
