package com.softserveinc.edu.ita.tests.item_management_page;

import com.softserveinc.edu.ita.enums.item_management_page.ProductsTableColumns;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

import java.util.*;

/**
 * This class to test sorting actions in supervisor's table of products.
 */
public class SortingTest extends TestRunner {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "getProductsTableColumns")
    public void testSorting(ProductsTableColumns column) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn("login2", DBUtility.getByLogin("login2").getPassword());
        final ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();
        itemManagementPage.clickResizeLink();

        itemManagementPage.clickProductsTableColumn(column);
        final Object[] baseColumnSortedAsc = itemManagementPage.getColumn(column);
        Arrays.sort(baseColumnSortedAsc);
        final Object[] columnSortedAsc = itemManagementPage.getColumn(column);

        itemManagementPage.clickProductsTableColumn(column);
        final Object[] baseColumnSortedDesc = itemManagementPage.getColumn(column);
        Arrays.sort(baseColumnSortedDesc);
        Collections.reverse(Arrays.asList(baseColumnSortedDesc));
        final Object[] columnSortedDesc = itemManagementPage.getColumn(column);

        itemManagementPage.clickLogOutButton();
        loggingAssert.assertTrue(isColumnsEquals(baseColumnSortedAsc, columnSortedAsc),
                String.format("Ascendant sorting by '%s' is working.", column));
        loggingAssert.assertTrue(isColumnsEquals(baseColumnSortedDesc, columnSortedDesc),
                String.format("Descendant sorting by '%s' is working.", column));
    }

    /**
     * This method helps to define equality of columns.
     */
    public boolean isColumnsEquals(Object[] baseColumn, Object[] column) {
        int iteration;
        for (iteration = 0; iteration < baseColumn.length; iteration++) {
            if (baseColumn[iteration].equals(column[iteration])) {
                //if true, we continue iteration
            } else {
                //there we stop iteration
                break;
            }
        }
        if (iteration == (baseColumn.length)) {
            return true;
        } else {
            return false;
        }
    }
}
