package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpervisorUITest extends TestRunner {


    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void SupervisorTabsTest(User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        Assert.assertTrue(userInfoPage.getUserRoleLabel()
                        .equals(user.getRoleName()),
                "Logged in user role is incorrect");

        Assert.assertTrue(UserInfoPage.);

    }


}
