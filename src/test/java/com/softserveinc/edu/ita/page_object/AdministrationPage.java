package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.domains.UserFromView;
import com.softserveinc.edu.ita.enums.UsersTableColumns;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.softserveinc.edu.ita.locators.AdministrationPageLocators.*;

/**
 * This class describes "Administration" page according to "Page Object" pattern.
 */
public class AdministrationPage extends LogOutBase {

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    public NewUserPage clickCreateUserLink() {
        click(CREATE_NEW_USER_LINK);
        return new NewUserPage(driver);
    }

    public String getRandomLoginFromView() {
        Random randomGenerator = new Random();
        int randomLoginRow = randomGenerator.nextInt(4) + 1;

        WebElement loginCell = driver.findElement(By.xpath(
                String.format(LOGIN_CELL, randomLoginRow)));

        return loginCell.getText();
    }

    public void clickLastButton() {
        click(LAST_BUTTON);
    }

    public String getLastLogin() {
        WebElement table = driver.findElement(USERS_TABLE.getBy());
        int tableSize = table.findElements(USERS_TABLE_ROWS.getBy()).size();
        String login = driver.findElement(By.xpath(String
                .format(LOGIN_CELL, tableSize))).getText();
        return login;
    }

    public void clickDeleteLastUserLink() {
        WebElement table = driver.findElement(USERS_TABLE.getBy());
        int tableSize = table.findElements(USERS_TABLE_ROWS.getBy()).size();
        driver.findElement(By.xpath(String
                .format(DELETE_LINK, tableSize))).click();
    }

    /**
     * A method to get "Administration" table from "Administration" page of web-application.
     */
    public List<UserFromView> getTableFromView() {
        List<UserFromView> usersList = new LinkedList<>();
        int pagination = 0;
        driver.findElement(SHOW_10_ITEMS.getBy()).click();
        do {
            if (driver.findElements(TABLE_ROWS.getBy()).size() <= 1) {
                return usersList;
            } else {
                List<WebElement> rowsList = driver.findElements(TABLE_ROWS.getBy());
                for (int j = 1; j < rowsList.size(); j++) {
                    List<WebElement> cellsList = rowsList.get(j).findElements(ROW_CELLS.getBy());
                    usersList.add(UserFromView.newBuilder()
                            .firstName(cellsList.get(0).getText())
                            .lastName(cellsList.get(1).getText())
                            .login(cellsList.get(2).getText())
                            .role(cellsList.get(3).getText())
                            .region(cellsList.get(4).getText())
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
        click(FIRST_BUTTON);
    }

    /**
     * A method to click "Next" button below "Ordering" table of "Ordering" page.
     */
    public void clickNextButton() {
        click(NEXT_BUTTON);
    }

    /**
     * A method to click one of "Administration" table headers to make sorting actions in the table.
     */
    public void clickAdministrationTableColumn(UsersTableColumns tableColumn) {
        driver.findElement(By.xpath(String.format(TABLE_COLUMN, tableColumn))).click();
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Clicked <b>%s</b>", tableColumn));
    }

    /**
     * A method to count quantity of pages with "Administration" table.
     */
    public int getQuantityOfTablePages() {
        return Integer.valueOf(getElementText(QUANTITY_OF_TABLE_PAGES));
    }
}
