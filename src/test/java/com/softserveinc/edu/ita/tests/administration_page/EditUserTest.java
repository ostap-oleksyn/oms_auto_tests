package com.softserveinc.edu.ita.tests.administration_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.EditUserPageLocators;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import com.softserveinc.edu.ita.pageobjects.AdministrationPage;
import com.softserveinc.edu.ita.pageobjects.EditUserPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.testng.annotations.Test;

import java.io.IOException;

public class EditUserTest extends TestRunner{

    @Test
    public void testUserEditing() throws IOException {

        final HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        final UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        EditUserPage editUserPage = administrationPage.clickRandomEditButton();
        loggingAssert.assertTrue(editUserPage.isElementDisplayed(EditUserPageLocators.EDIT_PAGE_MESSAGE));
        editUserPage.changeFirstName("Hey");


    }
}
