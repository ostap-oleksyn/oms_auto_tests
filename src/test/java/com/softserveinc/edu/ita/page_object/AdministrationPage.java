package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.domains.UserFromView;
import com.softserveinc.edu.ita.enums.UsersTableColumns;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.*;

/**
 * This class describes "Administration" page according to "Page Object" pattern.
 */
public class AdministrationPage extends LogOutBase {

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * A method to get "Administration" table from "Administration" page of web-application.
     */
    public List<UserFromView> getTableFromView() {
        List<UserFromView> usersList = new LinkedList<>();
        int pagination = 0;
        do {
            if (driver.findElements(AdministrationPageLocators.TABLE_ROWS.getBy()).size() <= 1) {
            } else {
                List<WebElement> rowsList = driver.findElements(AdministrationPageLocators.TABLE_ROWS.getBy());
                for (int j = 1; j < rowsList.size(); j++) {
                    List<WebElement> cellsList = rowsList.get(j).findElements(AdministrationPageLocators.ROW_CELLS.getBy());
                    usersList.add(new UserFromView.Builder()
                            .withFirstName(cellsList.get(0).getText())
                            .withLastName(cellsList.get(1).getText())
                            .withLogin(cellsList.get(2).getText())
                            .withRole(cellsList.get(3).getText())
                            .withRegion(cellsList.get(4).getText())
                            .build());
                }
            }
            pagination++;
            clickNextButton();
        } while (pagination < getQuantityOfTablePages());
        clickFirstButton();
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - <b>We got a table.</b>"));
        return usersList;
    }

    /**
     * A method to click "First" button below "Ordering" table of "Ordering" page.
     */
    public void clickFirstButton() {
        click(AdministrationPageLocators.FIRST_BUTTON);
    }

    /**
     * A method to click "Next" button below "Ordering" table of "Ordering" page.
     */
    public void clickNextButton() {
        click(AdministrationPageLocators.NEXT_BUTTON);
    }

    /**
     * A method to click one of "Administration" table headers to make sorting actions in the table.
     */
    public void clickAdministrationTableColumn(UsersTableColumns tableColumn) {
        driver.findElement(By.xpath(String.format(AdministrationPageLocators.TABLE_COLUMN, tableColumn))).click();
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Clicked <b>%s</b>", tableColumn));
    }

    /**
     * A method to count quantity of pages with "Administration" table.
     */
    public int getQuantityOfTablePages() {
        return Integer.valueOf(getElementText(AdministrationPageLocators.QUANTITY_OF_TABLE_PAGES));
    }
}
