package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;
import com.softserveinc.edu.ita.enums.UsersTable;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class is used to test sorting actions in 'Administration' table of 'Administration' page.
 */
public class APST extends TestRunner {
    HomePage homePage;
    UserInfoPage userInfoPage;
    AdministrationPage administrationPage;
    List<UserFromView> baseTableFromView;
    List<UserFromView> tableFromViewSortedAsc;
    List<UserFromView> tableFromViewSortedDesc;

    @Test(dataProvider = "getTables & enums.")
    public void testSorting(List<UserFromView> baseTable, List<UserFromView> table, UsersTable cell) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(baseTable, table, cell), "Table aren't equals.");

    }

    @DataProvider(name = "getTables & enums.")
    public Iterator<Object[]> getTestDataIterator() {
        final List<Object[]> testDataList = new ArrayList<>();
        Stream.of(UsersTable.values()).forEach(cell -> {
            try {
                administrationPage.sortBaseTableBy(baseTableFromView, cell);
                administrationPage.clickAdministrationTableColumn(cell);
                tableFromViewSortedAsc = administrationPage.getTableFromView();
                for(int i = 0; i < tableFromViewSortedAsc.size(); i++){
                    System.out.println(baseTableFromView.get(i));
                    System.out.println(tableFromViewSortedAsc.get(i));
                }
                testDataList.add(new Object[]{baseTableFromView, tableFromViewSortedAsc, cell});


//                administrationPage.reverseBaseTable(baseTableFromView);
//                administrationPage.clickAdministrationTableColumn(cell);
//                tableFromViewSortedDesc = administrationPage.getTableFromView();
//                testDataList.add(new Object[][]{{baseTableFromView}, {tableFromViewSortedDesc}, {cell}});

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return testDataList.iterator();
    }

    @BeforeClass
    public void logIn() {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn("iva", "qwerty");
        administrationPage = userInfoPage.clickAdministrationTab();
        baseTableFromView = administrationPage.getTableFromView();
    }
}
