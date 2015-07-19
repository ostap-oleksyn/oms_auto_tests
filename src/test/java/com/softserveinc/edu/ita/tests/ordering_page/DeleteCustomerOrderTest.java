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

    // test fail expected, non functional "Delete" button

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void testEditOrder(User user) {

        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        String order_name = orderingPage.getFirstOrderName();

        orderingPage.fillSearchField(DBUtility.getOrderNameByCustomer(user))
                .clickApplyButton();

        orderingPage.clickDeleteOrder();

        loggingAssert.assertNotEquals(order_name, orderingPage.getFirstOrderName(), "Order is deleted");

        userInfoPage.clickOrderingTab();

    }
}