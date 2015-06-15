package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;
import com.softserveinc.edu.ita.enums.UsersTable;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class is used to test sorting actions in 'Administration' table of 'Administration' page.
 */
public class AdministrationPageSortingTest extends TestRunner {
    HomePage homePage;
    UserInfoPage userInfoPage;
    AdministrationPage administrationPage;
    List<UserFromView> baseTableFromView;
    List<UserFromView> tableFromViewSortedAsc;
    List<UserFromView> tableFromViewSortedDesc;

    @Test(dataProvider = "getEnums")
    public void testSorting(UsersTable header) {

        administrationPage.clickAdministrationTableColumn(header);
        tableFromViewSortedAsc = administrationPage.getTableFromView();
        administrationPage.clickAdministrationTableColumn(header);
        tableFromViewSortedDesc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyIntegrityOfTableAfterSorting(baseTableFromView, tableFromViewSortedAsc),
                "Integrity of table was broken after sorting in ascending direction.");
        Assert.assertTrue(administrationPage.verifyIntegrityOfTableAfterSorting(baseTableFromView, tableFromViewSortedDesc),
                "Integrity of table was broken after sorting in descending direction.");

        administrationPage.sortBaseTableBy(baseTableFromView, header);
        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(baseTableFromView, tableFromViewSortedAsc, header),
                "Table sorted in ascendant direction by clicking accordant header isn't equal to base table sorted by comparator.");

        Collections.reverse(baseTableFromView);
        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(baseTableFromView, tableFromViewSortedDesc, header),
                "Table sorted in descendant direction by clicking accordant header isn't equal to base table sorted by comparator.");

    }

    @DataProvider(name = "getEnums")
    public Iterator<Object[]> getTestDataIterator() {
        final List<Object[]> testDataList = new ArrayList<>();
        Stream.of(UsersTable.values()).forEach(header -> testDataList.add(new Object[]{header}));
        return testDataList.iterator();
    }

    @BeforeClass
    public void logIn() {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn("iva", "qwerty");
        administrationPage = userInfoPage.clickAdministrationTab();
        baseTableFromView = administrationPage.getTableFromView();
    }

    @AfterClass
    public void logOut() {
        administrationPage.clickLogOutButton();
    }
}
