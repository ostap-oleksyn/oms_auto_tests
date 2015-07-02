package com.softserveinc.edu.ita.tests.item_management_page;

import com.softserveinc.edu.ita.enums.item_management_page.ProductsTableColumns;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import javax.lang.model.type.TypeVariable;
import java.lang.reflect.Type;
import java.sql.Types;
import java.util.*;

/**
 * Created by true on 01.07.2015.
 */
public class SortingTest<T> extends TestRunner {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "getProductsTableColumns")
    public void testSorting(ProductsTableColumns column) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn("login2", "qwerty");
        final ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();
        itemManagementPage.clickResizeLink();

        itemManagementPage.clickProductsTableColumn(column);

        final Object[] baseColumnsortedAsc = getColumn(column);
        Arrays.sort(baseColumnsortedAsc);
        final Object[] columnSortedAsc = getColumn(column);

        itemManagementPage.clickProductsTableColumn(column);

        final Object[] baseColumnsortedDesc = getColumn(column);
        Arrays.sort(baseColumnsortedDesc);
        Collections.reverse(Arrays.asList(baseColumnsortedDesc));
        final Object[] columnSortedDesc = getColumn(column);

        for (int i = 0; i < baseColumnsortedDesc.length; i++) {
            System.out.println(baseColumnsortedDesc[i]);
            System.out.println(columnSortedDesc[i]);
        }

        itemManagementPage.clickLogOutButton();
        loggingAssert.assertTrue(isColumnsEquals(baseColumnsortedAsc, columnSortedDesc),
                String.format("Ascendant sorting by '%s' is working.", column));
    }

    public boolean isColumnsEquals(Object[] baseColumn, Object[] column) {
        int i;
        for (i = 0; i < baseColumn.length; i++) {
            if (baseColumn[i].equals(column[i])) {
            } else {
                break;
            }
        }
        if (i == (baseColumn.length)) {
            System.out.println("true" + i);
            return true;
        } else {
            System.out.println("false" + i);
            return false;
        }
    }

    public Object[] getColumn(ProductsTableColumns column) {
        List<WebElement> columnsElement = driver.findElements(By
                .xpath(String.format(".//*[@id='table']/tbody/tr/td[%s]", String.valueOf(column.ordinal() + 1))));
        Object[] columnsData = new Object[columnsElement.size()];
        for (int i = 0; i < columnsElement.size(); i++) {
            columnsData[i] = column.getValue(columnsElement.get(i));
        }
        return columnsData;
    }
}
