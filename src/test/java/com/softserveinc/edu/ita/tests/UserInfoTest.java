package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.page_object.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserInfoTest extends TestRunner {

    private final String OMS_URL = "localhost:8080/OMS";
    Logger log = Logger.getLogger(UserInfoTest.class);

    @Test(dataProvider = "getAllRoles")
    public void testMethod(String login, String password, String firstName, String lastName,
                           String customerType, String userRole) {

        log.info("Test started");
        driver.get(OMS_URL);

        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(login, password);

        Assert.assertEquals(firstName, userInfoPage.getFirstNameText());
        Assert.assertEquals(lastName, userInfoPage.getLastNameText());
        Assert.assertEquals(customerType, userInfoPage.getCustomerTypeText());
        Assert.assertEquals(userRole, userInfoPage.getUserRoleText());

        userInfoPage.clickLogOutButton();
    }



//    @DataProvider(name = "userInfoData")
//    public static Object[][] primeNumbers() {
//        // stub for userInfo dataset
//        // TODO change with real data
//        return new Object[][] {
//                {"iva", "qwerty", "ivanka", "horoshko", "Standart", "Administrator"},
//                {"login1", "qwerty", "firstName1", "lastName1", "Standart", "Merchandiser"},
//                {"login2", "qwerty", "firstName2", "lastName2", "Standart", "Supervisor"},
//                {"login3", "qwerty", "firstName3", "lastName3", "Standart", "Customer"}
//        };
//    }


}
