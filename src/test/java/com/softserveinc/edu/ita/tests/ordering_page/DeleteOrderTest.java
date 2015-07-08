package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.testng.annotations.Test;

/**
 * Class to test order deleting functionality.
 */
public class DeleteOrderTest extends TestRunner {

    @Test
    public void testOrderDeleting() {

        final HomePage homePage = new HomePage(driver);
        final User merchandiser = DBUtility.getMerchandiser();
        final UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        final String deleteOrderName = orderingPage.getFirstOrderName();
        orderingPage.clickDeleteOrderButton();
        orderingPage.acceptAlert();

        loggingAssert.assertNotEquals(orderingPage.getFirstOrderName(), deleteOrderName, "Order is deleted");

        orderingPage.clickLogOutButton();
    }
}
