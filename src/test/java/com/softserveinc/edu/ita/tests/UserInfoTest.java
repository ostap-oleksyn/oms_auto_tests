package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.page_object.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.softserveinc.edu.ita.dataproviders.DataProviders;


/*
 * Test information on User Info tab for any role User (Ticket IFAA-14)
 */
public class UserInfoTest extends TestRunner {

    private final String OMS_URL = "localhost:8080/OMS";
    Logger log = Logger.getLogger(UserInfoTest.class);

    @Test(dataProvider = "getAllRoles", dataProviderClass = DataProviders.class)
    public void testMethod(User user) {

        log.info(String.format("Test started: %s [%s %s]: %s, %s", user.getLogin(),
                user.getFirstName(), user.getLastName(),
                user.getCustomerType(), user.getRoleName()));
        driver.get(OMS_URL);

        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        Assert.assertEquals(user.getFirstName(), userInfoPage.getFirstNameText());
        Assert.assertEquals(user.getLastName(), userInfoPage.getLastNameText());
        Assert.assertEquals(user.getCustomerType(), userInfoPage.getCustomerTypeText());
        Assert.assertEquals(user.getRoleName(), userInfoPage.getUserRoleText());

        userInfoPage.clickLogOutButton();
        log.info("Test finished");
    }
}
