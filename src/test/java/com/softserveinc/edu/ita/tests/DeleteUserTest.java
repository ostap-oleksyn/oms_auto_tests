package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test of existing User' deleting (Ticket IFAA-12)
 */
public class DeleteUserTest extends TestRunner {

    @Test
    public void testUserDeleting() {

        final HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        final UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        administrationPage.clickLastButton();

        final String lastUserLogin = administrationPage.getLastLogin();
        administrationPage.clickDeleteLastUser();

        administrationPage.acceptAlert();

        final User user = DBUtility.getByLogin(lastUserLogin);
        Assert.assertEquals("0", user.getIsUserActive());

        administrationPage.clickLogOutButton();
    }

}
