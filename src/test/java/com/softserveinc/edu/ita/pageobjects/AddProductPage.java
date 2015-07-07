package com.softserveinc.edu.ita.pageobjects;


import com.softserveinc.edu.ita.locators.AddProductPageLocators;
import org.openqa.selenium.WebDriver;

/**
 * PageObject class that represents Add Product page.
 */
public class AddProductPage extends LogOutBase {

    public AddProductPage(final WebDriver driver) {
        super(driver);
    }

    public ItemManagementPage clickOkButton() {
        click(AddProductPageLocators.OK_BUTTON);
        return new ItemManagementPage(driver);
    }

    public ItemManagementPage clickCancelButton() {
        click(AddProductPageLocators.CANCEL_BUTTON);
        return new ItemManagementPage(driver);
    }

    public AddProductPage fillProductName(final String productName) {
        driver.findElement(AddProductPageLocators.PRODUCT_NAME_FIELD.getBy()).clear();
        sendKeys(AddProductPageLocators.PRODUCT_NAME_FIELD, productName);
        return this;
    }

    public AddProductPage fillProductDescription(final String productDescription) {
        driver.findElement(AddProductPageLocators.PRODUCT_DESCRIPTION_FIELD.getBy()).clear();
        sendKeys(AddProductPageLocators.PRODUCT_DESCRIPTION_FIELD, productDescription);
        return this;
    }

    public AddProductPage fillProductPrice(final Double productPrice) {
        driver.findElement(AddProductPageLocators.PRODUCT_PRICE_FIELD.getBy()).clear();
        sendKeys(AddProductPageLocators.PRODUCT_PRICE_FIELD, productPrice.toString());
        return this;
    }

    public String getProductNameFieldText() {
        return getElementAttribute(AddProductPageLocators.PRODUCT_NAME_FIELD, "value");
    }

    public String getProductDescriptionFieldText() {
        return getElementAttribute(AddProductPageLocators.PRODUCT_DESCRIPTION_FIELD, "value");
    }

    public String getProductPriceFieldText() {
        return getElementAttribute(AddProductPageLocators.PRODUCT_PRICE_FIELD, "value");
    }
}
