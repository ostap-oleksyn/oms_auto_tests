package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.locators.EditProductPageLocators;
import org.openqa.selenium.WebDriver;

/**
 * PageObject class that represents Edit Product page.
 */
public class EditProductPage extends LogOutBase {
    public EditProductPage(final WebDriver driver) {
        super(driver);
    }

    public ItemManagementPage clickOkButton() {
        click(EditProductPageLocators.OK_BUTTON);
        return new ItemManagementPage(driver);
    }

    public EditProductPage fillProductName(final String productName) {
        driver.findElement(EditProductPageLocators.PRODUCT_NAME_FIELD.getBy()).clear();
        sendKeys(EditProductPageLocators.PRODUCT_NAME_FIELD, productName);
        return this;
    }

    public EditProductPage fillProductDescription(final String productDescription) {
        driver.findElement(EditProductPageLocators.PRODUCT_DESCRIPTION_FIELD.getBy()).clear();
        sendKeys(EditProductPageLocators.PRODUCT_DESCRIPTION_FIELD, productDescription);
        return this;
    }

    public EditProductPage fillProductPrice(final String productDescription) {
        driver.findElement(EditProductPageLocators.PRODUCT_PRICE_FIELD.getBy()).clear();
        sendKeys(EditProductPageLocators.PRODUCT_PRICE_FIELD, productDescription);
        return this;
    }
}
