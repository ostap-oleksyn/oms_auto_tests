package com.softserveinc.edu.ita.tests.item_management_page;


import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.pageobjects.EditProductPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.StringsGenerator;
import org.testng.annotations.Test;

public class EditProductTest extends TestRunner {

    EditProductPage editProductPage;
    private static final String TEST_PRODUCT_NAME = "Test name";
    private static final String TEST_PRODUCT_DESCRIPTION = "Test description";
    private static final Double TEST_PRICE = 28.0;


    @Test
    public void cancelProductDeleteingTest() {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn("login2", "qwerty");
        ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();

        final int numberOfRows = itemManagementPage.getNumberOfRows();
        final int randomRow = StringsGenerator.getRandomNumber(1, numberOfRows);
        Product randomProduct = itemManagementPage.getRandomProduct(randomRow);

        editProductPage = itemManagementPage.editRandomProduct(randomRow);

        itemManagementPage = editProductPage.fillProductName(TEST_PRODUCT_NAME)
                .fillProductDescription(TEST_PRODUCT_DESCRIPTION)
                .fillProductPrice(String.valueOf(TEST_PRICE))
                .clickOkButton();

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_NAME_CELL.modify(String.valueOf(randomRow))),
                TEST_PRODUCT_NAME);

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_DESCRIPTION_CELL.modify(String.valueOf(randomRow))),
                TEST_PRODUCT_DESCRIPTION);

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_PRICE_CELL.modify(String.valueOf(randomRow))),
                TEST_PRICE.toString());

        editProductPage = itemManagementPage.editRandomProduct(randomRow);

        itemManagementPage = editProductPage.fillProductName(randomProduct.getProductName())
                .fillProductDescription(randomProduct.getProductDescription())
                .fillProductPrice(String.valueOf(randomProduct.getProductPrice()))
                .clickOkButton();

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_NAME_CELL.modify(String.valueOf(randomRow))),
                randomProduct.getProductName());

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_DESCRIPTION_CELL.modify(String.valueOf(randomRow))),
                randomProduct.getProductDescription());

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_PRICE_CELL.modify(String.valueOf(randomRow))),
                String.valueOf(randomProduct.getProductPrice()));

    }
}
