package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * This enum includes two type of locators:
 * the first type locators are used without preliminary preparation;
 * the second type locators can be used after advance modification.
 */
public enum AddProductPageLocators implements ILocator {

    PRODUCT_NAME_FIELD(
            "Product name field",
            LocatorsType.BY_XPATH,
            ".//*[@id='name']"),
    PRODUCT_DESCRIPTION_FIELD(
            "Product description field",
            LocatorsType.BY_XPATH,
            ".//*[@id='description']"),
    PRODUCT_PRICE_FIELD(
            "Product price field",
            LocatorsType.BY_XPATH,
            ".//*[@id='price']"),
    OK_BUTTON(
            "Ok button",
            LocatorsType.BY_XPATH,
            ".//*[@id='productModel']/input[2]"),
    CANCEL_BUTTON(
            "Cancel button",
            LocatorsType.BY_XPATH,
            ".//*[@id='productModel']/input[3]");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    AddProductPageLocators(String name, LocatorsType locatorsType, String rawLocator) {
        this.name = name;
        this.locatorsType = locatorsType;
        this.rawLocator = rawLocator;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //This method prepares locator using additional parameter by means of so called "string-format" method.
    public AddProductPageLocators modify(String parameter) {
        this.modifiedLocator = String.format(this.rawLocator, parameter);
        return this;
    }

    @Override
    //This method converts locator into "By" format.
    public By getBy() {
        //This block of code is used to leave raw locator intact giving a possibility to use parameterized locator again.
        if (this.modifiedLocator == null) {
            this.byLocator = this.locatorsType.getBy(this.rawLocator);
        } else {
            this.byLocator = this.locatorsType.getBy(this.modifiedLocator);
        }
        return this.byLocator;
    }
}
