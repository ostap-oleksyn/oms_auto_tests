package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.domains.UserFromView;
import com.softserveinc.edu.ita.enums.SearchConditions;
import com.softserveinc.edu.ita.enums.SearchFilters;
import com.softserveinc.edu.ita.enums.UsersTableColumns;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
        click(AdministrationPageLocators.CREATE_NEW_USER_LINK);
        return new NewUserPage(driver);
    }

    public String getRandomLoginFromView() {
        Random randomGenerator = new Random();
        int randomLoginRow = randomGenerator.nextInt(4) + 1;

        WebElement loginCell = driver.findElement(By.xpath(
                String.format(AdministrationPageLocators.LOGIN_CELL, randomLoginRow)));

        return loginCell.getText();
    }

    public void clickLastButton() {
        click(AdministrationPageLocators.LAST_BUTTON);
    }

    public String getLastLogin() {
        WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE.getBy());
        int tableSize = table.findElements(AdministrationPageLocators.USERS_TABLE_ROWS.getBy()).size();
        String login = driver.findElement(By.xpath(String
                .format(AdministrationPageLocators.LOGIN_CELL, tableSize))).getText();
        return login;
    }

    public void clickDeleteLastUserLink() {
        WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE.getBy());
        int tableSize = table.findElements(AdministrationPageLocators.USERS_TABLE_ROWS.getBy()).size();
        driver.findElement(By.xpath(String
                .format(AdministrationPageLocators.DELETE_LINK, tableSize))).click();
    }

    /**
     * A method to get "Administration" table from "Administration" page of web-application.
     */
    public List<UserFromView> getTableFromView() {
        List<UserFromView> usersList = new LinkedList<>();
        int pagination = 0;
        click(SHOW_10_ITEMS_LINK);
        do {
            if (driver.findElements(AdministrationPageLocators.TABLE_ROWS.getBy()).size() <= 1) {
                    return usersList;
            } else {
                List<WebElement> rowsList = driver.findElements(AdministrationPageLocators.TABLE_ROWS.getBy());
                for (int j = 1; j < rowsList.size(); j++) {
                    List<WebElement> cellsList = rowsList.get(j).findElements(AdministrationPageLocators.ROW_CELLS.getBy());
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

    public int getFoundUsersNumber(){
        return Integer.parseInt(getElementText(AdministrationPageLocators.FOUND_USERS_NUMBER));
    }

    public int getNumberOfRows(){
        return driver.findElements(AdministrationPageLocators.USERS_TABLE_ROWS.getBy()).size();
    }

    public void clickUsersListResizeLink(){
        click(AdministrationPageLocators.USERS_LIST_RESIZE_LINK);
    }

    public int getCurrentPageNumber(){
        return Integer.parseInt(getElementText(AdministrationPageLocators.CURRENT_PAGE_NUMBER));
    }
    public void clickPreviousButton() {
        click(AdministrationPageLocators.BACKWARD_BUTTON);
    }

    /**
     * selects filter type
     *
     * @param filter
     * @return
     */
    public AdministrationPage setFilters(SearchFilters filter) {
        Select fieldSelect = new Select(driver.findElement(FILTER_SELECT.getBy()));
        fieldSelect.selectByVisibleText(filter.toString());
        return this;
    }

    /**
     * selects condition type
     *
     * @param condition
     * @return
     */
    public AdministrationPage setCondition(SearchConditions condition) {
        Select conditionSelect = new Select(driver.findElement(CONDITION_SELECT.getBy()));
        conditionSelect.selectByVisibleText(condition.toString());
        return this;
    }

    /**
     * inputs search text into search field
     *
     * @param searchTerm
     * @return
     */

    public AdministrationPage fillSearchField(String searchTerm) {
        sendKeys(SEARCH_FIELD,searchTerm);
        return this;
    }

    /**
     * click on the search button
     */
    public void clickSearchButton() {
        click(SEARCH_BUTTON);
    }

    public void clearSearchField() {
        driver.findElement(SEARCH_FIELD.getBy()).clear();
    }

}
