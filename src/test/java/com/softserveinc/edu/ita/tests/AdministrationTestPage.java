package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.locators.OrderingPageLocators;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AdministrationTestPage extends TestRunner {

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void validLoginTest(User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        Assert.assertTrue(driver.findElement(UserInfoPageLocators.USER_INFO_ELEMENT).isDisplayed());

        homePage.clickAdministrationButton();
        //driver.findElement(AdministrationPageLocators.ADMINISTRATION_PAGE_TAB).click();

        Assert.assertTrue(driver.findElement(AdministrationPageLocators.ADMINISTRATION_PAGE_TEXT)
                .getText().contains("This page is appointed for creating new and managing existing users"));

        Assert.assertTrue(driver.findElement(AdministrationPageLocators.CREATE_NEW_USER_BUTTON).isDisplayed());

        //driver.findElement(OrderingPageLocators.ORDERING_PAGE_TAB_).click();
        homePage.clickOrederingButton();

        Assert.assertTrue(driver.findElement(OrderingPageLocators.ORDERING_TABLE_FIRST_ELEMENT_TEXT)
                .getText().contains("Order Name"));

        Assert.assertTrue(driver.findElement(OrderingPageLocators.CREATE_NEW_ORDER_BUTTON).isDisplayed());

        userInfoPage.clickLogOutButton();
    }

}


