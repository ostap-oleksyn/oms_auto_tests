package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;
import com.softserveinc.edu.ita.enums.UsersTable;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
    public void testSorting(UsersTable enumeration) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        administrationPage.sortBaseTableBy(baseTableFromView, enumeration);

        administrationPage.clickAdministrationTableColumn(enumeration);
        tableFromViewSortedAsc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(baseTableFromView, tableFromViewSortedAsc, enumeration));

        administrationPage.reverseBaseTable(baseTableFromView);

        administrationPage.clickAdministrationTableColumn(enumeration);
        tableFromViewSortedDesc = administrationPage.getTableFromView();

        Assert.assertTrue(administrationPage.verifyEqualityOfTablesByColumn(baseTableFromView, tableFromViewSortedDesc, enumeration));

    }

    @DataProvider(name = "getEnums")
    public Iterator<Object[]> getTestDataIterator() {
        final List<Object[]> testDataList = new ArrayList<>();
        for (UsersTable enumeration : UsersTable.values()) testDataList.add(new Object[]{enumeration});
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
