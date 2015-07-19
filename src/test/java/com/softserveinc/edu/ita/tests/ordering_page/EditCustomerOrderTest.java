package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ordering_page.OrderFilter;
import com.softserveinc.edu.ita.enums.ordering_page.StatusFilterValue;
import com.softserveinc.edu.ita.locators.EditOrdeCustomerPageLocators;
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
    public void testEditOrder(User user) {

        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

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

        EditOrderCustomerPage editOrderCustomerPage = orderingPage.clickEditButton();

        String orderNumber = editOrderCustomerPage.getElementAttribute(EditOrdeCustomerPageLocators.ORDER_NUMBER_FIELD, "value");
        String selectedAssignee = editOrderCustomerPage.getElementAttribute(EditOrdeCustomerPageLocators.ASSIGNEE_LIST_USERS, "value");

        editOrderCustomerPage.fillOrderNumber("888");

        editOrderCustomerPage.changeAssigneeUser();

        editOrderCustomerPage.clickSaveButton();

        userInfoPage.clickOrderingTab();

        orderingPage.fillSearchField(DBUtility.getOrderNameByCustomer(user))
                .clickApplyButton();

        editOrderCustomerPage = orderingPage.clickEditButton();

        editOrderCustomerPage.fillOrderNumber(orderNumber);

        editOrderCustomerPage.fillAssigneeUser(selectedAssignee);

        editOrderCustomerPage.clickSaveButton();

        loggingAssert.assertEquals(orderNumber, editOrderCustomerPage.getValue(EditOrdeCustomerPageLocators.ORDER_NUMBER_FIELD));
        loggingAssert.assertEquals(selectedAssignee, editOrderCustomerPage.getValue(EditOrdeCustomerPageLocators.ASSIGNEE_LIST_USERS));

        orderingPage.clickLogOutButton();

    }
}
