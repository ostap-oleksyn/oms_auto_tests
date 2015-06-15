package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.locators.EditUserPageLocators;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.EditUserPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AdminEditTest extends TestRunner {

    @Test (dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)

    public void testUserEditing(User admin) throws IOException {

        HomePage homePage = new HomePage(driver);

        // TODO use one admin
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        EditUserPage editUserPage = administrationPage.clickEditUserLink();
        Assert.assertTrue(editUserPage.getElementText(EditUserPageLocators.EDIT_PAGE_MESSAGE)
                .contains("This page is appointed for creating new user for particular role."));

        editUserPage.changeFirstName("Tomas");
        editUserPage.setTextToElement("qwerty", EditUserPageLocators.PASSWORD_INPUT);
        editUserPage.setTextToElement("qwerty", EditUserPageLocators.CONFIRM_PASSWORD_INPUT);
        administrationPage = editUserPage.clickCreateButton();
    }
}
