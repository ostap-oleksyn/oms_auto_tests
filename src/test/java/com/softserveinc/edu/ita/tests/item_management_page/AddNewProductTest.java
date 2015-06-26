package com.softserveinc.edu.ita.tests.item_management_page;


import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.pageobjects.AddProductPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

public class AddNewProductTest extends TestRunner {


    HomePage homePage;
    UserInfoPage userInfoPage;
    ItemManagementPage itemManagementPage;
    AddProductPage addProductPage;

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void cancelProductAdding(User user) {


        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        itemManagementPage = userInfoPage.clickItemManagementTab();

        int numberOfFoundProduts = itemManagementPage.getFoundProductsNumber();

        addProductPage = itemManagementPage.clickAddProductLink();

        Product randomProduct = addProductPage.createRandomProduct();

        addProductPage.fillProductName(randomProduct.getProductName())
                .fillProductDescription(randomProduct.getProductDescription())
                .fillProductPrice(randomProduct.getProductPrice());

        itemManagementPage = addProductPage.clickCancelButton();

        loggingAssert.assertEquals(numberOfFoundProduts, itemManagementPage.getFoundProductsNumber(),
                String.format("Number of products didn't change: expected - <b>%s</b>; actual - <b>%s</b>",
                        numberOfFoundProduts, itemManagementPage.getFoundProductsNumber()));

    }


}
