package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.enums.Orders;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.OrderingPage;
import com.softserveinc.edu.ita.page_object.OrderingTableRow;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by true on 08.06.2015.
 */
public class OrderingTabSortingTest extends TestRunner {
    //Test sorting by Order Name
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testOrdernameColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<OrderingTableRow> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(OrderingTableRow::getOrderName));

        List<OrderingTableRow> sortedUpTableByOrderName = orderingPage.getSortedTableBy(Orders.ORDERNAME);
        List<OrderingTableRow> sortedDownTableByOrderName = orderingPage.getSortedTableBy(Orders.ORDERNAME);

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedUpTableByOrderName.get(i).getOrderName().equals(baseTable.get(i).getOrderName()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedDownTableByOrderName.get(i).getOrderName().equals(baseTable.get(j).getOrderName()));
        }
    }

    //Test sorting by Total Price
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testTotalPriceColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<OrderingTableRow> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(OrderingTableRow::getTotalPrice));

        List<OrderingTableRow> sortedUpTableByTotalPrice = orderingPage.getSortedTableBy(Orders.TOTALPRICE);
        List<OrderingTableRow> sortedDownTableByTotalPrice = orderingPage.getSortedTableBy(Orders.TOTALPRICE);

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedUpTableByTotalPrice.get(i).getTotalPrice().equals(baseTable.get(i).getTotalPrice()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedDownTableByTotalPrice.get(i).getTotalPrice().equals(baseTable.get(j).getTotalPrice()));
        }
    }

    //Test sorting by Max Discount
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testMaxDiscountColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<OrderingTableRow> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(OrderingTableRow::getMaxDiscount));

        List<OrderingTableRow> sortedUpTableByMaxDiscount = orderingPage.getSortedTableBy(Orders.MAXDISCOUNT);
        List<OrderingTableRow> sortedDownTableByMaxDiscount = orderingPage.getSortedTableBy(Orders.MAXDISCOUNT);

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedUpTableByMaxDiscount.get(i).getMaxDiscount().equals(baseTable.get(i).getMaxDiscount()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedDownTableByMaxDiscount.get(i).getMaxDiscount().equals(baseTable.get(j).getMaxDiscount()));
        }
    }

    //Test sorting by Delivery Date
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testDeliveryDateColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<OrderingTableRow> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(OrderingTableRow::getDeliveryDate));

        List<OrderingTableRow> sortedUpTableByDeliveryDate = orderingPage.getSortedTableBy(Orders.DELIVERYDATE);
        List<OrderingTableRow> sortedDownTableByDeliveryDate = orderingPage.getSortedTableBy(Orders.DELIVERYDATE);

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedUpTableByDeliveryDate.get(i).getDeliveryDate().equals(baseTable.get(i).getDeliveryDate()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedDownTableByDeliveryDate.get(i).getDeliveryDate().equals(baseTable.get(j).getDeliveryDate()));
        }
    }

    //Test sorting by Status
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testStatusColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<OrderingTableRow> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(OrderingTableRow::getStatus));

        List<OrderingTableRow> sortedUpTableByStatus = orderingPage.getSortedTableBy(Orders.STATUS);
        List<OrderingTableRow> sortedDownTableByStatus = orderingPage.getSortedTableBy(Orders.STATUS);

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedUpTableByStatus.get(i).getStatus().equals(baseTable.get(i).getStatus()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedDownTableByStatus.get(i).getStatus().equals(baseTable.get(j).getStatus()));
        }
    }

    //Test sorting by Assignee
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testAssigneeColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<OrderingTableRow> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(OrderingTableRow::getAssignee));

        List<OrderingTableRow> sortedUpTableByAssignee = orderingPage.getSortedTableBy(Orders.ASSIGNEE);
        List<OrderingTableRow> sortedDownTableByAssignee = orderingPage.getSortedTableBy(Orders.ASSIGNEE);

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedUpTableByAssignee.get(i).getAssignee().equals(baseTable.get(i).getAssignee()));
        }

        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedDownTableByAssignee.get(i).getAssignee().equals(baseTable.get(j).getAssignee()));
        }
    }

    //Test sorting by Role
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testRoleColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<OrderingTableRow> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(OrderingTableRow::getRole));

        List<OrderingTableRow> sortedUpTableByRole = orderingPage.getSortedTableBy(Orders.ROLE);
        List<OrderingTableRow> sortedDownTableByRole = orderingPage.getSortedTableBy(Orders.ROLE);

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedUpTableByRole.get(i).getRole().equals(baseTable.get(i).getRole()));
        }

        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedDownTableByRole.get(i).getRole().equals(baseTable.get(j).getRole()));
        }
    }
}
