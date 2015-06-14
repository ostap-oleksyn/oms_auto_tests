package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.interfaces.LogOutAble;
import com.softserveinc.edu.ita.locators.HomePageLocators;
import com.softserveinc.edu.ita.locators.LogoutPageLocators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LogOutBase extends PageObjectBase implements LogOutAble {

    public LogOutBase(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogOutButton() {
        driver.findElement(HomePageLocators.LOG_OUT_BUTTON).click();
        return new HomePage(driver);
    }

    public AdministrationPage clickAdministrationTab() {
        driver.findElement(LogoutPageLocators.ADMINISTRATION_TAB);
        return new AdministrationPage(driver);
    }

    public List<WebElement[]> getTableCells(By tableLocator) {
        WebElement table = driver.findElement(tableLocator);
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
        List<WebElement[]> resultTable = new ArrayList<>();

        for (WebElement row: rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td"));

            WebElement[] resultRow = new WebElement[cells.size()];
            for (int i = 0; i < cells.size(); i++) {
                resultRow[i] = cells.get(i);
            }
            resultTable.add(resultRow);
        }
        return  resultTable;
    }

    public int getRowsCount(By tableLocator) {
        WebElement table = driver.findElement(tableLocator);
        return table.findElements(By.xpath(".//tbody/tr")).size();
    }

}



