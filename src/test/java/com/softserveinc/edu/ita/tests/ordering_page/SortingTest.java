package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.dao.AbstractDAO;
import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.dao.FactoryDAO;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.domains.Order;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.OrdersTableColumns;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

/**
 * This class is used to test sorting actions in 'Ordering' table of 'Ordering' page.
 */
public class SortingTest extends TestRunner {

    // TODO Do smth with Order::getrole

    //Test sorting by Order Name
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testOrderNameColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getOrderName));

        orderingPage.clickOrdersTableColumn(OrdersTableColumns.ORDER_NAME);
        List<Order> sortedTableByOrderNameAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTableColumns.ORDER_NAME);
        List<Order> sortedTableByOrderNameDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            loggingAssert.assertTrue(sortedTableByOrderNameAsc.get(i).getOrderName().equals(tableFromView.get(i).getOrderName()),
                    "Sorting by role in ascending order assert.");
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            loggingAssert.assertTrue(sortedTableByOrderNameDesc.get(i).getOrderName().equals(tableFromView.get(j).getOrderName()),
                    "Sorting by role in descending order assert.");
        }
    }

    //Test sorting by Total Price
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testTotalPriceColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getTotalPrice));

        orderingPage.clickOrdersTableColumn(OrdersTableColumns.TOTAL_PRICE);
        List<Order> sortedTableByTotalPriceAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTableColumns.TOTAL_PRICE);
        List<Order> sortedTableByTotalPriceDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            loggingAssert.assertTrue(sortedTableByTotalPriceAsc.get(i).getTotalPrice().equals(tableFromView.get(i).getTotalPrice()),
                    "Sorting by total price in ascending order assert.");
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            loggingAssert.assertTrue(sortedTableByTotalPriceDesc.get(i).getTotalPrice().equals(tableFromView.get(j).getTotalPrice()),
                    "Sorting by total price in descending order assert.");
        }
    }

    //Test sorting by Max Discount
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testMaxDiscountColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getMaxDiscount));

        orderingPage.clickOrdersTableColumn(OrdersTableColumns.MAX_DISCOUNT);
        List<Order> sortedTableByMaxDiscountAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTableColumns.MAX_DISCOUNT);
        List<Order> sortedTableByMaxDiscountDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            loggingAssert.assertTrue(sortedTableByMaxDiscountAsc.get(i).getMaxDiscount() == tableFromView.get(i).getMaxDiscount(),
                    "Sorting by max discount in ascending order assert.");
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            loggingAssert.assertTrue(sortedTableByMaxDiscountDesc.get(i).getMaxDiscount() == tableFromView.get(j).getMaxDiscount(),
                    "Sorting by max discount in descending order assert.");
        }
    }

    //Test sorting by Delivery Date
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testDeliveryDateColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getDeliveryDate));

        orderingPage.clickOrdersTableColumn(OrdersTableColumns.DELIVERY_DATE);
        List<Order> sortedTableByDeliveryDateAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTableColumns.DELIVERY_DATE);
        List<Order> sortedTableByDeliveryDateDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            loggingAssert.assertTrue(sortedTableByDeliveryDateAsc.get(i).getDeliveryDate().equals(tableFromView.get(i).getDeliveryDate()),
                    "Sorting by dalivery date in ascending order assert.");
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            loggingAssert.assertTrue(sortedTableByDeliveryDateDesc.get(i).getDeliveryDate().equals(tableFromView.get(j).getDeliveryDate()),
                    "Sorting by delivary date in descending order assert.");
        }
    }

    //Test sorting by Status
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testStatusColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getStatusName));

        orderingPage.clickOrdersTableColumn(OrdersTableColumns.STATUS);
        List<Order> sortedTableByStatusAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTableColumns.STATUS);
        List<Order> sortedTableByStatusDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            loggingAssert.assertTrue(sortedTableByStatusAsc.get(i).getStatusName().equals(tableFromView.get(i).getStatusName()),
                    "Sorting by status in ascending order assert.");
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            loggingAssert.assertTrue(sortedTableByStatusDesc.get(i).getStatusName().equals(tableFromView.get(j).getStatusName()),
                    "Sorting by status in descending order assert.");
        }
    }

    //Test sorting by Assignee
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testAssigneeColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getAssignee));

        orderingPage.clickOrdersTableColumn(OrdersTableColumns.ASSIGNEE);
        List<Order> sortedTableByAssigneeAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTableColumns.ASSIGNEE);
        List<Order> sortedTableByAssigneeDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            loggingAssert.assertTrue(sortedTableByAssigneeAsc.get(i).getAssignee() == tableFromView.get(i).getAssignee(),
                    "Sorting by assignee in ascending order assert.");
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            loggingAssert.assertTrue(sortedTableByAssigneeDesc.get(i).getAssignee() == tableFromView.get(j).getAssignee(),
                    "Sorting by assignee in descending order assert.");
        }
    }

    //Test sorting by Role
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testRoleColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Order> tableFromView = orderingPage.getTableFromView();
        tableFromView.sort(Comparator.comparing(Order::getRole));

        orderingPage.clickOrdersTableColumn(OrdersTableColumns.ROLE);
        List<Order> sortedTableByRoleAsc = orderingPage.getTableFromView();
        orderingPage.clickOrdersTableColumn(OrdersTableColumns.ROLE);
        List<Order> sortedTableByRoleDesc = orderingPage.getTableFromView();

        for (int i = 0; i < tableFromView.size(); i++) {
            loggingAssert.assertTrue(sortedTableByRoleAsc.get(i).getRole().equals(tableFromView.get(i).getRole()),
                    "Sorting by role in ascending order assert.");
        }

        for (int i = 0, j = tableFromView.size() - 1; i < tableFromView.size(); i++, j--) {
            loggingAssert.assertTrue(sortedTableByRoleDesc.get(i).getRole().equals(tableFromView.get(j).getRole()),
                    "Sorting by role in descending order assert.");
        }
    }

    @AfterMethod
    void logOut() {
        OrderingPage orderingPage = new OrderingPage(driver);
        orderingPage.clickLogOutButton();
    }

}
