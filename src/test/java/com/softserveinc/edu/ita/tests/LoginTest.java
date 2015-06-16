package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.HomePageLocators;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.annotations.Test;

public class LoginTest extends TestRunner {

    private HomePage homePage;
    private UserInfoPage userInfoPage;

    @Test
    public void emptyLoginTest() {
        homePage = new HomePage(driver);
        homePage.clickSubmitButton();
        loggingAssert.assertTrue(homePage.isElementDisplayed(HomePageLocators.LOGIN_ERROR_MESSAGE), "Error message displayed");

    }

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void validLoginTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(user.getRoleName()), "User roles match");

        userInfoPage.clickLogOutButton();
    }

    @Test(dataProvider = "getInvalidUsers", dataProviderClass = DataProviders.class)
    public void invalidLoginTest(String login, String password) {
        homePage = new HomePage(driver);
        homePage.logIn(login, password);

        loggingAssert.assertTrue(homePage.isElementDisplayed(HomePageLocators.LOGIN_ERROR_MESSAGE), "Error message displayed");
    }

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void logOutTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(user.getRoleName()), "User roles match");
        userInfoPage.clickLogOutButton();
        loggingAssert.assertTrue(homePage.isElementDisplayed(HomePageLocators.LOGIN_USER_INPUT), "User logged out");
    }

}
