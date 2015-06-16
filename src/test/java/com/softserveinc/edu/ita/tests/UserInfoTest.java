package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test information on User Info tab for any role User (Ticket IFAA-14)
 */
public class UserInfoTest extends TestRunner {

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void testUserInfoTab(User user) {

        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        Assert.assertEquals(user.getFirstName(), userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_LABEL));
        Assert.assertEquals(user.getLastName(), userInfoPage
                .getElementText(UserInfoPageLocators.LAST_NAME_LABEL));
        Assert.assertEquals(user.getCustomerType(), userInfoPage
                .getElementText(UserInfoPageLocators.CUSTOMER_TYPE_LABEL));
        Assert.assertEquals(user.getRoleName(), userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL));

        userInfoPage.clickLogOutButton();
    }
}
