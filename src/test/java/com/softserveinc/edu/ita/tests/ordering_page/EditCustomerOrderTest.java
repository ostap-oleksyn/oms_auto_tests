package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ordering_page.OrderFilter;
import com.softserveinc.edu.ita.enums.ordering_page.StatusFilterValue;
import com.softserveinc.edu.ita.locators.EditOrderCustomerPageLocators;
import com.softserveinc.edu.ita.locators.OrderingPageLocators;
import com.softserveinc.edu.ita.pageobjects.*;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.utils.TestRunner;
import org.testng.annotations.Test;

/**
 * Class to test edit order customer that was created by him.
 */
public class EditCustomerOrderTest extends TestRunner {

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void testEditOrder(final User user) {

        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        orderingPage.setFilter(OrderFilter.STATUS)
                .setFilterValue(StatusFilterValue.PENDING)
                .clickApplyButton();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.ORDER_STATUS_COLUMN)
                .equals("Pending"), "Order Status 'Pending'");

        orderingPage.clickEditLink();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.CANNOT_EDIT_ORDER_MASSEGE)
                .equals("you cant see this order"), "You can't edit this order");

        userInfoPage.clickOrderingTab();

        orderingPage.setFilter(OrderFilter.STATUS)
                .setFilterValue(StatusFilterValue.ORDERED)
                .clickApplyButton();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.ORDER_STATUS_COLUMN)
                .equals("Ordered"), "Order Status 'Ordered'");

        orderingPage.clickEditLink();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.CANNOT_EDIT_ORDER_MASSEGE)
                .equals("you cant see this order"), "You can't edit this order");

        userInfoPage.clickOrderingTab();

        orderingPage.setFilter(OrderFilter.STATUS)
                .setFilterValue(StatusFilterValue.DELIVERED)
                .clickApplyButton();

        loggingAssert.assertTrue(userInfoPage
                .getElementText(OrderingPageLocators.ORDER_STATUS_COLUMN)
                .equals("Delivered"), "Order Status 'Delivered'");

        orderingPage.clickEditLink();

        loggingAssert.assertTrue(userInfoPage
                    .getElementText(OrderingPageLocators.CANNOT_EDIT_ORDER_MASSEGE)
                    .equals("you cant see this order"), "You can't edit this order");

        userInfoPage.clickOrderingTab();

        orderingPage.setFilter(OrderFilter.STATUS)
                    .setFilterValue(StatusFilterValue.NONE)
                    .clickApplyButton();

        orderingPage.fillSearchField(DBUtility.getOrderNameByCustomer(user))
                    .clickApplyButton();

        final EditOrderCustomerPage editOrderCustomerPage = orderingPage.clickEditButton();

        final String orderNumber = editOrderCustomerPage.getElementAttribute(EditOrderCustomerPageLocators.ORDER_NUMBER_FIELD, "value");
        final String selectedAssignee = editOrderCustomerPage.getElementAttribute(EditOrderCustomerPageLocators.ASSIGNEE_LIST_USERS, "value");

        editOrderCustomerPage.fillOrderNumber("888")
                .changeAssigneeUser()
                .clickSaveButton();

        userInfoPage.clickOrderingTab();

        orderingPage.fillSearchField(DBUtility.getOrderNameByCustomer(user))
                .clickApplyButton()
                .clickEditButton();

        editOrderCustomerPage.fillOrderNumber(orderNumber)
                .fillAssigneeUser(selectedAssignee)
                .clickSaveButton();

        loggingAssert.assertEquals(orderNumber, editOrderCustomerPage.getValue(EditOrderCustomerPageLocators.ORDER_NUMBER_FIELD));
        loggingAssert.assertEquals(selectedAssignee, editOrderCustomerPage.getValue(EditOrderCustomerPageLocators.ASSIGNEE_LIST_USERS));

        orderingPage.clickLogOutButton();
    }
}
