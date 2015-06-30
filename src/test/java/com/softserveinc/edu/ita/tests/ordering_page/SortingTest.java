package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.OrderFromView;
import com.softserveinc.edu.ita.enums.ordering_page.OrdersTableColumns;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;

/**
 * This class is used to test sorting actions in 'Ordering' table of 'Ordering' page.
 */
public class SortingTest extends TestRunner {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "getOrdersTableColumns")
    public void testSorting(OrdersTableColumns column) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn("login1", "qwerty");
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        final List<OrderFromView> baseTableFromView = orderingPage.getTableFromView();

        orderingPage.clickOrdersTableColumn(column);
        final List<OrderFromView> tableFromViewSortedAsc = orderingPage.getTableFromView();

        orderingPage.clickOrdersTableColumn(column);
        final List<OrderFromView> tableFromViewSortedDesc = orderingPage.getTableFromView();

        loggingSoftAssert.assertTrue(isTableIntact(baseTableFromView, tableFromViewSortedAsc),
                String.format("Table isn't broken after ascendant sorting by '%s'.", column));
        loggingSoftAssert.assertTrue(isTableIntact(baseTableFromView, tableFromViewSortedDesc),
                String.format("Table's isn't broken after descendant sorting by '%s'.", column));

        sortBaseTableBy(baseTableFromView, column);
        loggingSoftAssert.assertTrue(isTablesEqualsByColumn(baseTableFromView, tableFromViewSortedAsc, column),
                String.format("Ascendant sorting by '%s' is working.", column));

        Collections.reverse(baseTableFromView);
        loggingSoftAssert.assertTrue(isTablesEqualsByColumn(baseTableFromView, tableFromViewSortedDesc, column),
                String.format("Descendant sorting by '%s' is working.", column));

        orderingPage.clickLogOutButton();
        loggingSoftAssert.assertAll();
    }

    /**
     * Interface with method used in method "isTablesEqualsByColumn".
     */
    private interface ComparisonCondition {
        String callMethod(OrderFromView method);
    }

    /**
     * A method to verify equality of tables by given column.
     */
    public boolean isTablesEqualsByColumn(List<OrderFromView> sortedBaseTableFromView, List<OrderFromView> sortedTableByView, OrdersTableColumns column) {
        Map<OrdersTableColumns, ComparisonCondition> sortConditionsMap = new HashMap<>();
        sortConditionsMap.put(OrdersTableColumns.ORDER_NAME, OrderFromView::getOrderName);
        sortConditionsMap.put(OrdersTableColumns.TOTAL_PRICE, OrderFromView::getTotalPrice);
        sortConditionsMap.put(OrdersTableColumns.MAX_DISCOUNT, OrderFromView::getMaxDiscount);
        sortConditionsMap.put(OrdersTableColumns.DELIVERY_DATE, OrderFromView::getDeliveryDate);
        sortConditionsMap.put(OrdersTableColumns.STATUS, OrderFromView::getStatus);
        sortConditionsMap.put(OrdersTableColumns.ASSIGNEE, OrderFromView::getAssignee);
        sortConditionsMap.put(OrdersTableColumns.ROLE, OrderFromView::getRole);

        Iterator baseTableIterator = sortedBaseTableFromView.iterator();
        Iterator tableIterator = sortedTableByView.iterator();
        int equalsCells = 0;
        while (baseTableIterator.hasNext() && sortConditionsMap.get(column).callMethod((OrderFromView) baseTableIterator.next())
                .equals(sortConditionsMap.get(column).callMethod((OrderFromView) tableIterator.next()))) {
            equalsCells++;
        }
        return (equalsCells == sortedBaseTableFromView.size());
    }

    /**
     * A method to sort base table by given column through comparator.
     */
    public void sortBaseTableBy(List<OrderFromView> baseTableFromView, OrdersTableColumns column) {
        Map<OrdersTableColumns, Function<OrderFromView, String>> sortConditionsMap = new HashMap<>();
        sortConditionsMap.put(OrdersTableColumns.ORDER_NAME, OrderFromView::getOrderName);
        sortConditionsMap.put(OrdersTableColumns.TOTAL_PRICE, OrderFromView::getTotalPrice);
        sortConditionsMap.put(OrdersTableColumns.MAX_DISCOUNT, OrderFromView::getMaxDiscount);
        sortConditionsMap.put(OrdersTableColumns.DELIVERY_DATE, OrderFromView::getDeliveryDate);
        sortConditionsMap.put(OrdersTableColumns.STATUS, OrderFromView::getStatus);
        sortConditionsMap.put(OrdersTableColumns.ASSIGNEE, OrderFromView::getAssignee);
        sortConditionsMap.put(OrdersTableColumns.ROLE, OrderFromView::getRole);
        baseTableFromView.sort(Comparator.comparing(sortConditionsMap.get(column)));
    }

    /**
     * A method to verify integrity of table after sorting. The method says "All of the rows are(true)/aren't(false) intact after sorting".
     */
    public boolean isTableIntact(List<OrderFromView> baseTable, List<OrderFromView> tableAfterSorting) {
        int intactRows = 0;
        Iterator tableIterator = tableAfterSorting.iterator();
        while (tableIterator.hasNext() && baseTable.toString().contains(tableIterator.next().toString())) {
            intactRows++;
        }
        return (intactRows == tableAfterSorting.size());
    }
}