package com.softserveinc.edu.ita.pageobjects;


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
                .withProductName(generateString("NameSymbols", 1, 13))
                .withProductDescription(generateString("NameSymbols", 1, 25))
                .withProductPrice("Digits", 1, 3)
                .build();
    }


}
