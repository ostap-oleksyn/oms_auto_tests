package com.softserveinc.edu.ita.tests;import com.softserveinc.edu.ita.domains.UserFromView;import com.softserveinc.edu.ita.enums.UsersTableColumns;import com.softserveinc.edu.ita.dataproviders.DataProviders;import com.softserveinc.edu.ita.page_object.AdministrationPage;import com.softserveinc.edu.ita.page_object.HomePage;import com.softserveinc.edu.ita.page_object.UserInfoPage;import org.testng.annotations.*;import java.util.*;import java.util.function.Function;/** * This class is used to test sorting actions in 'Administration' table of 'Administration' page. */public class AdministrationPageSortingTest extends TestRunner {    @Test(dataProviderClass = DataProviders.class, dataProvider = "getUsersTableColumns")    public void testSorting(UsersTableColumns column) {        final HomePage homePage = new HomePage(driver);        final UserInfoPage userInfoPage = homePage.logIn("iva", "qwerty");        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();        final List<UserFromView> baseTableFromView = administrationPage.getTableFromView();                administrationPage.clickAdministrationTableColumn(column);        final List<UserFromView> tableFromViewSortedAsc = administrationPage.getTableFromView();        administrationPage.clickAdministrationTableColumn(column);        final List<UserFromView> tableFromViewSortedDesc = administrationPage.getTableFromView();        loggingSoftAssert.assertTrue(isTableUnbroken(baseTableFromView, tableFromViewSortedAsc),                String.format("Table isn't broken after ascendant sorting by '%s'.", column));        loggingSoftAssert.assertTrue(isTableUnbroken(baseTableFromView, tableFromViewSortedDesc),                String.format("Table's isn't broken after descendant sorting by '%s'.", column));        sortBaseTableBy(baseTableFromView, column);        loggingSoftAssert.assertTrue(isTablesEqualsByColumn(baseTableFromView, tableFromViewSortedAsc, column),                String.format("Ascendant sorting by '%s' is working.", column));        Collections.reverse(baseTableFromView);        loggingSoftAssert.assertTrue(isTablesEqualsByColumn(baseTableFromView, tableFromViewSortedDesc, column),                String.format("Descendant sorting by '%s' is working.", column));        administrationPage.clickLogOutButton();    }    /**     * Interface with method used in method "isTablesEqualsByColumn".     */    private interface ComparisonCondition {        String callMethod(UserFromView method);    }    /**     * A method to verify equality of tables by given column.     */    public boolean isTablesEqualsByColumn(List<UserFromView> sortedBaseTableFromView, List<UserFromView> sortedTableByView, UsersTableColumns column) {        Map<UsersTableColumns, ComparisonCondition> sortConditionsMap = new HashMap<>();        sortConditionsMap.put(UsersTableColumns.FIRST_NAME, UserFromView::getFirstName);        sortConditionsMap.put(UsersTableColumns.LAST_NAME, UserFromView::getLastName);        sortConditionsMap.put(UsersTableColumns.LOGIN, UserFromView::getLogin);        sortConditionsMap.put(UsersTableColumns.ROLE, UserFromView::getRole);        sortConditionsMap.put(UsersTableColumns.REGION, UserFromView::getRegion);        Iterator baseTableIterator = sortedBaseTableFromView.iterator();        Iterator tableIterator = sortedTableByView.iterator();        int equalsCells = 0;        while (baseTableIterator.hasNext() && sortConditionsMap.get(column).callMethod((UserFromView) baseTableIterator.next())                .equals(sortConditionsMap.get(column).callMethod((UserFromView) tableIterator.next()))) {            equalsCells++;        }        return (equalsCells == sortedBaseTableFromView.size());    }    /**     * A method to sort base table by given column through comparator.     */    public void sortBaseTableBy(List<UserFromView> baseTableFromView, UsersTableColumns column) {        Map<UsersTableColumns, Function<UserFromView, String>> sortConditionsMap = new HashMap<>();        sortConditionsMap.put(UsersTableColumns.FIRST_NAME, UserFromView::getFirstName);        sortConditionsMap.put(UsersTableColumns.LAST_NAME, UserFromView::getLastName);        sortConditionsMap.put(UsersTableColumns.LOGIN, UserFromView::getLogin);        sortConditionsMap.put(UsersTableColumns.ROLE, UserFromView::getRole);        sortConditionsMap.put(UsersTableColumns.REGION, UserFromView::getRegion);        baseTableFromView.sort(Comparator.comparing(sortConditionsMap.get(column)));    }    /**     * A method to verify integrity of table after sorting. The method says "All of the rows are(true)/aren't(false) intact after sorting".     */    public boolean isTableUnbroken(List<UserFromView> baseTable, List<UserFromView> tableAfterSorting) {        int intactRows = 0;        Iterator tableIterator = tableAfterSorting.iterator();        while (tableIterator.hasNext() && baseTable.toString().contains(tableIterator.next().toString())) {            intactRows++;        }        return (intactRows == tableAfterSorting.size());    }}