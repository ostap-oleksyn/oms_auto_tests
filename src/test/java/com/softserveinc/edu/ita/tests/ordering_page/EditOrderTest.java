package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ordering_page.ItemsOrderStatus;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.pageobjects.EditOrderPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

import static com.softserveinc.edu.ita.locators.OrderingPageLocators.*;
import static com.softserveinc.edu.ita.locators.EditOrderPageLocators.*;

/**
 * Class to test order editing functionality.
 */

public class EditOrderTest extends TestRunner {

    // expected test result failed, appears error message
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testOrderEdit(final User merchandiser) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField(merchandiser.getLogin())
                .clickApplyButton();
        if (!orderingPage.isElementDisplayed(EDIT_LINK)) {
            loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(EDIT_LINK),
                    "There are no orders which assign for " + merchandiser.getLogin());
            orderingPage.clickLogOutButton();
        }

        final EditOrderPage editOrderPage = orderingPage.clickEditLink();
        editOrderPage.setItemOrderStatus(ItemsOrderStatus.DELIVERED)
                .clickItemCancelButton();
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ORDER_TABLE), "cancel and return to ordering page");

        orderingPage.clickEditLink();
        editOrderPage.setItemOrderStatus(ItemsOrderStatus.DELIVERED)
                .clickItemSaveButton();

        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ORDER_TABLE), "save and return to ordering page");

        driver.navigate().back();

        editOrderPage.clickLogOutButton();

        loggingSoftAssert.assertAll();
    }

    // expected test result failed, non functional "To HomePage" button
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testErrorOrderEdit(final User merchandiser) {
        final HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        final UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField(admin.getLogin())
                .clickApplyButton();
        if (!orderingPage.isElementDisplayed(EDIT_LINK)) {
            loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(EDIT_LINK),
                    "There are no orders which assign for " + merchandiser.getLogin());
            orderingPage.clickLogOutButton();
        }

        final EditOrderPage editOrderPage = orderingPage.clickEditLink();
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ITEM_ERROR_MESSAGE), "Error message is enabled ");

        editOrderPage.clickItemErrorShowButton();
        loggingSoftAssert.assertEquals(driver.findElement(ITEM_DETAILS_ABOUT_ERROR.getBy()).getText(),
                "You can not see order details with another assignee.",
                "show details about error");

        editOrderPage.clickItemGoToHomeButton();
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ORDER_TABLE), "return to ordering page");

        driver.navigate().back();

        editOrderPage.clickLogOutButton();

        loggingSoftAssert.assertAll();
    }
}
