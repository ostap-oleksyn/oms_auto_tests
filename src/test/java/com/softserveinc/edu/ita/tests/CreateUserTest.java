package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dataproviders.GeneratedDataProviders;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import com.softserveinc.edu.ita.page_object.*;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.softserveinc.edu.ita.utils.StringsGenerator.generateString;

/**
 * Test creating of new User (Ticket IFAA-9)
 */
public class CreateUserTest extends TestRunner {

    Logger log = Logger.getLogger(UserInfoTest.class);

    @Test(dataProvider = "generatedValidUserData", dataProviderClass = GeneratedDataProviders.class, enabled = false)
    public void testValidUserCreate(User newUser) {

        User admin = DBUtility.getAdmin();

        HomePage homePage = new HomePage(driver);
        LoggedPageBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        newUserPage.sendTextToElement(newUser.getLogin(), NewUserPageLocators.LOGIN_NAME_INPUT);
        newUserPage.sendTextToElement(newUser.getFirstName(), NewUserPageLocators.FIRST_NAME_INPUT);
        newUserPage.sendTextToElement(newUser.getLastName(), NewUserPageLocators.LAST_NAME_INPUT);
        newUserPage.sendTextToElement(newUser.getPassword(), NewUserPageLocators.PASSWORD_INPUT);
        newUserPage.sendTextToElement(newUser.getPassword(), NewUserPageLocators.CONFIRM_PASSWORD_INPUT);
        newUserPage.sendTextToElement(newUser.getEmail(), NewUserPageLocators.EMAIL_INPUT);
        newUserPage.selectRegion(newUser.getRegionName());
        newUserPage.clickOnElement(By.xpath(String.format(NewUserPageLocators.ROLE_SELECT, newUser.getRoleName())));
        administrationPage = newUserPage.clickCreateButton();

        User lastUser = DBUtility.getLastUser();
        Assert.assertEquals(newUser.getLogin(), lastUser.getLogin());

        administrationPage.clickLogOutButton();
    }

    @Test
    public void testNotValidUserCreate() {
        User admin = DBUtility.getAdmin();

        HomePage homePage = new HomePage(driver);
        LoggedPageBase logOutPage = homePage.logIn(admin.getLogin(), admin.getPassword());

        AdministrationPage administrationPage = logOutPage.clickAdministrationTab();
        NewUserPage newUserPage = administrationPage.clickCreateUserLink();

        User newUser = new User();
        newUser.setLogin(generateString("name_symbols", 14, 20).toLowerCase());
        newUserPage.sendTextToElement(newUser.getLogin(), NewUserPageLocators.LOGIN_NAME_INPUT);
        //Assert.assertEquals();



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        administrationPage.clickLogOutButton();
    }
}
