package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.enums.item_management_page.ItemFilter;
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
}
