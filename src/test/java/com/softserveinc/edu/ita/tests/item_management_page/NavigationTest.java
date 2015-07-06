package com.softserveinc.edu.ita.tests.item_management_page;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

/**
 * Class to test navigation of "Item Management" page.
 */
public class NavigationTest extends TestRunner {

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void navigationTest(User user) throws DAOException {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();

        loggingSoftAssert.assertTrue(itemManagementPage.getCurrentPageNumber() == 1, "Default page number is <b>1</b>");
        loggingSoftAssert.assertFalse(itemManagementPage.isElementEnabled(ItemManagementPageLocators.FIRST_BUTTON),
                "'<b>First</b>' button is disabled");
        loggingSoftAssert.assertFalse(itemManagementPage.isElementEnabled(ItemManagementPageLocators.PREVIOUS_BUTTON),
                "'<b>Backward</b>' button is disabled");

        loggingSoftAssert.assertEquals(itemManagementPage.getFoundProductsNumber(), DBUtility.getActiveProductsNumber(),
                String.format("Number of products match: found products - <b>%d</b>; all products - <b>%d</b>;",
                        itemManagementPage.getFoundProductsNumber(), DBUtility.getActiveProductsNumber()));

        loggingSoftAssert.assertTrue(itemManagementPage.getNumberOfRows() == 5, "Products list default size is <b>5</b> rows");

        loggingSoftAssert.assertTrue(itemManagementPage.getElementText(ItemManagementPageLocators.PRODUCTS_LIST_RESIZE_LINK)
                .equals("Show 10 items"), "<b>Show 10 items</b> link is displayed");

        itemManagementPage.clickResizeLink();
        loggingSoftAssert.assertTrue(itemManagementPage.getNumberOfRows() == 10, "Users list size changed to <b>10</b> rows");
        loggingSoftAssert.assertTrue(itemManagementPage.getElementText(ItemManagementPageLocators.PRODUCTS_LIST_RESIZE_LINK)
                .equals("Show 5 items"), "<b>Show 5 items</b> link is displayed");

        itemManagementPage.clickLastButton();
        loggingSoftAssert.assertEquals(itemManagementPage.getCurrentPageNumber(), itemManagementPage.getTablePagesQuantity(),
                "Navigated to the last page");

        loggingSoftAssert.assertFalse(itemManagementPage.isElementEnabled(ItemManagementPageLocators.LAST_BUTTON),
                "'<b>Last</b>' button is disabled");
        loggingSoftAssert.assertFalse(itemManagementPage.isElementEnabled(ItemManagementPageLocators.NEXT_BUTTON),
                "'<b>Forward</b>' button is disabled");

        itemManagementPage.clickPreviousButton();
        loggingSoftAssert.assertEquals(itemManagementPage.getCurrentPageNumber(),
                itemManagementPage.getTablePagesQuantity() - 1, "Navigated to the previous page");

        itemManagementPage.clickNextButton();
        loggingSoftAssert.assertEquals(itemManagementPage.getCurrentPageNumber(),
                itemManagementPage.getTablePagesQuantity(), "Navigated to the next page");

        itemManagementPage.clickLogOutButton();
        loggingSoftAssert.assertAll();
    }
}
