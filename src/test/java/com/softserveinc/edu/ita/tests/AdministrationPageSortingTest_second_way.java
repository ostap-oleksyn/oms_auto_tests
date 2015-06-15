package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;
import com.softserveinc.edu.ita.enums.UsersTable;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * This class is used to test sorting actions in 'Administration' table of 'Administration' page.
 */
public class AdministrationPageSortingTest_second_way extends TestRunner {
    HomePage homePage;
    UserInfoPage userInfoPage;
    AdministrationPage administrationPage;
    List<UserFromView> baseTableFromView;

    @Test(dataProvider = "getEnums")
    public void testSorting(UsersTable header) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        administrationPage.sortBaseTableBy(baseTableFromView, header);
        administrationPage.clickAdministrationTableColumn(UsersTable.FIRST_NAME);
        administrationPage.getTableFromView();



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
}
