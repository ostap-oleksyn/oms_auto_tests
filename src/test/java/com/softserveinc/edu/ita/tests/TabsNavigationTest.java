package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.locators.OrderingPageLocators;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.page_object.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TabsNavigationTest extends TestRunner {

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void administratorTabsTest(User user) {
        final HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        Assert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                        .equals(user.getRoleName()),
                "Logged in user role is incorrect");

        Assert.assertTrue(userInfoPage.isElementDisplayed(UserInfoPageLocators.USER_INFO_TAB),
                "User Info tab is not displayed");
        Assert.assertTrue(userInfoPage.isElementDisplayed(UserInfoPageLocators.ADMINISTRATION_TAB),
                "Administration tab is not displayed");

        Assert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(UserInfoPageLocators.USER_INFO_TAB)),
                "User Info tab is not the default tab");

        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        Assert.assertTrue(administrationPage.getElementText(UserInfoPageLocators.ACTIVE_TAB)
                        .equals(administrationPage.getElementText(UserInfoPageLocators.ADMINISTRATION_TAB)),
                "Didn't switch to Administration tab");

        Assert.assertTrue(administrationPage.isElementDisplayed(AdministrationPageLocators.CREATE_NEW_USER_LINK),
                "Create new user link is not displayed");

        userInfoPage = administrationPage.clickUserInfoTab();

        Assert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(UserInfoPageLocators.USER_INFO_TAB)),
                "Didn't switch to User Info tab");

        userInfoPage.clickLogOutButton();
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void merchandiserTabsTest(User user) {
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

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void customerTabsTest(User user) {
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

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void supervisorTabsTest(User user) {
        final HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        Assert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                        .equals(user.getRoleName()),
                "Logged in user role is incorrect");

        Assert.assertTrue(userInfoPage.isElementDisplayed(UserInfoPageLocators.USER_INFO_TAB),
                "User Info tab is not displayed");
        Assert.assertTrue(userInfoPage.isElementDisplayed(UserInfoPageLocators.USER_ITEM_MANAGEMENT_TAB),
                "Item Management tab is not displayed");

        Assert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(UserInfoPageLocators.USER_INFO_TAB)),
                "User Info tab is not the default tab");

        final ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();

        Assert.assertTrue(itemManagementPage.getElementText(UserInfoPageLocators.ACTIVE_TAB)
                        .equals(itemManagementPage.getElementText(UserInfoPageLocators.USER_ITEM_MANAGEMENT_TAB)),
                "Didn't switch to Item Management tab");

        Assert.assertTrue(itemManagementPage.isElementDisplayed(ItemManagementPageLocators.ADD_PRODUCT_LINK),
                "Add Product link is not displayed");

        userInfoPage = itemManagementPage.clickUserInfoTab();

        Assert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(UserInfoPageLocators.USER_INFO_TAB)),
                "Didn't switch to User Info tab");

    }
}
