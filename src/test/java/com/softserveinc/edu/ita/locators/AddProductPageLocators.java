package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AddProductPageLocators implements ILocator {

    PRODUCT_NAME_FIELD(
            "Product name field",
            By.xpath(".//*[@id='name']")),
    PRODUCT_DESCRIPTION_FIELD(
            "Product description field",
            By.xpath(".//*[@id='description']")),
    PRODUCT_PRICE_FIELD(
            "Product price field",
            By.xpath(".//*[@id='price']")),
    OK_BUTTON(
            "Ok button",
            By.xpath(".//*[@id='productModel']/input[2]")),
    CANCEL_BUTTON(
            "Cancel button",
            By.xpath(".//*[@id='productModel']/input[3]"));

    AddProductPageLocators(String name, By locator) {
        this.name = name;
        this.locator = locator;
    }

    private String name;
    private By locator;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public By getBy() {
        return this.locator;
    }
}
