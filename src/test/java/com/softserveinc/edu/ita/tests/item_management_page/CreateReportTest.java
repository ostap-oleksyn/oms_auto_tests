package com.softserveinc.edu.ita.tests.item_management_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

/**
 * Class to test creating report of products.
 */
public class CreateReportTest extends TestRunner {

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void createReportTest(final User user) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();

        loggingAssert.assertTrue(itemManagementPage.isElementDisplayed(ItemManagementPageLocators.CREATE_REPORT_LINK),
                "<b>Create report link</b> is displayed");

        itemManagementPage.clickCreateReportLink();

        loggingAssert.assertTrue(itemManagementPage.isElementDisplayed(ItemManagementPageLocators.SAVE_REPORT_LINK),
                "<b>Save report link</b> is displayed");

        itemManagementPage.clickLogOutButton();
    }
}




