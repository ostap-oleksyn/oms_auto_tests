package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;
import com.softserveinc.edu.ita.enums.UsersTable;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
     * A method to click "First" button below "Ordering" table of "Ordering" page.
     */
    public void clickFirstButton() {
        driver.findElement(AdministrationPageLocators.FIRST_BUTTON).click();
    }

    /**
     * A method to click "Next" button below "Ordering" table of "Ordering" page.
     */
    public void clickNextButton() {
        driver.findElement(AdministrationPageLocators.NEXT_BUTTON).click();
    }

    /**
     * A method to click one of "Administration" table headers to make sorting actions in the table.
     */
    public void clickAdministrationTableColumn(UsersTable tableColumn) {
        driver.findElement(By.xpath(String.format(AdministrationPageLocators.TABLE_COLUMN, tableColumn.getName()))).click();
    }

    /**
     * A method to count quantity of pages with "Administration" table.
     */
    public int getQuantityOfTablePages() {
        return Integer.valueOf(getElementText(AdministrationPageLocators.QUANTITY_OF_TABLE_PAGES));
    }

    /**
     * A method to verify equality of tables by given column.
     */
    public boolean verifyEqualityOfTablesByColumn(List<UserFromView> sortedBaseTable, List<UserFromView> sortedTable, UsersTable header)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Iterator firstTableIterator = sortedBaseTable.iterator();
        Iterator secondTableIterator = sortedTable.iterator();
        int equalsCells = 0;
        while (firstTableIterator.hasNext() &&
                UserFromView.class.getDeclaredMethod(header.getMethodName()).invoke(firstTableIterator.next())
                        .equals(UserFromView.class.getDeclaredMethod(header.getMethodName()).invoke(secondTableIterator.next()))) {
            equalsCells++;
        }
        return (equalsCells == sortedBaseTable.size());
    }

    /**
     * A method to sort base table by given column through comparator.
     */
    public void sortBaseTableBy(List<UserFromView> baseTableFromView, UsersTable defaultHeader) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (UserFromView user : baseTableFromView) {
            user.setDefaultHeader(UserFromView.class.getDeclaredMethod(defaultHeader.getMethodName()).invoke(user).toString().toLowerCase());
        }
        baseTableFromView.sort(Comparator.comparing(UserFromView::getDefaultHeader));
    }

    /**
     * A method to reverse base table after sorting.
     */
    public void reverseBaseTable(List<UserFromView> baseTableFromView) {
        baseTableFromView.sort(Comparator.comparing(UserFromView::getDefaultHeader).reversed());
    }

    /**
     * A method to verify integrity of table after sorting. The method says "All of the rows are(true)/aren't(false) intact after sorting".
     */
    public boolean verifyIntegrityOfTableAfterSorting(List<UserFromView> baseTable, List<UserFromView> tableAfterSorting) {
        int quantityOfIntactTableRowsAfterSorting = 0;
        Iterator tableIterator = tableAfterSorting.iterator();
        while (tableIterator.hasNext() && baseTable.toString().contains(tableIterator.next().toString())) {
            quantityOfIntactTableRowsAfterSorting++;
        }
        return (quantityOfIntactTableRowsAfterSorting == tableAfterSorting.size());
    }

}
