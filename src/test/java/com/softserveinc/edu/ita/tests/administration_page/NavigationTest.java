package com.softserveinc.edu.ita.tests.administration_page;


import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.pageobjects.AdministrationPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import org.testng.annotations.Test;

import static com.softserveinc.edu.ita.utils.DBUtility.getActiveUsersNumber;

public class NavigationTest extends TestRunner {

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void navigationTest(User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        loggingSoftAssert.assertTrue(administrationPage.getCurrentPageNumber() == 1, "Default page number is <b>1</b>");
        loggingSoftAssert.assertFalse(administrationPage.isElementEnabled(AdministrationPageLocators.FIRST_BUTTON.setByWithoutParameter()),
                "'<b>First</b>' button is disabled");
        loggingSoftAssert.assertFalse(administrationPage.isElementEnabled(AdministrationPageLocators.BACKWARD_BUTTON.setByWithoutParameter()),
                "'<b>Backward</b>' button is disabled");

        loggingSoftAssert.assertEquals(getActiveUsersNumber(), administrationPage.getFoundUsersNumber(),
                String.format("Number of users match: found users - <b>%d</b>; active users - <b>%d</b>;",
                        administrationPage.getFoundUsersNumber(), getActiveUsersNumber()));


        loggingSoftAssert.assertTrue(administrationPage.getNumberOfRows() == 5, "Users list default size is <b>5</b> rows");

        loggingSoftAssert.assertTrue(administrationPage.getElementText(AdministrationPageLocators.USERS_LIST_RESIZE_LINK.setByWithoutParameter())
                .equals("Show 10 items"), "<b>Show 10 items</b> link is displayed");

        administrationPage.clickUsersListResizeLink();
        loggingSoftAssert.assertTrue(administrationPage.getNumberOfRows() == 10, "Users list size changed to <b>10</b> rows");
        loggingSoftAssert.assertTrue(administrationPage.getElementText(AdministrationPageLocators.USERS_LIST_RESIZE_LINK.setByWithoutParameter())
                .equals("Show 5 items"), "<b>Show 5 items</b> link is displayed");

        administrationPage.clickLastButton();
        loggingSoftAssert.assertEquals(administrationPage.getCurrentPageNumber(), administrationPage.getQuantityOfTablePages(),
                "Navigated to the last page");

        loggingSoftAssert.assertFalse(administrationPage.isElementEnabled(AdministrationPageLocators.LAST_BUTTON.setByWithoutParameter()),
                "'<b>Last</b>' button is disabled");
        loggingSoftAssert.assertFalse(administrationPage.isElementEnabled(AdministrationPageLocators.NEXT_BUTTON.setByWithoutParameter()),
                "'<b>Forward</b>' button is disabled");

        administrationPage.clickPreviousButton();
        loggingSoftAssert.assertEquals(administrationPage.getCurrentPageNumber(),
                administrationPage.getQuantityOfTablePages() - 1, "Navigated to the previous page");

        administrationPage.clickNextButton();
        loggingSoftAssert.assertEquals(administrationPage.getCurrentPageNumber(),
                administrationPage.getQuantityOfTablePages(), "Navigated to the next page");

        loggingSoftAssert.assertAll();

        administrationPage.clickLogOutButton();
    }
}
