package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.administration_page.SearchConditions;
import com.softserveinc.edu.ita.enums.administration_page.SearchFilters;
import com.softserveinc.edu.ita.enums.item_management_page.ItemFilter;
import com.softserveinc.edu.ita.enums.ordering_page.OrderFilter;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.enums.ordering_page.RoleFilterValue;
import com.softserveinc.edu.ita.enums.ordering_page.StatusFilterValue;
import com.softserveinc.edu.ita.pageobjects.*;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.testng.annotations.Test;

import static com.softserveinc.edu.ita.utils.EnumUtil.getRandomEnum;
import static com.softserveinc.edu.ita.utils.StringsGenerator.generateString;

public class RevertToDefaultStateTest extends TestRunner {

    private HomePage homePage;
    private UserInfoPage userInfoPage;
    private OrderingPage orderingPage;
    private String searchTerm;

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void administrationTabTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        final SearchConditions condition = getRandomEnum(SearchConditions.class, 0);
        final SearchFilters filter = getRandomEnum(SearchFilters.class, 0);
        searchTerm = generateString("NameSymbols", 5, 10);

        administrationPage.setFilters(filter);
        administrationPage.setCondition(condition);
        administrationPage.fillSearchField(searchTerm);
        administrationPage.clickSearchButton();

        userInfoPage = administrationPage.clickUserInfoTab();
        administrationPage = userInfoPage.clickAdministrationTab();

        loggingSoftAssert.assertEquals(administrationPage.getSelectedFilter(), filter.getFilterName(),
                String.format("Selected filter didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filter.getFilterName(), administrationPage.getSelectedFilter()));

        loggingSoftAssert.assertEquals(administrationPage.getSelectedCondition(), condition.getCondition(),
                String.format("Selected condition didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , condition.getCondition(), administrationPage.getSelectedCondition()));

        loggingSoftAssert.assertEquals(administrationPage.getSearchFieldText(), searchTerm,
                String.format("Typed in search term didn't cleared: expected - <b>%s</b>; actual - <b>%s</b>;",
                        searchTerm, administrationPage.getSearchFieldText()));

        administrationPage.clickLogOutButton();
    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void itemManagementTabTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();

        final ItemFilter filter = getRandomEnum(ItemFilter.class, 0);
        searchTerm = generateString("NameSymbols", 5, 10);

        itemManagementPage.setFilters(filter);
        itemManagementPage.fillSearchField(searchTerm);
        itemManagementPage.clickSearchButton();

        userInfoPage = itemManagementPage.clickUserInfoTab();
        itemManagementPage = userInfoPage.clickItemManagementTab();

        loggingSoftAssert.assertEquals(itemManagementPage.getSelectedFilter(), filter.getFilterName(),
                String.format("Selected filter didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filter.getFilterName(), itemManagementPage.getSelectedFilter()));

        loggingSoftAssert.assertEquals(itemManagementPage.getSearchFieldText(), searchTerm,
                String.format("Typed in search term didn't cleared: expected - <b>%s</b>; actual - <b>%s</b>;",
                        searchTerm, itemManagementPage.getSearchFieldText()));

        itemManagementPage.clickLogOutButton();
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void merchandiserOrderingTabTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        orderingPage = userInfoPage.clickOrderingTab();

        final OrderFilter filter = getRandomEnum(OrderFilter.class, 0);
        final Enum filterValue;
        final OrderSearchCondition searchCondition = getRandomEnum(OrderSearchCondition.class, 0);
        searchTerm = generateString("NameSymbols", 5, 10);

        if (filter.equals(OrderFilter.ROLE)) {
            filterValue = getRandomEnum(RoleFilterValue.class, 0);
        } else {
            filterValue = getRandomEnum(StatusFilterValue.class, 0);
        }

        orderingPage.setFilter(filter);
        orderingPage.clickApplyButton();
        orderingPage.setFilterValue(filterValue);
        orderingPage.setSearchCondition(searchCondition);
        orderingPage.fillSearchField(searchTerm);
        orderingPage.clickApplyButton();

        userInfoPage = orderingPage.clickUserInfoTab();
        orderingPage = userInfoPage.clickOrderingTab();

        loggingSoftAssert.assertEquals(orderingPage.getSelectedFilter(), filter.getFilterName(),
                String.format("Selected filter didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filter.getFilterName(), orderingPage.getSelectedFilter()));

        loggingSoftAssert.assertEquals(orderingPage.getSelectedFilterValue(), filterValue.toString(),
                String.format("Selected filter value didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filterValue.toString(), orderingPage.getSelectedFilterValue()));

        loggingSoftAssert.assertEquals(orderingPage.getSelectedSearchCondition(), searchCondition.getSearchCondition(),
                String.format("Selected search condition didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , searchCondition.getSearchCondition(), orderingPage.getSelectedSearchCondition()));

        loggingSoftAssert.assertEquals(orderingPage.getSearchFieldText(), searchTerm,
                String.format("Typed in search term didn't cleared: expected - <b>%s</b>; actual - <b>%s</b>;",
                        searchTerm, orderingPage.getSearchFieldText()));

        orderingPage.clickLogOutButton();
    }

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void customerOrderingTabTest(User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        orderingPage = userInfoPage.clickOrderingTab();

        final OrderFilter filter = getRandomEnum(OrderFilter.class, 0);
        final Enum filterValue;
        final OrderSearchCondition searchCondition = getRandomEnum(OrderSearchCondition.class, 0);
        searchTerm = generateString("NameSymbols", 5, 10);

        if (filter.equals(OrderFilter.ROLE)) {
            filterValue = getRandomEnum(RoleFilterValue.class, 0);
        } else {
            filterValue = getRandomEnum(StatusFilterValue.class, 0);
        }

        orderingPage.setFilter(filter);
        orderingPage.clickApplyButton();
        orderingPage.setFilterValue(filterValue);
        orderingPage.setSearchCondition(searchCondition);
        orderingPage.fillSearchField(searchTerm);
        orderingPage.clickApplyButton();

        userInfoPage = orderingPage.clickUserInfoTab();
        orderingPage = userInfoPage.clickOrderingTab();

        loggingSoftAssert.assertEquals(orderingPage.getSelectedFilter(), filter.getFilterName(),
                String.format("Selected filter didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filter.getFilterName(), orderingPage.getSelectedFilter()));

        loggingSoftAssert.assertEquals(orderingPage.getSelectedFilterValue(), filterValue.toString(),
                String.format("Selected filter value didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filterValue.toString(), orderingPage.getSelectedFilterValue()));

        loggingSoftAssert.assertEquals(orderingPage.getSelectedSearchCondition(), searchCondition.getSearchCondition(),
                String.format("Selected search condition didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , searchCondition.getSearchCondition(), orderingPage.getSelectedSearchCondition()));

        loggingSoftAssert.assertEquals(orderingPage.getSearchFieldText(), searchTerm,
                String.format("Typed in search term didn't cleared: expected - <b>%s</b>; actual - <b>%s</b>;",
                        searchTerm, orderingPage.getSearchFieldText()));

        orderingPage.clickLogOutButton();
    }
}
