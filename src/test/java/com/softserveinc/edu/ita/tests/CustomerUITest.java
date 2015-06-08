package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.OrderingPageLocators;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.OrderingPage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerUITest extends TestRunner {

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void administratorTabsTest(User user) {
        final HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        Assert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                        .equals(user.getRoleName()),
                "Logged in user role is incorrect");

        Assert.assertTrue(userInfoPage.isElementDisplayed(UserInfoPageLocators.USER_INFO_TAB),
                "User Info tab is not displayed");
        Assert.assertTrue(userInfoPage.isElementDisplayed(UserInfoPageLocators.ORDERING_TAB),
                "Ordering tab is not displayed");

        Assert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(UserInfoPageLocators.USER_INFO_TAB)),
                "User Info tab is not the default tab");

        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        Assert.assertTrue(orderingPage.getElementText(UserInfoPageLocators.ACTIVE_TAB)
                        .equals(orderingPage.getElementText(UserInfoPageLocators.ORDERING_TAB)),
                "Didn't switch to Ordering tab");

        Assert.assertTrue(orderingPage.isElementDisplayed(OrderingPageLocators.CREATE_NEW_ORDER_LINK),
                "Create new order link is not displayed");

        userInfoPage = orderingPage.clickUserInfoTab();

        Assert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(UserInfoPageLocators.USER_INFO_TAB)),
                "Didn't switch to User Info tab");

        userInfoPage.clickLogOutButton();
    }
}
