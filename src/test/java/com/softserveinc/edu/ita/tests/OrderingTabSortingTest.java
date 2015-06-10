package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.dao_jdbc.domains.Orders;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.OrderingPage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * This class is used to test sorting actions in 'Ordering' table of 'Ordering' page.
 */
public class OrderingTabSortingTest extends TestRunner {
    //Test sorting by Order Name
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testOrderNameColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Orders> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(Orders::getOrderName));

        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.ORDER_NAME.toString());
        List<Orders> sortedTableByOrderNameAsc = orderingPage.getTable();
        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.ORDER_NAME.toString());
        List<Orders> sortedTableByOrderNameDis = orderingPage.getTable();

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedTableByOrderNameAsc.get(i).getOrderName().equals(baseTable.get(i).getOrderName()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedTableByOrderNameDis.get(i).getOrderName().equals(baseTable.get(j).getOrderName()));
        }
    }

    //Test sorting by Total Price
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testTotalPriceColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Orders> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(Orders::getTotalPrice));

        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.TOTAL_PRICE.toString());
        List<Orders> sortedTableByTotalPriceAsc = orderingPage.getTable();
        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.TOTAL_PRICE.toString());
        List<Orders> sortedTableByTotalPriceDisc = orderingPage.getTable();

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedTableByTotalPriceAsc.get(i).getTotalPrice().equals(baseTable.get(i).getTotalPrice()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedTableByTotalPriceDisc.get(i).getTotalPrice().equals(baseTable.get(j).getTotalPrice()));
        }
    }

    //Test sorting by Max Discount
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testMaxDiscountColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Orders> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(Orders::getMaxDiscount));

        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.MAX_DISCOUNT.toString());
        List<Orders> sortedTableByMaxDiscountAsc = orderingPage.getTable();
        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.MAX_DISCOUNT.toString());
        List<Orders> sortedTableByMaxDiscountDisc = orderingPage.getTable();

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedTableByMaxDiscountAsc.get(i).getMaxDiscount().equals(baseTable.get(i).getMaxDiscount()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedTableByMaxDiscountDisc.get(i).getMaxDiscount().equals(baseTable.get(j).getMaxDiscount()));
        }
    }

    //Test sorting by Delivery Date
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testDeliveryDateColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Orders> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(Orders::getDeliveryDate));

        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.DELIVERY_DATE.toString());
        List<Orders> sortedTableByDeliveryDateAsc = orderingPage.getTable();
        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.DELIVERY_DATE.toString());
        List<Orders> sortedTableByDeliveryDateDisc = orderingPage.getTable();

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedTableByDeliveryDateAsc.get(i).getDeliveryDate().equals(baseTable.get(i).getDeliveryDate()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedTableByDeliveryDateDisc.get(i).getDeliveryDate().equals(baseTable.get(j).getDeliveryDate()));
        }
    }

    //Test sorting by Status
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testStatusColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Orders> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(Orders::getStatus));

        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.STATUS.toString());
        List<Orders> sortedTableByStatusAsc = orderingPage.getTable();
        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.STATUS.toString());
        List<Orders> sortedTableByStatusDisc = orderingPage.getTable();

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedTableByStatusAsc.get(i).getStatus().equals(baseTable.get(i).getStatus()));
        }
        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedTableByStatusDisc.get(i).getStatus().equals(baseTable.get(j).getStatus()));
        }
    }

    //Test sorting by Assignee
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testAssigneeColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Orders> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(Orders::getAssignee));

        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.ASSIGNEE.toString());
        List<Orders> sortedTableByAssigneeAsc = orderingPage.getTable();
        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.ASSIGNEE.toString());
        List<Orders> sortedTableByAssigneeDisc = orderingPage.getTable();

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedTableByAssigneeAsc.get(i).getAssignee().equals(baseTable.get(i).getAssignee()));
        }

        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedTableByAssigneeDisc.get(i).getAssignee().equals(baseTable.get(j).getAssignee()));
        }
    }

    //Test sorting by Role
    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testRoleColumn(User user) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        List<Orders> baseTable = orderingPage.getTable();
        baseTable.sort(Comparator.comparing(Orders::getRole));

        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.ROLE.toString());
        List<Orders> sortedTableByRoleaAsc = orderingPage.getTable();
        orderingPage.clickTableColumn(com.softserveinc.edu.ita.enums.Orders.ROLE.toString());
        List<Orders> sortedTableByRoleDisc = orderingPage.getTable();

        for (int i = 0; i < baseTable.size(); i++) {
            Assert.assertTrue(sortedTableByRoleaAsc.get(i).getRole().equals(baseTable.get(i).getRole()));
        }

        for (int i = 0, j = baseTable.size() - 1; i < baseTable.size(); i++, j--) {
            Assert.assertTrue(sortedTableByRoleDisc.get(i).getRole().equals(baseTable.get(j).getRole()));
        }
    }
}
