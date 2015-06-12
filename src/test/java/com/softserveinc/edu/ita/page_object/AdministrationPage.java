package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;
import com.softserveinc.edu.ita.enums.UsersTable;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class AdministrationPage extends LogOutBase {

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * There is method to get "Administration" table from "Administration" page of web-application.
     */
    public List<UserFromView> getTableFromView() {
        List<UserFromView> usersList = new LinkedList<>();
        int pagination = 0;
        do {
            if (driver.findElements(AdministrationPageLocators.TABLE_ROWS).size() <= 1) {
            } else {
                List<WebElement> rowsList = driver.findElements(AdministrationPageLocators.TABLE_ROWS);
                for (int j = 1; j < rowsList.size(); j++) {
                    List<WebElement> cellsList = rowsList.get(j).findElements(AdministrationPageLocators.ROW_CELLS);
                    usersList.add(new UserFromView.UserFromViewBuilder()
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
        return usersList;
    }

    /**
     * There is method to click "First" button below "Ordering" table of "Ordering" page.
     */
    public void clickFirstButton() {
        driver.findElement(AdministrationPageLocators.FIRST_BUTTON).click();
    }

    /**
     * There is method to click "Next" button below "Ordering" table of "Ordering" page.
     */
    public void clickNextButton() {
        driver.findElement(AdministrationPageLocators.NEXT_BUTTON).click();
    }

    /**
     * There is method to click one of "Administration" table headers to make sorting actions in the table.
     */
    public void clickAdministrationTableColumn(UsersTable tableColumn) {
        driver.findElement(By.xpath(String.format(AdministrationPageLocators.TABLE_COLUMN, tableColumn.getName()))).click();
    }

    public int getQuantityOfTablePages() {
        return Integer.valueOf(getElementText(AdministrationPageLocators.QUANTITY_OF_TABLE_PAGES));
    }

    /**
     * There is method to verify equality of tables by one of the columns.
     */
    public boolean verifyEqualityOfTablesByColumn(List<UserFromView> sortedBaseTable, List<UserFromView> sortedTable, UsersTable field)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int equalsCells = 0;
        for (int i = 0; i < sortedBaseTable.size(); i++) {
            if (UserFromView.class.getDeclaredMethod(field.getMethodName()).invoke(sortedBaseTable.get(i))
                    .equals(UserFromView.class.getDeclaredMethod(field.getMethodName()).invoke(sortedTable.get(i)))) {
                equalsCells++;
            }
        }
        return (equalsCells == sortedBaseTable.size());
    }

}
