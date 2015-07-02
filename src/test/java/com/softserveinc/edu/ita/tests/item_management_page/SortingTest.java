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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by true on 01.07.2015.
 */
public class SortingTest extends TestRunner {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "getProductsTableColumns")
    public void testSorting(ProductsTableColumns column) {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn("login2", "qwerty");
        final ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();
        itemManagementPage.clickResizeLink();

        System.out.println(column.toString());
        itemManagementPage.clickProductsTableColumn(column);
        System.out.println(column.toString());


        List<WebElement> columnsData = driver.findElements(By
                .xpath(String.format(".//*[@id='table']/tbody/tr/td[%s]", String.valueOf(column.ordinal() + 1))));
        final List<String> columnsCellText = new LinkedList<>();

        for (int i = 0; i < columnsData.size(); i++) {
            columnsCellText.add(columnsData.get(i).getText());
        }

        final List<String> base = new LinkedList<>();
        for (int i = 0; i < columnsData.size(); i++) {
            base.add(columnsData.get(i).getText());
        }

        Collections.sort(base);
        for (int i = 0; i < columnsData.size(); i++) {
            System.out.println(columnsCellText.get(i));
            System.out.println(base.get(i));
        }
        itemManagementPage.clickLogOutButton();
        loggingAssert.assertTrue(isColumnsEquals(base, columnsCellText),
                String.format("Table isn't broken after ascendant sorting by '%s'.", column));
    }

    public boolean isColumnsEquals(List<String> baseColumn, List<String> column) {
        int i;
        for (i = 0; i < baseColumn.size(); i++) {
            if (baseColumn.get(i).equals(column.get(i))) {
            } else {
                break;
            }
        }
        if (i == (baseColumn.size())) {
            System.out.println("true" + i);
            return true;
        } else {
            System.out.println("false" + i);
            return false;
        }
    }
}
