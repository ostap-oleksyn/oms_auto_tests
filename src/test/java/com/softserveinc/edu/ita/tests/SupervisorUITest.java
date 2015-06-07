package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
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

        Assert.assertTrue(userInfoPage.getUserRoleLabel()
                        .equals(user.getRoleName()),
                "Logged in user role is incorrect");

        Assert.assertTrue(userInfoPage.getUserInfoTab().isDisplayed(),
                "User Info tab is not displayed");
        Assert.assertTrue(userInfoPage.getItemManagementTab().isDisplayed(),
                "Item Management tab is not displayed");

        Assert.assertTrue(userInfoPage.getActiveTab().getText()
                        .equals(userInfoPage.getUserInfoTab().getText()),
                "User Info tab is not the default tab");

        final ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();

        Assert.assertTrue(itemManagementPage.getActiveTab().getText()
                        .equals(itemManagementPage.getItemManagementTab().getText()),
                "Didn't switch to Item Management tab");

        Assert.assertTrue(itemManagementPage.getAddProductLink().isDisplayed(),
                "Add Product link is not displayed");

        userInfoPage = itemManagementPage.clickUserInfoTab();

        Assert.assertTrue(userInfoPage.getActiveTab().getText()
                        .equals(userInfoPage.getUserInfoTab().getText()),
                "Didn't switch to User Info tab");

    }
}
