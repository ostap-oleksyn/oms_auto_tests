package com.softserveinc.edu.ita.tests.userinfo_page;

import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import org.testng.annotations.Test;


/**
 * Test information on User Info tab for any role User (Ticket IFAA-14)
 */
public class UserInfoTest extends TestRunner {


    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void testUserInfoTab(User user) {

        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        loggingAssert.assertEquals(user.getFirstName(), userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_LABEL), "User first names match");
        loggingAssert.assertEquals(user.getLastName(), userInfoPage
                .getElementText(UserInfoPageLocators.LAST_NAME_LABEL), "User last names match");
        loggingAssert.assertEquals(user.getCustomerTypeRef(), userInfoPage
                .getElementText(UserInfoPageLocators.CUSTOMER_TYPE_LABEL), "User customer types match");
        loggingAssert.assertEquals(user.getRoleRef(), userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL), "User roles match");

        userInfoPage.clickLogOutButton();
    }
}
