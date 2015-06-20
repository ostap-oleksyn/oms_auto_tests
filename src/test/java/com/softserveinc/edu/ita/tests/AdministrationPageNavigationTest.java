package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.annotations.Test;

import static com.softserveinc.edu.ita.utils.DBUtility.getActiveUsersNumber;
import static com.softserveinc.edu.ita.utils.DBUtility.getAdmin;

public class AdministrationPageNavigationTest extends TestRunner {

    @Test
    public void administrationPageNavigationTest() {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(getAdmin().getLogin(), getAdmin().getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        loggingAssert.assertTrue(administrationPage.getCurrentPageNumber() == 1, "Default page number is <b>1</b>");
        loggingAssert.assertFalse(administrationPage.isElementEnabled(AdministrationPageLocators.FIRST_BUTTON),
                "'<b>First</b>' button is disabled");
        loggingAssert.assertFalse(administrationPage.isElementEnabled(AdministrationPageLocators.BACKWARD_BUTTON),
                "'<b>Backward</b>' button is disabled");

        loggingAssert.assertEquals(getActiveUsersNumber(), administrationPage.getFoundUsersNumber(),
                String.format("Number of users match: found users - <b>%d</b>; active users - <b>%d</b>;",
                        administrationPage.getFoundUsersNumber(), getActiveUsersNumber()));


        loggingAssert.assertTrue(administrationPage.getNumberOfRows() == 5, "Users list default size is <b>5</b> rows");

        loggingAssert.assertTrue(administrationPage.getElementText(AdministrationPageLocators.USERS_LIST_RESIZE_LINK)
                .equals("Show 10 items"), "<b>Show 10 items</b> link is displayed");

        administrationPage.clickUsersListResizeLink();
        loggingAssert.assertTrue(administrationPage.getNumberOfRows() == 10, "Users list size changed to <b>10</b> rows");
        loggingAssert.assertTrue(administrationPage.getElementText(AdministrationPageLocators.USERS_LIST_RESIZE_LINK)
                .equals("Show 5 items"), "<b>Show 5 items</b> link is displayed");

        administrationPage.clickLastButton();
        loggingAssert.assertEquals(administrationPage.getCurrentPageNumber(), administrationPage.getQuantityOfTablePages(),
                "Navigated to the last page");

        loggingAssert.assertFalse(administrationPage.isElementEnabled(AdministrationPageLocators.LAST_BUTTON),
                "'<b>Last</b>' button is disabled");
        loggingAssert.assertFalse(administrationPage.isElementEnabled(AdministrationPageLocators.NEXT_BUTTON),
                "'<b>Forward</b>' button is disabled");

        administrationPage.clickPreviousButton();
        loggingAssert.assertEquals(administrationPage.getCurrentPageNumber(),
                administrationPage.getQuantityOfTablePages() - 1, "Navigated to the previous page");

        administrationPage.clickNextButton();
        loggingAssert.assertEquals(administrationPage.getCurrentPageNumber(),
                administrationPage.getQuantityOfTablePages(), "Navigated to the next page");
    }
}
