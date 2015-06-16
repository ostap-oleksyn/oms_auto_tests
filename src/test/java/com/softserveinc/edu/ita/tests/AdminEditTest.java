package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.EditUserPageLocators;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.EditUserPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import com.softserveinc.edu.ita.utils.PropertyLoader;
import org.testng.annotations.Test;

import java.io.IOException;

public class AdminEditTest extends TestRunner{

    @Test()//dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)

    public void testUserEditing() throws IOException {

        HomePage homePage = new HomePage(driver);

        User admin = new User();
        admin.setLogin(PropertyLoader.getProperty("admin.login"));
        admin.setPassword(PropertyLoader.getProperty("admin.password"));

        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        EditUserPage editUserPage = administrationPage.clickEditButton();
      //  loggingAssert.assertTrue(editUserPage.getElementText(EditUserPageLocators.EDIT_PAGE_MESSAGE)
      //          .contains("This page is appointed for creating new user for particular role."),
      //          "Edit page message is displayed");

        editUserPage.changeFirstName("Tomas");






    }
}
