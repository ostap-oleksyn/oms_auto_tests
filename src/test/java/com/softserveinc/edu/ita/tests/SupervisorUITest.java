package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.ItemManagementPage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SupervisorUITest extends TestRunner {

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
