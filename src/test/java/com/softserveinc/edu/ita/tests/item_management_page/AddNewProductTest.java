package com.softserveinc.edu.ita.tests.item_management_page;


import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.pageobjects.AddProductPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.softserveinc.edu.ita.utils.DBUtility.deleteLastAddedProduct;
import static com.softserveinc.edu.ita.utils.DBUtility.getLastAddedProduct;

public class AddNewProductTest extends TestRunner {

    HomePage homePage;
    UserInfoPage userInfoPage;
    ItemManagementPage itemManagementPage;
    AddProductPage addProductPage;

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void cancelProductAddingTest(User user) {

        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        itemManagementPage = userInfoPage.clickItemManagementTab();

        int numberOfFoundProducts = itemManagementPage.getFoundProductsNumber();

        addProductPage = itemManagementPage.clickAddProductLink();

        loggingSoftAssert.assertTrue(addProductPage.getProductNameFieldText().isEmpty(),
                "<b>Product name field</b> is empty");
        loggingSoftAssert.assertTrue(addProductPage.getProductDescriptionFieldText().isEmpty(),
                "<b>Product description field</b> is empty");
        loggingSoftAssert.assertTrue(addProductPage.getProductPriceFieldText().isEmpty(),
                "<b>Product price field</b> is empty");

        Product newProduct = addProductPage.createRandomProduct();

        itemManagementPage = addProductPage.fillProductName(newProduct.getProductName())
                .fillProductDescription(newProduct.getProductDescription())
                .fillProductPrice(newProduct.getProductPrice())
                .clickCancelButton();

        loggingAssert.assertEquals(numberOfFoundProducts, itemManagementPage.getFoundProductsNumber(),
                String.format("Number of products didn't change: expected - <b>%s</b>; actual - <b>%s</b>;",
                        numberOfFoundProducts, itemManagementPage.getFoundProductsNumber()));

        loggingSoftAssert.assertAll();
    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void addNewProductTest(User user) {

        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        itemManagementPage = userInfoPage.clickItemManagementTab();

        int numberOfFoundProducts = itemManagementPage.getFoundProductsNumber();

        addProductPage = itemManagementPage.clickAddProductLink();

        Product newProduct = addProductPage.createRandomProduct();

        itemManagementPage = addProductPage.fillProductName(newProduct.getProductName())
                .fillProductDescription(newProduct.getProductDescription())
                .fillProductPrice(newProduct.getProductPrice())
                .clickOkButton();

        loggingAssert.assertEquals(newProduct.getProductName(), getLastAddedProduct().getProductName(),
                String.format("Product <b>%s</b> added to database", getLastAddedProduct().getProductName()));

        loggingAssert.assertEquals(numberOfFoundProducts + 1, itemManagementPage.getFoundProductsNumber(),
                String.format("Number of found products increased: expected - <b>%s</b>; actual - <b>%s</b>;",
                        numberOfFoundProducts + 1, itemManagementPage.getFoundProductsNumber()));

        itemManagementPage.clickLastButton();

        loggingAssert.assertEquals(newProduct.getProductName(),
                itemManagementPage.getElementText(ItemManagementPageLocators.LAST_PRODUCT_NAME),
                String.format("Name of the added product matches the last product in the table: expected - <b>%s</b>; actual - <b>%s</b>;",
                        newProduct.getProductName(),
                        itemManagementPage.getElementText(ItemManagementPageLocators.LAST_PRODUCT_NAME)));

        loggingAssert.assertEquals(newProduct.getProductDescription(),
                itemManagementPage.getElementText(ItemManagementPageLocators.LAST_PRODUCT_DESCRIPTION),
                String.format("Description of the added product matches the last product in the table: expected - <b>%s</b>; actual - <b>%s</b>;",
                        newProduct.getProductDescription(),
                        itemManagementPage.getElementText(ItemManagementPageLocators.LAST_PRODUCT_DESCRIPTION)));

        loggingAssert.assertEquals(newProduct.getProductPrice().toString(),
                itemManagementPage.getElementText(ItemManagementPageLocators.LAST_PRODUCT_PRICE),
                String.format("Price of the added product matches the last product in the table: expected - <b>%s</b>; actual - <b>%s</b>;",
                        newProduct.getProductPrice(),
                        itemManagementPage.getElementText(ItemManagementPageLocators.LAST_PRODUCT_PRICE)));

        deleteLastAddedProduct(getLastAddedProduct());

        loggingSoftAssert.assertAll();
    }


    @AfterMethod
    public void logOut() {
        itemManagementPage.clickLogOutButton();
    }
}
