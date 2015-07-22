package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.utils.TestRunner;
import org.testng.annotations.Test;

/**
 * Class to test order deleting functionality by customer.
 */
public class DeleteCustomerOrderTest extends TestRunner {

    // test fail expected, "Delete" button is not working properly.
    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void testOrderDeleting(final User user) {

        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        final String orderName = orderingPage.getFirstOrderName();

        orderingPage.fillSearchField(DBUtility.getOrderNameByCustomer(user))
                .clickApplyButton()
                .clickDeleteOrder();

        loggingAssert.assertNotEquals(orderName, orderingPage.getFirstOrderName(), "Order is deleted");

        userInfoPage.clickOrderingTab();

    }
}