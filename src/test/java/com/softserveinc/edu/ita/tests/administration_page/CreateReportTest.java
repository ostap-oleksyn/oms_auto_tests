package com.softserveinc.edu.ita.tests.administration_page;


import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.pageobjects.AdministrationPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

public class CreateReportTest extends TestRunner {

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void createReportTest(User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        loggingAssert.assertTrue(administrationPage.isElementDisplayed(AdministrationPageLocators.CREATE_REPORT_LINK),
                "<b>Create report link</b> is displayed");

        administrationPage.clickCreateReportLink();

        loggingAssert.assertTrue(administrationPage.isElementDisplayed(AdministrationPageLocators.SAVE_REPORT_LINK),
                "<b>Save report link</b> is displayed");

        administrationPage.clickLogOutButton();
    }
}
