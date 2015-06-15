package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.*;
import com.softserveinc.edu.ita.page_object.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TabsNavigationTest extends TestRunner {

    HomePage homePage;
    UserInfoPage userInfoPage;
    AdministrationPage administrationPage;
    OrderingPage orderingPage;
    ItemManagementPage itemManagementPage;

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void administratorTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL.getBy())
                .equals(user.getRoleName()), "User role assert");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB.getBy()),
                "User Info tab display assert");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ADMINISTRATION_TAB.getBy()),
                "Administration tab display assert");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.getBy())),
                "Default tab assert");

        administrationPage = userInfoPage.clickAdministrationTab();

        loggingAssert.assertTrue(administrationPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(administrationPage.getElementText(CommonLocators.ADMINISTRATION_TAB.getBy())),
                "Switch to Administration tab assert");

        loggingAssert.assertTrue(administrationPage.isElementDisplayed(AdministrationPageLocators.CREATE_NEW_USER_LINK.getBy()),
                "Create new user link display assert");

        userInfoPage = administrationPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.getBy())),
                "Switch to User Info tab assert");
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void merchandiserTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL.getBy())
                .equals(user.getRoleName()), "User role assert");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB.getBy()),
                "User Info tab display assert");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ORDERING_TAB.getBy()),
                "Ordering tab display assert");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.getBy())),
                "Default tab assert");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(orderingPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(orderingPage.getElementText(CommonLocators.ORDERING_TAB.getBy())),
                "Switch to Ordering tab assert");

        loggingAssert.assertTrue(orderingPage.isElementDisplayed(OrderingPageLocators.CREATE_NEW_ORDER_LINK.getBy()),
                "Create new order link displayed assert");

        userInfoPage = orderingPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.getBy())),
                "Switch to User Info tab assert");
    }

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void customerTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL.getBy())
                .equals(user.getRoleName()), "User role assert");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB.getBy()),
                "User Info tab display assert");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ORDERING_TAB.getBy()),
                "Ordering tab display assert");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.getBy())),
                "Default tab assert");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(orderingPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(orderingPage.getElementText(CommonLocators.ORDERING_TAB.getBy())),
                "Switch to Ordering tab assert");

        loggingAssert.assertTrue(orderingPage.isElementDisplayed(OrderingPageLocators.CREATE_NEW_ORDER_LINK.getBy()),
                "Create new order link display assert");

        userInfoPage = orderingPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.getBy())),
                "Switch to User Info tab assert");
    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void supervisorTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL.getBy())
                .equals(user.getRoleName()), "User role assert");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB.getBy()),
                "User Info tab display assert");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ITEM_MANAGEMENT_TAB.getBy()),
                "Item Management display assert");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.getBy())),
                "Default tab assert");

        itemManagementPage = userInfoPage.clickItemManagementTab();

        loggingAssert.assertTrue(itemManagementPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(itemManagementPage.getElementText(CommonLocators.ITEM_MANAGEMENT_TAB.getBy())),
                "Switch to Item Management tab assert");

        loggingAssert.assertTrue(itemManagementPage.isElementDisplayed(ItemManagementPageLocators.ADD_PRODUCT_LINK.getBy()),
                "Add Product link display assert");

        userInfoPage = itemManagementPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB.getBy())
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB.getBy())),
                "Switch to User Info tab assert");
    }

    @AfterMethod
    public void afterMethod() {
        userInfoPage.clickLogOutButton();
    }
}
