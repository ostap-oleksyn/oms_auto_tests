package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.*;
import com.softserveinc.edu.ita.pageobjects.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TabsNavigationTest extends TestRunner {

    private HomePage homePage;
    private UserInfoPage userInfoPage;
    private OrderingPage orderingPage;

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void administratorTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(user.getRoleReference()), "User roles match");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB.setByWithoutParameter()),
                "User Info tab displayed");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ADMINISTRATION_TAB.setByWithoutParameter()),
                "Administration tab displayed");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.setByWithoutParameter())),
                "User info tab is the default tab");

        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        loggingAssert.assertTrue(administrationPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(administrationPage.getElementText(CommonLocators.ADMINISTRATION_TAB.setByWithoutParameter())),
                "Switched to Administration tab");

        loggingAssert.assertTrue(administrationPage.isElementDisplayed(AdministrationPageLocators.CREATE_NEW_USER_LINK),
                "Create new user link displayed");

        userInfoPage = administrationPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.setByWithoutParameter())),
                "Switched to User Info tab");
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void merchandiserTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(user.getRoleReference()), "Users roles match");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB.setByWithoutParameter()),
                "User Info tab displayed");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ORDERING_TAB.setByWithoutParameter()),
                "Ordering tab displayed");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.setByWithoutParameter())),
                "User info tab is the default tab");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(orderingPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(orderingPage.getElementText(CommonLocators.ORDERING_TAB.setByWithoutParameter())),
                "Switched to Ordering tab");

        loggingAssert.assertTrue(orderingPage.isElementDisplayed(OrderingPageLocators.CREATE_NEW_ORDER_LINK),
                "Create new order link displayed");

        userInfoPage = orderingPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.setByWithoutParameter())),
                "Switched to User Info tab");
    }

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void customerTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(user.getRoleReference()), "User rolees match");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB.setByWithoutParameter()),
                "User Info tab displayed");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ORDERING_TAB.setByWithoutParameter()),
                "Ordering tab displayed");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.setByWithoutParameter())),
                "User info tab is the default tab");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(orderingPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(orderingPage.getElementText(CommonLocators.ORDERING_TAB.setByWithoutParameter())),
                "Switched to Ordering tab");

        loggingAssert.assertTrue(orderingPage.isElementDisplayed(OrderingPageLocators.CREATE_NEW_ORDER_LINK),
                "Create new order link displayed");

        userInfoPage = orderingPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.setByWithoutParameter())),
                "Switched to User Info tab");
    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void supervisorTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(user.getRoleReference()), "User roles match");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB.setByWithoutParameter()),
                "User Info tab displayed");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ITEM_MANAGEMENT_TAB.setByWithoutParameter()),
                "Item Management displayed");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.setByWithoutParameter())),
                "User info tab is the default tab");

        ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();

        loggingAssert.assertTrue(itemManagementPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(itemManagementPage.getElementText(CommonLocators.ITEM_MANAGEMENT_TAB.setByWithoutParameter())),
                "Switched to Item Management tab");

        loggingAssert.assertTrue(itemManagementPage.isElementDisplayed(ItemManagementPageLocators.ADD_PRODUCT_LINK),
                "Add Product link displayed");

        userInfoPage = itemManagementPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.setByWithoutParameter())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.setByWithoutParameter())),
                "Switched to User Info tab");
    }

    @AfterMethod
    public void afterMethod() {
        userInfoPage.clickLogOutButton();
    }
}
