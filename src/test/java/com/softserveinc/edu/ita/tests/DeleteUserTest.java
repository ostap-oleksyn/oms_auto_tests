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
        administrationPage.clickOnElement(AdministrationPageLocators.LAST_BUTTON);

        // get last user from table
        int lastRowNumber = administrationPage.getRowsCount(AdministrationPageLocators.USERS_TABLE);
        String login = administrationPage.getElementText(By.xpath(String
                .format(AdministrationPageLocators.LOGIN_CELL, lastRowNumber)));

        // and try to delete it by clicking on Delete link
        administrationPage.clickOnElement(By.xpath(String
                .format(AdministrationPageLocators.DELETE_LINK, lastRowNumber)));
        administrationPage.submitAlert();

        User user = DBUtility.getByLogin(login);
        Assert.assertEquals("0", user.getIsUserActive());

        administrationPage.clickLogOutButton();
    }

}
