package com.softserveinc.edu.ita.tests.item_management_page;

import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ProductStatus;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.utils.RandomUtil;
import org.openqa.selenium.Alert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class DeleteProductTest extends TestRunner {

    private HomePage homePage;
    private UserInfoPage userInfoPage;
    private ItemManagementPage itemManagementPage;
    private static final String ALERT_MESSAGE = "Are you sure you want to delete this product?";


    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void cancelProductDeleteingTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        itemManagementPage = userInfoPage.clickItemManagementTab();

        final int numberOfFoundProducts = itemManagementPage.getFoundProductsNumber();
        final int numberOfRows = itemManagementPage.getNumberOfRows();
        final int randomRow = RandomUtil.getRandomInteger(1, numberOfRows);

        itemManagementPage.deleteRandomProduct(randomRow);

        final Alert confirmDelete = driver.switchTo().alert();

        loggingSoftAssert.assertTrue(confirmDelete.getText().equals(ALERT_MESSAGE),
                "Confirmation alert message appeared");

        confirmDelete.dismiss();

        loggingAssert.assertEquals(numberOfFoundProducts, itemManagementPage.getFoundProductsNumber(),
                String.format("Found products number didn't change: expected - <b>%s</b>; actual - <b>%s</b>;",
                        numberOfFoundProducts, itemManagementPage.getFoundProductsNumber()));

        loggingSoftAssert.assertAll();
    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void deleteProductTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        itemManagementPage = userInfoPage.clickItemManagementTab();

        final int numberOfFoundProducts = itemManagementPage.getFoundProductsNumber();
        final int numberOfRows = itemManagementPage.getNumberOfRows();
        final int randomRow = RandomUtil.getRandomInteger(1, numberOfRows);

        final Product product = itemManagementPage.getRandomProduct(randomRow);
        itemManagementPage.deleteRandomProduct(randomRow);

        final Alert confirmDelete = driver.switchTo().alert();

        loggingSoftAssert.assertTrue(confirmDelete.getText().equals(ALERT_MESSAGE),
                "Confirmation alert message appeared");

        confirmDelete.accept();

        loggingAssert.assertEquals(numberOfFoundProducts - 1, itemManagementPage.getFoundProductsNumber(),
                String.format("Product deleted. Found products number changed: expected - <b>%s</b>; actual - <b>%s</b>;",
                        numberOfFoundProducts - 1, itemManagementPage.getFoundProductsNumber()));

        loggingAssert.assertTrue(
                DBUtility.getProductStatus(product.getProductName(), product.getProductDescription()) ==
                        ProductStatus.INACTIVE.getStatus(),
                String.format("Product <b>%s</b> status changed to 0", product.getProductName()));

        DBUtility.setProductStatus(product.getProductName(), product.getProductDescription(), ProductStatus.ACTIVE);

        loggingAssert.assertTrue(
                DBUtility.getProductStatus(product.getProductName(), product.getProductDescription()) == ProductStatus.ACTIVE.getStatus(),
                String.format("Product <b>%s</b> status changed to 1", product.getProductName()));

        loggingSoftAssert.assertAll();
    }

    @AfterMethod
    public void logOut() {
        itemManagementPage.clickLogOutButton();
    }
}
