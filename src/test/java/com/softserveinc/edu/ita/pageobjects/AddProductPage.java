package com.softserveinc.edu.ita.pageobjects;


import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.locators.AddProductPageLocators;
import org.openqa.selenium.WebDriver;

import static com.softserveinc.edu.ita.utils.StringsGenerator.generateString;

public class AddProductPage extends LogOutBase {

    public AddProductPage(WebDriver driver) {
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

    public Product createRandomProduct() {
        return Product.newBuilder()
                .withoutId()
                .withoutStatus()
                .withProductName(generateString("NameSymbols", 5, 13))
                .withProductDescription(generateString("NameSymbols", 10, 25))
                .withProductPrice(Double.parseDouble(generateString("Digits", 1, 3)))
                .build();
    }

    public AddProductPage fillProductName(String productName) {
        driver.findElement(AddProductPageLocators.PRODUCT_NAME_FIELD.getBy()).clear();
        sendKeys(AddProductPageLocators.PRODUCT_NAME_FIELD, productName);
        return this;
    }

    public AddProductPage fillProductDescription(String productDescription) {
        driver.findElement(AddProductPageLocators.PRODUCT_DESCRIPTION_FIELD.getBy()).clear();
        sendKeys(AddProductPageLocators.PRODUCT_DESCRIPTION_FIELD, productDescription);
        return this;
    }

    public AddProductPage fillProductPrice(Double productPrice) {
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
