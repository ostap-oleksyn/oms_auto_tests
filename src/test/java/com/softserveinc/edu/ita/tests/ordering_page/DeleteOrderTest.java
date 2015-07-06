package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.testng.annotations.Test;

public class DeleteOrderTest extends TestRunner {

    @Test
    public void testOrderDeleting() {

        final HomePage homePage = new HomePage(driver);
        final User merchandiser = DBUtility.getMerchandiser();
        UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        String deleteOrderName = orderingPage.getFirstOrder();
        orderingPage.clickDeleteOrderButton();
        orderingPage.acceptAlert();

        loggingAssert.assertNotEquals(orderingPage.getFirstOrder(), deleteOrderName, "Order is deleted");

        orderingPage.clickLogOutButton();
    }
}
