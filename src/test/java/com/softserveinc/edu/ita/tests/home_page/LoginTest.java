package com.softserveinc.edu.ita.tests.home_page;


import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.locators.HomePageLocators;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

/**
 * Class to test logging in functionality on home page.
 */
public class LoginTest extends TestRunner {

    private HomePage homePage;
    private UserInfoPage userInfoPage;

    @Test
    public void testEmptyLogin() {
        homePage = new HomePage(driver);
        homePage.clickSubmitButton();
        loggingAssert.assertTrue(homePage.isElementDisplayed(HomePageLocators.LOGIN_ERROR_MESSAGE), "Error message displayed");

    }

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void testValidLogin(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(Roles.getRoleName(user.getRoleReference())), "User roles match");

        userInfoPage.clickLogOutButton();
    }

    @Test(dataProvider = "getInvalidUsers", dataProviderClass = DataProviders.class)
    public void testInvalidLogin(final String login, final String password) {
        homePage = new HomePage(driver);
        homePage.logIn(login, password);

        loggingAssert.assertTrue(homePage.isElementDisplayed(HomePageLocators.LOGIN_ERROR_MESSAGE), "Error message displayed");
    }

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void testLogOut(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(Roles.getRoleName(user.getRoleReference())), "User roles match");
        userInfoPage.clickLogOutButton();
        loggingAssert.assertTrue(homePage.isElementDisplayed(HomePageLocators.LOGIN_USER_INPUT), "User logged out");
    }

}
