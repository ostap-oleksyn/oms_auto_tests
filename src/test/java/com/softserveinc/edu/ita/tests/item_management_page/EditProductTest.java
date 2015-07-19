package com.softserveinc.edu.ita.tests.item_management_page;


import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.pageobjects.EditProductPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.utils.RandomUtil;
import com.softserveinc.edu.ita.utils.TestRunner;
import org.testng.annotations.Test;

/**
 * Class to test editing existing product functionality on item management page.
 */
public class EditProductTest extends TestRunner {

    private static final String TEST_PRODUCT_NAME = "Test name";
    private static final String TEST_PRODUCT_DESCRIPTION = "Test description";
    private static final Double TEST_PRODUCT_PRICE = 28.0;

    private HomePage homePage;
    private UserInfoPage userInfoPage;
    private ItemManagementPage itemManagementPage;
    private EditProductPage editProductPage;


    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void testCancelProductEditing(final User user) {

        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        itemManagementPage = userInfoPage.clickItemManagementTab();

        final int numberOfRows = itemManagementPage.getNumberOfRows();
        final int randomRow = RandomUtil.getRandomInteger(1, numberOfRows);
        final Product randomProductBeforeEdit = itemManagementPage.getRandomProduct(randomRow);

        editProductPage = itemManagementPage.editRandomProduct(randomRow);

        itemManagementPage = editProductPage.fillProductName(TEST_PRODUCT_NAME)
                .fillProductDescription(TEST_PRODUCT_DESCRIPTION)
                .fillProductPrice(String.valueOf(TEST_PRODUCT_PRICE))
                .clickCancelButton();

        final Product randomProductAfterEdit = itemManagementPage.getRandomProduct(randomRow);

        loggingAssert.assertEquals(
                randomProductBeforeEdit.getProductName(), randomProductAfterEdit.getProductName(),
                String.format("Product name didn't changed: <b>%s</b>", randomProductBeforeEdit.getProductName()));

        loggingAssert.assertEquals(
                randomProductBeforeEdit.getProductDescription(), randomProductAfterEdit.getProductDescription(),
                String.format("Product description didn't changed: <b>%s</b>", randomProductBeforeEdit.getProductDescription()));

        loggingAssert.assertEquals(
                randomProductBeforeEdit.getProductPrice(), randomProductAfterEdit.getProductPrice(),
                String.format("Product price didn't changed: <b>%s</b>", randomProductBeforeEdit.getProductPrice()));

        itemManagementPage.clickLogOutButton();
    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void testProductEditing(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        itemManagementPage = userInfoPage.clickItemManagementTab();

        final int numberOfRows = itemManagementPage.getNumberOfRows();
        final int randomRow = RandomUtil.getRandomInteger(1, numberOfRows);
        final Product randomProduct = itemManagementPage.getRandomProduct(randomRow);

        editProductPage = itemManagementPage.editRandomProduct(randomRow);

        itemManagementPage = editProductPage.fillProductName(TEST_PRODUCT_NAME)
                .fillProductDescription(TEST_PRODUCT_DESCRIPTION)
                .fillProductPrice(String.valueOf(TEST_PRODUCT_PRICE))
                .clickOkButton();

        Product databaseProduct = DBUtility.getProduct(TEST_PRODUCT_NAME, TEST_PRODUCT_DESCRIPTION);

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_NAME_CELL.modify(String.valueOf(randomRow))),
                TEST_PRODUCT_NAME, String.format("Product name changed to <b>%s</b>", TEST_PRODUCT_NAME));

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_DESCRIPTION_CELL.modify(String.valueOf(randomRow))),
                TEST_PRODUCT_DESCRIPTION, String.format("Product description changed to <b>%s</b>", TEST_PRODUCT_DESCRIPTION));

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_PRICE_CELL.modify(String.valueOf(randomRow))),
                TEST_PRODUCT_PRICE.toString(), String.format("Product price changed to <b>%s</b>", TEST_PRODUCT_PRICE));


        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_NAME_CELL.modify(String.valueOf(randomRow))),
                databaseProduct.getProductName(), "Product name changed in database");

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_DESCRIPTION_CELL.modify(String.valueOf(randomRow))),
                databaseProduct.getProductDescription(), "Product description changed in database");

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_PRICE_CELL.modify(String.valueOf(randomRow))),
                databaseProduct.getProductPrice().toString(), "Product price changed in database");

        editProductPage = itemManagementPage.editRandomProduct(randomRow);

        itemManagementPage = editProductPage.fillProductName(randomProduct.getProductName())
                .fillProductDescription(randomProduct.getProductDescription())
                .fillProductPrice(String.valueOf(randomProduct.getProductPrice()))
                .clickOkButton();

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_NAME_CELL.modify(String.valueOf(randomRow))),
                randomProduct.getProductName(),
                String.format("Product name changed back to <b>%s</b>", randomProduct.getProductName()));

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_DESCRIPTION_CELL.modify(String.valueOf(randomRow))),
                randomProduct.getProductDescription(),
                String.format("Product description changed back to <b>%s</b>", randomProduct.getProductDescription()));

        loggingAssert.assertEquals(
                itemManagementPage.getElementText(ItemManagementPageLocators.TABLE_PRICE_CELL.modify(String.valueOf(randomRow))),
                String.valueOf(randomProduct.getProductPrice()),
                String.format("Product price changed back to <b>%s</b>", randomProduct.getProductPrice()));

        databaseProduct = DBUtility.getProduct(randomProduct.getProductName(), randomProduct.getProductDescription());

        loggingAssert.assertEquals(databaseProduct.getProductName(), randomProduct.getProductName(),
                "Product name changed back in database");

        loggingAssert.assertEquals(
                databaseProduct.getProductDescription(), randomProduct.getProductDescription(),
                "Product description changed back in database");

        loggingAssert.assertEquals(
                databaseProduct.getProductPrice(), randomProduct.getProductPrice(),
                "Product price changed back in database");

        itemManagementPage.clickLogOutButton();
    }
}
