package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dao_jdbc.classes.User;
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
    public void validLoginTest(User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        Assert.assertTrue(userInfoPage.getUserRoleLabel()
                        .equals(user.getRole),
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
    public void logOutTest(User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        Assert.assertTrue(userInfoPage.getUserRoleLabel()
                        .equals(user.getRole),
                "Logged in user role is incorrect");
        userInfoPage.clickLogOutButton();
        Assert.assertTrue(homePage.getLoginInputField()
                        .isDisplayed(),
                "User didn't logged out");
    }

}
