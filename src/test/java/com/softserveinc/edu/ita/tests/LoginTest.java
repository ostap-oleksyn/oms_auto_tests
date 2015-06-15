package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.HomePageLocators;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.annotations.Test;

public class LoginTest extends TestRunner {


    @Test
    public void emptyLoginTest() {
        final HomePage homePage = new HomePage(driver);
        homePage.clickSubmitButton();
        loggingAssert.assertTrue(homePage.isElementDisplayed(HomePageLocators.LOGIN_ERROR_MESSAGE), "Error message display assert");

    }

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void validLoginTest(User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(user.getRoleName()), "User role assert");

        userInfoPage.clickLogOutButton();
    }

    @Test(dataProvider = "getInvalidUsers", dataProviderClass = DataProviders.class)
    public void invalidLoginTest(String login, String password) {
        final HomePage homePage = new HomePage(driver);
        homePage.logIn(login, password);

        loggingAssert.assertTrue(homePage.isElementDisplayed(HomePageLocators.LOGIN_ERROR_MESSAGE), "Error message display assert");
    }

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void logOutTest(User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(user.getRoleName()), "User role assert");
        userInfoPage.clickLogOutButton();
        loggingAssert.assertTrue(homePage.isElementDisplayed(HomePageLocators.LOGIN_USER_INPUT), "User log out assert");
    }

}
