package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdministratorUITest extends TestRunner{

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
}
