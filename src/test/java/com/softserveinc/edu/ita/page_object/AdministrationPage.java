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

    public List<UserFromView> getTableFromView() {

        List<UserFromView> usersList = new LinkedList<>();
        int i = 0;
        do {
            WebElement table = driver.findElement(AdministrationPageLocators.TABLE);
            List<WebElement> rowsList = table.findElements(AdministrationPageLocators.TABLE_ROWS);

            for (int j = 1; j < rowsList.size(); j++) {
                List<WebElement> tdList = rowsList.get(j).findElements(AdministrationPageLocators.TABLE_CELLS);

                usersList.add(new UserFromView.AdministratorBuilder()
                        .withFirstName(tdList.get(0).getText())
                        .withLastName(tdList.get(1).getText())
                        .withLogin(tdList.get(2).getText())
                        .withRole(tdList.get(3).getText())
                        .withRegion(tdList.get(4).getText())
                        .build());
            }
            i++;
            clickNextButton();

        } while (i < getQuantityOfTablePages());
        clickFirstButton();
        return usersList;
    }

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
     * There is method to click one of "Ordering" table headers to make sorting actions in the table.
     */
    public void clickAdministrationTableColumn(UsersTable tableColumn) {
        driver.findElement(By.xpath(String.format(AdministrationPageLocators.TABLE_COLUMN, tableColumn.toString()))).click();
    }

    public int getQuantityOfTablePages() {
        return Integer.valueOf(getElementText(AdministrationPageLocators.QUANTITY_OF_TABLE_PAGES));
    }

    public boolean compareTablesByField(List<UserFromView> baseTable, List<UserFromView> sortedTable, String field)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int k = 0;
        for(int i = 0; i < baseTable.size(); i++) {
            if(UserFromView.class.getDeclaredMethod(field).invoke(baseTable.get(i))
            .equals(UserFromView.class.getDeclaredMethod(field).invoke(sortedTable.get(i)))){
                k++;
            };
        }
        return (k==baseTable.size());
    }
}
