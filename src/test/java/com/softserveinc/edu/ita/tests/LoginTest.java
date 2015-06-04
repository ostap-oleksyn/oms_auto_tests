package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestRunner {

    final String errorMessage = "Your login attempt was not successful, try again.";

    @Test
    public void emptyLoginTest() {
        final HomePage homePage = new HomePage(driver);
        homePage.clickSubmitButton();
        Assert.assertTrue(homePage.getErrorMessage()
                        .contains(errorMessage),
                "Error message is not displayed");

    }

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void validLoginTest(String login, String password, String role) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(login, password);

        Assert.assertTrue(userInfoPage.getUserRoleLabel()
                        .equals(role),
                "Logged in user role is incorrect");
        userInfoPage.clickLogOutButton();
    }

    @Test(dataProvider = "getInvalidUsers", dataProviderClass = DataProviders.class)
    public void invalidLoginTest(String login, String password) {
        final HomePage homePage = new HomePage(driver);
        homePage.logIn(login, password);

        Assert.assertTrue(homePage.getErrorMessage()
                        .contains(errorMessage),
                "Error message is not displayed");
    }

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void logOutTest(String login, String password, String role) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(login, password);

        Assert.assertTrue(userInfoPage.getUserRoleLabel()
                        .equals(role),
                "Logged in user role is incorrect");
        userInfoPage.clickLogOutButton();
        Assert.assertTrue(homePage.getLoginInputField()
                        .isDisplayed(),
                "User didn't logged out");
    }

}
