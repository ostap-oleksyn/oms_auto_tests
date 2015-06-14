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
public class AdministrationPageSortingTest_second_way extends TestRunner {
    HomePage homePage;
    UserInfoPage userInfoPage;
    AdministrationPage administrationPage;
    List<UserFromView> baseTableFromView;
    List<UserFromView> tableFromViewSortedAsc;
    List<UserFromView> tableFromViewSortedDesc;

    @Test(dataProvider = "getTables & enums.")
    public void testSorting(List<UserFromView> tableFromViewSortedAsc, List<UserFromView> tableFromViewSortedDesc, UsersTable header) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Assert.assertTrue(administrationPage.verifyIntegrityOfTableAfterSorting(baseTableFromView, tableFromViewSortedAsc),
                "Integrity of table was broken after sorting in ascending direction.");

        Assert.assertTrue(administrationPage.verifyIntegrityOfTableAfterSorting(baseTableFromView, tableFromViewSortedDesc),
                "Integrity of table was broken after sorting in descending direction.");

        administrationPage.sortBaseTableBy(baseTableFromView, header);
        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(baseTableFromView, tableFromViewSortedAsc, header),
                "Ascendant tables aren't equals.");

        administrationPage.reverseBaseTable(baseTableFromView);
        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(baseTableFromView, tableFromViewSortedDesc, header),
                "Descendant tables aren't equals.");

    }

    @DataProvider(name = "getTables & enums.")
    public Iterator<Object[]> getTestDataIterator() {
        final List<Object[]> testDataList = new ArrayList<>();
        Stream.of(UsersTable.values()).forEach(header -> {
                    administrationPage.clickAdministrationTableColumn(header);
                    tableFromViewSortedAsc = administrationPage.getTableFromView();
                    administrationPage.clickAdministrationTableColumn(header);
                    tableFromViewSortedDesc = administrationPage.getTableFromView();
                    testDataList.add(new Object[]{tableFromViewSortedAsc, tableFromViewSortedDesc, header});
                }
        );
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
