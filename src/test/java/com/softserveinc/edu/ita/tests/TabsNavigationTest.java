package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.administration_page.SearchConditions;
import com.softserveinc.edu.ita.enums.administration_page.SearchFilters;
import com.softserveinc.edu.ita.enums.item_management_page.ItemFilter;
import com.softserveinc.edu.ita.enums.ordering_page.OrderFilter;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.enums.ordering_page.RoleFilterValue;
import com.softserveinc.edu.ita.enums.ordering_page.StatusFilterValue;
import com.softserveinc.edu.ita.locators.*;
import com.softserveinc.edu.ita.pageobjects.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.softserveinc.edu.ita.utils.EnumUtil.getRandomEnum;
import static com.softserveinc.edu.ita.utils.RandomUtil.getRandomString;

public class TabsNavigationTest extends TestRunner {

    private HomePage homePage;
    private UserInfoPage userInfoPage;
    private AdministrationPage administrationPage;
    private OrderingPage orderingPage;
    private ItemManagementPage itemManagementPage;
    private String searchTerm;

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void administratorTabsTest(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(Roles.getRoleName(user.getRoleReference())), "User roles match");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB),
                "User Info tab displayed");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ADMINISTRATION_TAB),
                "Administration tab displayed");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB)),
                "User info tab is the default tab");

        administrationPage = userInfoPage.clickAdministrationTab();

        loggingAssert.assertTrue(administrationPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(administrationPage.getElementText(CommonLocators.ADMINISTRATION_TAB)),
                "Switched to Administration tab");

        loggingAssert.assertTrue(administrationPage.isElementDisplayed(AdministrationPageLocators.CREATE_NEW_USER_LINK),
                "Create new user link displayed");

        userInfoPage = administrationPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB)),
                "Switched to User Info tab");
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void merchandiserTabsTest(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(Roles.getRoleName(user.getRoleReference())), "Users roles match");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB),
                "User Info tab displayed");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ORDERING_TAB),
                "Ordering tab displayed");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB)),
                "User info tab is the default tab");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(orderingPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(orderingPage.getElementText(CommonLocators.ORDERING_TAB)),
                "Switched to Ordering tab");

        loggingAssert.assertTrue(orderingPage.isElementDisplayed(OrderingPageLocators.CREATE_NEW_ORDER_LINK),
                "Create new order link displayed");

        userInfoPage = orderingPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB)),
                "Switched to User Info tab");
    }

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void customerTabsTest(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(Roles.getRoleName(user.getRoleReference())), "User rolees match");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB),
                "User Info tab displayed");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ORDERING_TAB),
                "Ordering tab displayed");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB)),
                "User info tab is the default tab");

        orderingPage = userInfoPage.clickOrderingTab();

        loggingAssert.assertTrue(orderingPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(orderingPage.getElementText(CommonLocators.ORDERING_TAB)),
                "Switched to Ordering tab");

        loggingAssert.assertTrue(orderingPage.isElementDisplayed(OrderingPageLocators.CREATE_NEW_ORDER_LINK),
                "Create new order link displayed");

        userInfoPage = orderingPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB)),
                "Switched to User Info tab");
    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void supervisorTabsTest(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());

        loggingAssert.assertTrue(userInfoPage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL)
                .equals(Roles.getRoleName(user.getRoleReference())), "User roles match");

        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.USER_INFO_TAB),
                "User Info tab displayed");
        loggingAssert.assertTrue(userInfoPage.isElementDisplayed(CommonLocators.ITEM_MANAGEMENT_TAB),
                "Item Management displayed");

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB)),
                "User info tab is the default tab");

        itemManagementPage = userInfoPage.clickItemManagementTab();

        loggingAssert.assertTrue(itemManagementPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(itemManagementPage.getElementText(CommonLocators.ITEM_MANAGEMENT_TAB)),
                "Switched to Item Management tab");

        loggingAssert.assertTrue(itemManagementPage.isElementDisplayed(ItemManagementPageLocators.ADD_PRODUCT_LINK),
                "Add Product link displayed");

        userInfoPage = itemManagementPage.clickUserInfoTab();

        loggingAssert.assertTrue(userInfoPage.getElementText(CommonLocators.ACTIVE_TAB)
                        .equals(userInfoPage.getElementText(CommonLocators.USER_INFO_TAB)),
                "Switched to User Info tab");
    }

    @Test(dataProvider = "getAdministrators", dataProviderClass = DataProviders.class)
    public void administrationTabStateTest(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        administrationPage = userInfoPage.clickAdministrationTab();

        final SearchConditions condition = getRandomEnum(SearchConditions.class);
        final SearchFilters filter = getRandomEnum(SearchFilters.class);
        searchTerm = getRandomString("NameSymbols", 5, 10);

        administrationPage.setFilters(filter)
                .setCondition(condition)
                .fillSearchField(searchTerm)
                .clickSearchButton();

        userInfoPage = administrationPage.clickUserInfoTab();
        administrationPage = userInfoPage.clickAdministrationTab();

        loggingSoftAssert.assertEquals(administrationPage.getElementText(AdministrationPageLocators.SELECTED_FILTER), filter.getFilterName(),
                String.format("Selected filter didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filter.getFilterName(), administrationPage.getElementText(AdministrationPageLocators.SELECTED_FILTER)));

        loggingSoftAssert.assertEquals(administrationPage.getElementText(AdministrationPageLocators.SELECTED_CONDITION), condition.getCondition(),
                String.format("Selected condition didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , condition.getCondition(), administrationPage.getElementText(AdministrationPageLocators.SELECTED_CONDITION)));

        loggingSoftAssert.assertEquals(administrationPage.getSearchFieldText(), searchTerm,
                String.format("Typed in search term didn't cleared: expected - <b>%s</b>; actual - <b>%s</b>;",
                        searchTerm, administrationPage.getSearchFieldText()));

        loggingSoftAssert.assertAll();
    }

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void itemManagementTabStateTest(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        itemManagementPage = userInfoPage.clickItemManagementTab();

        final ItemFilter filter = getRandomEnum(ItemFilter.class);
        searchTerm = getRandomString("NameSymbols", 5, 10);

        itemManagementPage.setFilters(filter)
                .fillSearchField(searchTerm)
                .clickSearchButton();

        userInfoPage = itemManagementPage.clickUserInfoTab();
        itemManagementPage = userInfoPage.clickItemManagementTab();

        loggingSoftAssert.assertEquals(itemManagementPage.getElementText(ItemManagementPageLocators.SELECTED_FILTER), filter.getFilterName(),
                String.format("Selected filter didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filter.getFilterName(), itemManagementPage.getElementText(ItemManagementPageLocators.SELECTED_FILTER)));

        loggingSoftAssert.assertEquals(itemManagementPage.getSearchFieldText(), searchTerm,
                String.format("Typed in search term didn't cleared: expected - <b>%s</b>; actual - <b>%s</b>;",
                        searchTerm, itemManagementPage.getSearchFieldText()));

        loggingSoftAssert.assertAll();
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void merchandiserOrderingTabStateTest(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        orderingPage = userInfoPage.clickOrderingTab();

        final OrderFilter filter = getRandomEnum(OrderFilter.class);
        final Enum filterValue;
        final OrderSearchCondition searchCondition = getRandomEnum(OrderSearchCondition.class);
        searchTerm = getRandomString("NameSymbols", 5, 10);

        if (filter.equals(OrderFilter.ROLE)) {
            filterValue = getRandomEnum(RoleFilterValue.class);
        } else {
            filterValue = getRandomEnum(StatusFilterValue.class);
        }

        orderingPage.setFilter(filter)
                .clickApplyButton()
                .setFilterValue(filterValue)
                .setSearchCondition(searchCondition)
                .fillSearchField(searchTerm)
                .clickApplyButton();

        userInfoPage = orderingPage.clickUserInfoTab();
        orderingPage = userInfoPage.clickOrderingTab();

        loggingSoftAssert.assertEquals(orderingPage.getElementText(OrderingPageLocators.SELECTED_FILTER), filter.getFilterName(),
                String.format("Selected filter didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filter.getFilterName(), orderingPage.getElementText(OrderingPageLocators.SELECTED_FILTER)));

        loggingSoftAssert.assertEquals(orderingPage.getElementText(OrderingPageLocators.SELECTED_FILTER_VALUE), filterValue.toString(),
                String.format("Selected filter value didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filterValue.toString(), orderingPage.getElementText(OrderingPageLocators.SELECTED_FILTER_VALUE)));

        loggingSoftAssert.assertEquals(orderingPage.getElementText(OrderingPageLocators.SELECTED_SEARCH_CONDITION), searchCondition.getSearchCondition(),
                String.format("Selected search condition didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , searchCondition.getSearchCondition(), orderingPage.getElementText(OrderingPageLocators.SELECTED_SEARCH_CONDITION)));

        loggingSoftAssert.assertEquals(orderingPage.getSearchFieldText(), searchTerm,
                String.format("Typed in search term didn't cleared: expected - <b>%s</b>; actual - <b>%s</b>;",
                        searchTerm, orderingPage.getSearchFieldText()));

        loggingSoftAssert.assertAll();
    }

    @Test(dataProvider = "getCustomers", dataProviderClass = DataProviders.class)
    public void customerOrderingTabStateTest(final User user) {
        homePage = new HomePage(driver);
        userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        orderingPage = userInfoPage.clickOrderingTab();

        final OrderFilter filter = getRandomEnum(OrderFilter.class);
        final Enum filterValue;
        final OrderSearchCondition searchCondition = getRandomEnum(OrderSearchCondition.class);
        searchTerm = getRandomString("NameSymbols", 5, 10);

        if (filter.equals(OrderFilter.ROLE)) {
            filterValue = getRandomEnum(RoleFilterValue.class, 0);
        } else {
            filterValue = getRandomEnum(StatusFilterValue.class, 0);
        }

        orderingPage.setFilter(filter)
                .clickApplyButton()
                .setFilterValue(filterValue)
                .setSearchCondition(searchCondition)
                .fillSearchField(searchTerm)
                .clickApplyButton();

        userInfoPage = orderingPage.clickUserInfoTab();
        orderingPage = userInfoPage.clickOrderingTab();

        loggingSoftAssert.assertEquals(orderingPage.getElementText(OrderingPageLocators.SELECTED_FILTER), filter.getFilterName(),
                String.format("Selected filter didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filter.getFilterName(), orderingPage.getElementText(OrderingPageLocators.SELECTED_FILTER)));

        loggingSoftAssert.assertEquals(orderingPage.getElementText(OrderingPageLocators.SELECTED_FILTER_VALUE), filterValue.toString(),
                String.format("Selected filter value didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , filterValue.toString(), orderingPage.getElementText(OrderingPageLocators.SELECTED_FILTER_VALUE)));

        loggingSoftAssert.assertEquals(orderingPage.getElementText(OrderingPageLocators.SELECTED_SEARCH_CONDITION), searchCondition.getSearchCondition(),
                String.format("Selected search condition didn't revert to default: expected - <b>%s</b>; actual - <b>%s</b>;"
                        , searchCondition.getSearchCondition(), orderingPage.getElementText(OrderingPageLocators.SELECTED_SEARCH_CONDITION)));

        loggingSoftAssert.assertEquals(orderingPage.getSearchFieldText(), searchTerm,
                String.format("Typed in search term didn't cleared: expected - <b>%s</b>; actual - <b>%s</b>;",
                        searchTerm, orderingPage.getSearchFieldText()));

        loggingSoftAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        userInfoPage.clickLogOutButton();
    }
}
