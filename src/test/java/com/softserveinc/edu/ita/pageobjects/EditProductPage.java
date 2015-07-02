package com.softserveinc.edu.ita.pageobjects;


import com.softserveinc.edu.ita.locators.EditProductPageLocators;
import org.openqa.selenium.WebDriver;

public class EditProductPage extends LogOutBase {
    public EditProductPage(WebDriver driver) {
        super(driver);
    }

    public ItemManagementPage clickOkButton() {
        click(EditProductPageLocators.OK_BUTTON);
        return new ItemManagementPage(driver);
    }

    public EditProductPage fillProductName(String productName) {
        driver.findElement(EditProductPageLocators.PRODUCT_NAME_FIELD.getBy()).clear();
        sendKeys(EditProductPageLocators.PRODUCT_NAME_FIELD, productName);
        return this;
    }

    public EditProductPage fillProductDescription(String productDescription) {
        driver.findElement(EditProductPageLocators.PRODUCT_DESCRIPTION_FIELD.getBy()).clear();
        sendKeys(EditProductPageLocators.PRODUCT_DESCRIPTION_FIELD, productDescription);
        return this;
    }

    public EditProductPage fillProductPrice(String productDescription) {
        driver.findElement(EditProductPageLocators.PRODUCT_PRICE_FIELD.getBy()).clear();
        sendKeys(EditProductPageLocators.PRODUCT_PRICE_FIELD, productDescription);
        return this;
    }
}
