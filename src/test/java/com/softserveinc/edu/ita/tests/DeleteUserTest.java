package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Test: deleting of existing User (Ticket IFAA-12)
 */
public class DeleteUserTest extends TestRunner {

    @Test
    public void testUserDeleting() {
        User admin = DBUtility.getAdmin();
        HomePage homePage = new HomePage(driver);

        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();
        administrationPage.clickOnElement(AdministrationPageLocators.LAST_BUTTON);

        int lastRowNumber = administrationPage.getRowsCount(AdministrationPageLocators.USERS_TABLE);
        administrationPage.clickOnElement(By.xpath(String
                .format(AdministrationPageLocators.DELETE_LINK, lastRowNumber)));

        administrationPage.submitAlert();
    }


}
