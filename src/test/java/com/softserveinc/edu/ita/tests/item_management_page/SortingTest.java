package com.softserveinc.edu.ita.tests.item_management_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.enums.item_management_page.ProductsTableColumns;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Class to test sorting actions in supervisor's table of products.
 */
public class SortingTest extends TestRunner {

    // expected test result failed, not all columns can be sorted
    @Test(dataProviderClass = DataProviders.class, dataProvider = "getProductsTableColumns")
    public void testSorting(final ProductsTableColumns column) {
        final HomePage homePage = new HomePage(driver);
        final User randomSupervisor = DBUtility.getRandomUserByRole(Roles.SUPERVISOR);
        final UserInfoPage userInfoPage = homePage.logIn(randomSupervisor.getLogin(), randomSupervisor.getPassword());
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
        loggingAssert.assertEquals(baseColumnSortedAsc, columnSortedAsc,
                String.format("Ascendant sorting by '%s' is working.", column));
        loggingAssert.assertEquals(baseColumnSortedDesc, columnSortedDesc,
                String.format("Descendant sorting by '%s' is working.", column));
    }
}
