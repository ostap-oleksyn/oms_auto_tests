package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.GeneratedDataProviders;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import com.softserveinc.edu.ita.page_object.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Test creating of new User (Ticket IFAA-9)
 */
public class CreateUserTest extends TestRunner {

    Logger log = Logger.getLogger(UserInfoTest.class);

    @Test(dataProvider = "generatedUserData", dataProviderClass = GeneratedDataProviders.class)
    public void testValidUserCreate(User user) {

        final String ADMIN_LOGIN = "iva";
        final String ADMIN_PASSWORD = "qwerty";

        HomePage homePage = new HomePage(driver);
        LoggedPageBase logOutPage = homePage.logIn(ADMIN_LOGIN, ADMIN_PASSWORD);

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.sendTextToElement(user.getLogin(), NewUserPageLocators.LOGIN_NAME_INPUT);
        newUserPage.sendTextToElement(user.getFirstName(), NewUserPageLocators.FIRST_NAME_INPUT);
        newUserPage.sendTextToElement(user.getLastName(), NewUserPageLocators.LAST_NAME_INPUT);
        newUserPage.sendTextToElement(user.getPassword(), NewUserPageLocators.PASSWORD_INPUT);
        newUserPage.sendTextToElement(user.getPassword(), NewUserPageLocators.CONFIRM_PASSWORD_INPUT);
        newUserPage.sendTextToElement(user.getEmail(), NewUserPageLocators.EMAIL_INPUT);
        newUserPage.selectRegion(user.getRegionName());
        newUserPage.clickOnElement(By.xpath(String.format(NewUserPageLocators.ROLE_SELECT, user.getRoleName())));


        administrationPage.clickLogOutButton();
    }
}
