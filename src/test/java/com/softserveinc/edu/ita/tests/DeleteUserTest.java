package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test of existing User' deleting (Ticket IFAA-12)
 */
public class DeleteUserTest extends TestRunner {

    @Test
    public void testUserDeleting() {
        User admin = DBUtility.getAdmin();
        HomePage homePage = new HomePage(driver);

        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        administrationPage.clickLastButton();

        String lastUserLogin = administrationPage.getLastLogin();
        administrationPage.clickDeleteLastUser();

        administrationPage.closeAlert();

        User user = DBUtility.getByLogin(lastUserLogin);
        Assert.assertEquals("0", user.getIsUserActive());

        administrationPage.clickLogOutButton();
    }

}
