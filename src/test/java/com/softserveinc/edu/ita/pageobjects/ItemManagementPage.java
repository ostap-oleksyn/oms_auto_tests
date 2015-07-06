package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.enums.item_management_page.ItemFilter;
import com.softserveinc.edu.ita.enums.item_management_page.ProductsTableColumns;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.List;

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

    public int getNumberOfRows() {
        return driver.findElements(ItemManagementPageLocators.TABLE_ROW.getBy()).size();
    }

    public Product getRandomProduct(int modifier) {
        return Product.newBuilder()
                .withoutId()
                .withoutStatus()
                .withProductName(getElementText(ItemManagementPageLocators.TABLE_NAME_CELL.modify(String.valueOf(modifier))))
                .withProductDescription(getElementText(ItemManagementPageLocators.TABLE_DESCRIPTION_CELL.modify(String.valueOf(modifier))))
                .withProductPrice(Double.parseDouble(getElementText(ItemManagementPageLocators.TABLE_PRICE_CELL.modify(String.valueOf(modifier)))))
                .build();
    }

    public void deleteRandomProduct(int randomRow) {
        click(ItemManagementPageLocators.TABLE_DELETE_LINK.modify(String.valueOf(randomRow)));
    }

    public EditProductPage editRandomProduct(int randomRow) {
        click(ItemManagementPageLocators.TABLE_EDIT_LINK.modify(String.valueOf(randomRow)));
        return new EditProductPage(driver);
    }

    /**
     * Method to click on the "create report" link.
     */
    public void clickCreateReportLink() {
        click(ItemManagementPageLocators.CREATE_REPORT_LINK);
    }

    /**
     * There is method to click one of "Products" table headers to make sorting actions in the table.
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

    /**
     * To get column by name from table of products.
     */
    public Object[] getColumn(ProductsTableColumns column) {
        List<WebElement> columnsElement = driver.findElements(ItemManagementPageLocators.COLUMNS_ELEMENTS
                .modify(String.valueOf(column.ordinal() + 1)).getBy());
        Object[] columnsData = new Object[columnsElement.size()];
        for (int i = 0; i < columnsElement.size(); i++) {
            columnsData[i] = column.getValue(columnsElement.get(i));
        }
        return columnsData;
    }

    public ItemManagementPage clearSearchField() {
        driver.findElement(ItemManagementPageLocators.SEARCH_FIELD.getBy()).clear();
        Reporter.log("<br>INFO&nbsp;&nbsp; - Cleared <b>Search field</b>");
        return this;
    }

    public int getCurrentPageNumber() {
        return Integer.parseInt(getElementText(ItemManagementPageLocators.CURRENT_PAGE_NUMBER));
    }

    public int getTablePagesQuantity() {
        return Integer.valueOf(getElementText(ItemManagementPageLocators.QUANTITY_OF_TABLE_PAGES));
    }

    public void clickPreviousButton() {
        click(ItemManagementPageLocators.PREVIOUS_BUTTON);
    }

    public void clickNextButton() {
        click(ItemManagementPageLocators.NEXT_BUTTON);
    }
}
