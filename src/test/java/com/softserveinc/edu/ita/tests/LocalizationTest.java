package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.*;
import com.softserveinc.edu.ita.pageobjects.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LocalizationTest extends TestRunner {

    HomePage homePage;
    UserInfoPage userInfoPage;
    AdministrationPage administrationPage;
    OrderingPage orderingPage;
    ItemManagementPage itemManagementPage;

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void administratorTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_LABEL)
                .equals("User Info"), "User info label is in English");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_LABEL)
                .equals("First name"), "User first name is in English");

        userInfoPage.clickLanguageLink(CommonLocators.UA_LINK);

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_LABEL)
                .equals("Деталі Користувача"), "Changing User info to Ukrainian");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_LABEL)
                .equals("Ім'я"), "Changing User first name to Ukrainian");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.SEARCH_FILTER_LABEL)
                .equals("Шукати замовлення за:"), "Changing Filter label to Ukrainian");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.EDIT_TABLE_LABEL)
                .equals("Редагувати"), "Changing Edit table label to Ukrainian");

        administrationPage = userInfoPage.clickAdministrationTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(AdministrationPageLocators.ADMINISTRATOR_APPOINTED_LABEL.setByWithoutParameter())
                .equals("Дана сторінка призначена для створення " +
                        "та керування користувачами"), "Changing Administration appointed label to Ukrainian");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(AdministrationPageLocators.FILTER_LABEL.setByWithoutParameter())
                .equals("Фільтр на поле:"), "Changing Filter label to Ukrainian");

        userInfoPage = orderingPage.clickUserInfoTab();

        userInfoPage.clickLanguageLink(CommonLocators.EN_LINK);
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void merchandiserTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_LABEL)
                .equals("User Info"), "User info is in English");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_LABEL)
                .equals("First name"), "User first name is in English");

        userInfoPage.clickLanguageLink(CommonLocators.UA_LINK);

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_LABEL)
                .equals("Деталі Користувача"), "Changing User info to Ukrainian");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_LABEL)
                .equals("Ім'я"), "Changing User first name label to Ukrainian");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.SEARCH_FILTER_LABEL)
                .equals("Шукати замовлення за:"), "Changing Search filter label to Ukrainian");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.EDIT_TABLE_LABEL)
                .equals("Редагувати"), "Changing Edit table label to Ukrainian");

        userInfoPage = orderingPage.clickUserInfoTab();

        userInfoPage.clickLanguageLink(CommonLocators.EN_LINK);
    }

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void customerTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_LABEL)
                .equals("User Info"), "User info is in English");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_LABEL)
                .equals("First name"), "First name title is in English");

        userInfoPage.clickLanguageLink(CommonLocators.UA_LINK);

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_LABEL)
                .equals("Деталі Користувача"), "Changing User info to Ukrainian");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_LABEL)
                .equals("Ім'я"), "Changing User first name title to Ukrainian");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.SEARCH_FILTER_LABEL)
                .equals("Шукати замовлення за:"), "Changing Search filter label to Ukrainian");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.EDIT_TABLE_LABEL)
                .equals("Редагувати"), "Changing Edit table label to Ukrainian");

        userInfoPage = orderingPage.clickUserInfoTab();

        userInfoPage.clickLanguageLink(CommonLocators.EN_LINK);

    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void supervisorTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_LABEL)
                .equals("User Info"), "User info is in English");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_LABEL)
                .equals("First name"), "First name title is in English");

        userInfoPage.clickLanguageLink(CommonLocators.UA_LINK);

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_LABEL)
                .equals("Деталі Користувача"), "Changing User info to Ukrainian");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_LABEL)
                .equals("Ім'я"), "Changing First name title to Ukrainian");

        itemManagementPage = userInfoPage.clickItemManagementTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(ItemManagementPageLocators.SUPERVISOR_APPOINTED_LABEL)
                .equals("Дана сторінка призначена для додавання нових та " +
                        "редагування існуючих продуктів."), "Changing Supervisor appointed label to Ukrainian");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(ItemManagementPageLocators.SUPERVISOR_FILTER_LABEL)
                .equals("Фільтр на поле:"), "Changing Filter label appointed label to Ukrainian");

        userInfoPage = itemManagementPage.clickUserInfoTab();

        userInfoPage.clickLanguageLink(CommonLocators.EN_LINK);
    }

    @AfterMethod
    public void afterMethod () {
        userInfoPage.clickLogOutButton();
    }
}




