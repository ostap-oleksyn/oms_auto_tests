package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.domains.AdministrationsTableRow;
import com.softserveinc.edu.ita.enums.administration_page.SearchConditions;
import com.softserveinc.edu.ita.enums.administration_page.SearchFilters;
import com.softserveinc.edu.ita.enums.administration_page.UsersTableColumns;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * PageObject class that represents Administration page.
 */
public class AdministrationPage extends LogOutBase {

    public AdministrationPage(final WebDriver driver) {
        super(driver);
    }

    public NewUserPage clickCreateUserLink() {
        click(AdministrationPageLocators.CREATE_NEW_USER_LINK);
        return new NewUserPage(driver);
    }

    public String getRandomLoginFromView() {
        final Random randomGenerator = new Random();
        final int randomLoginRow = randomGenerator.nextInt(4) + 1;
        return getElementText(AdministrationPageLocators.LOGIN_CELL.modify(String.valueOf(randomLoginRow)));
    }


    public EditUserPage clickEditButton(final int randomNumber) {
        click(AdministrationPageLocators.EDIT_USER_LINK
                .modify(String.valueOf(randomNumber)));
        return new EditUserPage(driver);
    }

    public void clickLastButton() {
        click(AdministrationPageLocators.LAST_BUTTON);
    }

    public String getLastLogin() {
        final WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE.getBy());
        final int tableSize = table.findElements(AdministrationPageLocators.USERS_TABLE_ROWS.getBy()).size();
        return getElementText(AdministrationPageLocators.LOGIN_CELL.modify(String.valueOf(tableSize)));

    }

    public void clickDeleteLastUserLink() {
        final WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE.getBy());
        final int tableSize = table.findElements(AdministrationPageLocators.USERS_TABLE_ROWS.getBy()).size();
        click(AdministrationPageLocators.DELETE_LINK.modify(String.valueOf(tableSize)));
    }

    /**
     * A method to get "Administration" table
     * from "Administration" page of web-application.
     */
    public List<AdministrationsTableRow> getTableFromView() {
        final List<AdministrationsTableRow> usersList = new LinkedList<>();
        int pagination = 0;
        if (getElementText(AdministrationPageLocators.USERS_LIST_RESIZE_LINK).contains("10"))
            click(AdministrationPageLocators.USERS_LIST_RESIZE_LINK);
        do {
            if (driver.findElements(AdministrationPageLocators.TABLE_ROWS.getBy()).size() <= 1) {
                return usersList;
            } else {
                final List<WebElement> rowsList = driver.findElements(AdministrationPageLocators.TABLE_ROWS.getBy());
                for (int j = 1; j < rowsList.size(); j++) {
                    final List<WebElement> cellsList = rowsList.get(j).findElements(AdministrationPageLocators.ROW_CELLS.getBy());
                    usersList.add(AdministrationsTableRow.newBuilder()
                            .setFirstName(cellsList.get(0).getText())
                            .setLastName(cellsList.get(1).getText())
                            .setLogin(cellsList.get(2).getText())
                            .setRole(cellsList.get(3).getText())
                            .setRegion(cellsList.get(4).getText())
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
     * A method to click one of "Administration"
     * table headers to make sorting actions in the table.
     */
    public void clickAdministrationTableColumn(final UsersTableColumns tableColumn) {
        click(AdministrationPageLocators.TABLE_COLUMN.modify(tableColumn.toString()));
    }

    /**
     * A method to count quantity of pages with "Administration" table.
     */
    public int getQuantityOfTablePages() {
        return Integer.valueOf(getElementText(AdministrationPageLocators.QUANTITY_OF_TABLE_PAGES));
    }

    public int getFoundUsersNumber() {
        return Integer.parseInt(getElementText(AdministrationPageLocators.FOUND_USERS_NUMBER));
    }

    public int getNumberOfRows() {
        return driver.findElements(AdministrationPageLocators.USERS_TABLE_ROWS.getBy()).size();
    }

    public void clickUsersListResizeLink() {
        click(AdministrationPageLocators.USERS_LIST_RESIZE_LINK);
    }

    public int getCurrentPageNumber() {
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
    public AdministrationPage setFilters(final SearchFilters filter) {
        final Select fieldSelect = new Select(driver.findElement(AdministrationPageLocators.FILTER_SELECT.getBy()));
        fieldSelect.selectByVisibleText(filter.toString());
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected filter - <b>'%s'</b>", filter.getFilterName()));
        return this;
    }

    /**
     * selects condition type
     *
     * @param condition
     * @return
     */
    public AdministrationPage setCondition(final SearchConditions condition) {
        final Select conditionSelect = new Select(driver.findElement(AdministrationPageLocators.CONDITION_SELECT.getBy()));
        conditionSelect.selectByVisibleText(condition.toString());
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected condition - <b>'%s'</b>", condition.getCondition()));
        return this;
    }

    /**
     * inputs search text into search field
     *
     * @param searchTerm
     * @return
     */
    public AdministrationPage fillSearchField(final String searchTerm) {
        sendKeys(AdministrationPageLocators.SEARCH_FIELD, searchTerm);
        return this;
    }

    /**
     * click on the search button
     */
    public void clickSearchButton() {
        click(AdministrationPageLocators.SEARCH_BUTTON);
    }

    public void clearSearchField() {
        driver.findElement(AdministrationPageLocators.SEARCH_FIELD.getBy()).clear();
    }

    public void clickCreateReportLink() {
        click(AdministrationPageLocators.CREATE_REPORT_LINK);
    }

    public String getSearchFieldText() {
        return getElementAttribute(AdministrationPageLocators.SEARCH_FIELD, "value");
    }
}
