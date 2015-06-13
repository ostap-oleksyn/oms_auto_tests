package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Reporter;
import org.testng.annotations.Test;


/**
 * Test information on User Info tab for any role User (Ticket IFAA-14)
 */
public class UserInfoTest extends TestRunner {


    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void testUserInfoTab(User user) {

        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        Reporter.log(UserInfoPageLocators.FIRST_NAME_LABEL.toString());
        loggingAssert.assertEquals(user.getFirstName(), userInfoPage
                .getElementText(UserInfoPageLocators.FIRST_NAME_LABEL.getBy()), "User first name assert");
        loggingAssert.assertEquals(user.getLastName(), userInfoPage
                .getElementText(UserInfoPageLocators.LAST_NAME_LABEL.getBy()), "User last name assert");
        loggingAssert.assertEquals(user.getCustomerType(), userInfoPage
                .getElementText(UserInfoPageLocators.CUSTOMER_TYPE_LABEL.getBy()), "User customer type assert");
        loggingAssert.assertEquals(user.getRoleName(), userInfoPage
                .getElementText(UserInfoPageLocators.USER_ROLE_LABEL.getBy()), "User role assert");

        userInfoPage.clickLogOutButton();
    }
}
