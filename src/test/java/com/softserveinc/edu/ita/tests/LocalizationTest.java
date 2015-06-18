package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.*;
import com.softserveinc.edu.ita.page_object.*;
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
                .getElementText(UserInfoPageLocators.USER_INFO_TITLE_TEXT)
                .equals("User Info"), "Verify EN title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_TEXT)
                .equals("First name"), "Verify EN text present");

        userInfoPage.clickLanguageTo(CommonLocators.UA_BUTTON);

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_TITLE_TEXT)
                .equals("Деталі Користувача"), "Verify UA title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_TEXT)
                .equals("Ім'я"), "Verify UA text present");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.SEARCH_FILTER_TEXT)
                .equals("Шукати замовлення за:"), "Verify UA filter title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.EDIT_TABLE_TEXT)
                .equals("Редагувати"), "Verify UA table text 'Edit'");

        administrationPage = userInfoPage.clickAdministrationTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(AdministrationPageLocators.ADMINISTRATOR_APPOINTED_TEXT)
                .equals("Дана сторінка призначена для створення " +
                        "та керування користувачами"), "Verify UA admin appointed text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(AdministrationPageLocators.FIELD_FILTER_TEXT)
                .equals("Фільтр на поле:"), "Verify UA filter field text");

        userInfoPage = orderingPage.clickUserInfoTab();

        userInfoPage.clickLanguageTo(CommonLocators.EN_BUTTON);
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void merchandiserTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_TITLE_TEXT)
                .equals("User Info"), "Verify EN title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_TEXT)
                .equals("First name"), "Verify EN text present");

        userInfoPage.clickLanguageTo(CommonLocators.UA_BUTTON);

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_TITLE_TEXT)
                .equals("Деталі Користувача"), "Verify UA title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_TEXT)
                .equals("Ім'я"), "Verify UA text present");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.SEARCH_FILTER_TEXT)
                .equals("Шукати замовлення за:"), "Verify UA filter title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.EDIT_TABLE_TEXT)
                .equals("Редагувати"), "Verify UA table text 'Edit'");

        userInfoPage = orderingPage.clickUserInfoTab();

        userInfoPage.clickLanguageTo(CommonLocators.EN_BUTTON);
    }

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void customerTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_TITLE_TEXT)
                .equals("User Info"), "Verify EN title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_TEXT)
                .equals("First name"), "Verify EN text present");

        userInfoPage.clickLanguageTo(CommonLocators.UA_BUTTON);

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_TITLE_TEXT)
                .equals("Деталі Користувача"), "Verify UA title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_TEXT)
                .equals("Ім'я"), "Verify UA text present");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.SEARCH_FILTER_TEXT)
                .equals("Шукати замовлення за:"), "Verify UA filter title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.EDIT_TABLE_TEXT)
                .equals("Редагувати"), "Verify UA table text 'Edit'");

        userInfoPage = orderingPage.clickUserInfoTab();

        userInfoPage.clickLanguageTo(CommonLocators.EN_BUTTON);

    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void supervisorTabsTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_TITLE_TEXT)
                .equals("User Info"), "Verify EN title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_TEXT)
                .equals("First name"), "Verify EN text present");

        userInfoPage.clickLanguageTo(CommonLocators.UA_BUTTON);

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_INFO_TITLE_TEXT)
                .equals("Деталі Користувача"), "Verify UA title text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_TITLE_TEXT)
                .equals("Ім'я"), "Verify UA text present");

        itemManagementPage = userInfoPage.clickItemManagementTab();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(ItemManagementPageLocators.SUPERVISOR_APPOINTED_TEXT)
                .equals("Дана сторінка призначена для додавання нових та " +
                        "редагування існуючих продуктів."), "Verify UA Supervisor appointed text present");
        loggingAssert.assertTrue(userInfoPage
                .getElementText(ItemManagementPageLocators.SUPERVISOR_FIELD_FILTER_TEXT)
                .equals("Фільтр на поле:"), "Verify UA filter field text");

        userInfoPage = itemManagementPage.clickUserInfoTab();

        userInfoPage.clickLanguageTo(CommonLocators.EN_BUTTON);
    }

    @AfterMethod
        public void afterMethod () {
            userInfoPage.clickLogOutButton();
        }
    }




