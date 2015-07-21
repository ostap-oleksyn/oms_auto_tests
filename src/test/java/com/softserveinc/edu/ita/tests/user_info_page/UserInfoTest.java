package com.softserveinc.edu.ita.tests.user_info_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;


/**
 * Class to test information on User Info tab for all user roles.
 */
public class UserInfoTest extends TestRunner {


    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void testUserInfoTab(final User user) {

        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertEquals(user.getFirstName(), userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_LABEL), "User first names match");
        loggingAssert.assertEquals(user.getLastName(), userInfoPage
                .getElementText(UserInfoPageLocators.LAST_NAME_LABEL), "User last names match");
        loggingAssert.assertEquals(user.getCustomerTypeName(), userInfoPage
                .getElementText(UserInfoPageLocators.CUSTOMER_TYPE_LABEL), "User customer types match");
        loggingAssert.assertEquals(user.getRoleName(), userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL), "User roles match");

        userInfoPage.clickLogOutButton();
    }
}
