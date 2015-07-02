package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.enums.item_management_page.ItemFilter;
import com.softserveinc.edu.ita.enums.item_management_page.ProductsTableColumns;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import static com.softserveinc.edu.ita.locators.AdministrationPageLocators.FILTER_SELECT;
import static com.softserveinc.edu.ita.locators.AdministrationPageLocators.SEARCH_BUTTON;

public class ItemManagementPage extends LogOutBase {

    public ItemManagementPage(WebDriver driver) {
        super(driver);
    }

    public ItemManagementPage setFilters(ItemFilter filter) {
        Select fieldSelect = new Select(driver.findElement(FILTER_SELECT.getBy()));
        fieldSelect.selectByVisibleText(filter.getFilterName());
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected filter - <b>'%s'</b>", filter.getFilterName()));
        return this;
    }

    public ItemManagementPage fillSearchField(String searchTerm) {
        sendKeys(ItemManagementPageLocators.SEARCH_FIELD, searchTerm);
        return this;
    }

    public ItemManagementPage clickSearchButton() {
        click(SEARCH_BUTTON);
        return this;
    }

    public String getSearchFieldText() {
        return getElementAttribute(ItemManagementPageLocators.SEARCH_FIELD, "value");
    }

    public AddProductPage clickAddProductLink() {
        click(ItemManagementPageLocators.ADD_PRODUCT_LINK);
        return new AddProductPage(driver);
    }

    public void clickLastButton() {
        click(ItemManagementPageLocators.LAST_BUTTON);
    }

    public int getFoundProductsNumber() {
        return Integer.parseInt(getElementText(ItemManagementPageLocators.FOUND_PRODUCTS_NUMBER));
    }

    /**
     * Method to click on the "create report" link.
     */
    public void clickCreateReportLink() {
        click(ItemManagementPageLocators.CREATE_REPORT_LINK);
    }

    /**
     * There is method to click one of "Ordering" table headers to make sorting actions in the table.
     */
    public void clickProductsTableColumn(ProductsTableColumns tableColumn) {
        click(ItemManagementPageLocators.TABLE_COLUMN.modify(tableColumn.toString()));
    }

    /**
     * To click resize link.
     */
    public void clickResizeLink() {
        click(ItemManagementPageLocators.PRODUCTS_LIST_RESIZE_LINK);
    }
}
