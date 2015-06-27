package com.softserveinc.edu.ita.tests.administration_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.pageobjects.AdministrationPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
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
        administrationPage.clickDeleteLastUserLink();
        administrationPage.acceptAlert();

        final User user = DBUtility.getByLogin(lastUserLogin);
        loggingAssert.assertEquals(user.getStatus(), 0, "User status is changed in database");

        administrationPage.clickLogOutButton();
        // TODO create DAO method for restore user status in database
    }

}
