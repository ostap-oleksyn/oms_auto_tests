package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ordering_page.OrderFilter;
import com.softserveinc.edu.ita.enums.ordering_page.RoleFilterValue;
import com.softserveinc.edu.ita.enums.ordering_page.StatusFilterValue;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.edu.ita.enums.ordering_page.OrderFilter.ROLE;
import static com.softserveinc.edu.ita.enums.ordering_page.OrderFilter.STATUS;
import static com.softserveinc.edu.ita.enums.ordering_page.StatusFilterValue.NONE;
import static com.softserveinc.edu.ita.locators.OrderingPageLocators.ORDER_STATUS_COLUMN;
import static com.softserveinc.edu.ita.locators.OrderingPageLocators.ROLE_COLUMN;

public class FilterTest extends TestRunner {
   private List<WebElement> ordersList;
   private List<WebElement> ordersListBeforeFilter;
   private List<WebElement> ordersListAfterFilter;


    @Test
    public void testFilterStatus() {
        HomePage homePage = new HomePage(driver);
        User merchandiser = DBUtility.getMerchandiser();
        UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        for (StatusFilterValue value : StatusFilterValue.values()) {
            orderingPage.setFilter(STATUS)
                    .clickApplyButton()
                    .setFilterValue(value)
                    .clickApplyButton();
        /*
          skips filter value NONE because we have the separate method testNoneFilter()
         */
            if (value != NONE) {
                ordersList = getOrderFromView(STATUS);

                if (ordersList.isEmpty()) {
                    loggingSoftAssert.assertTrue(ordersList.isEmpty(), STATUS + " " + value + " not found");
                } else {
                    loggingSoftAssert.assertEquals(ordersList.get(0).getText().toLowerCase(), value.name().toLowerCase(),
                            STATUS + " " + value);
                }
                loggingSoftAssert.assertAll();

                orderingPage.clearSearchField();
            }
        }
        orderingPage.clickLogOutButton();
    }

    @Test
    public void testFilterRole() {
        HomePage homePage = new HomePage(driver);
        User merchandiser = DBUtility.getMerchandiser();
        UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        for (RoleFilterValue value : RoleFilterValue.values()) {
            orderingPage.setFilter(ROLE)
                    .clickApplyButton()
                    .setFilterValue(value)
                    .clickApplyButton();
        /*
          skips filter value NONE because we have the separate method testNoneFilter()
        */
            if (value != RoleFilterValue.NONE) {
                ordersList = getOrderFromView(OrderFilter.ROLE);

                if (ordersList.isEmpty()) {
                    loggingSoftAssert.assertTrue(ordersList.isEmpty(), ROLE + " " + value + " not found");
                } else {
                    loggingSoftAssert.assertEquals(ordersList.get(0).getText().toLowerCase(), value.name().toLowerCase(),
                            ROLE + " " + value);
                }
                loggingSoftAssert.assertAll();

                orderingPage.clearSearchField();
            }
        }
        orderingPage.clickLogOutButton();
    }

    @Test
    public void testNoneFilter() {
        HomePage homePage = new HomePage(driver);
        User merchandiser = DBUtility.getMerchandiser();
        UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        ordersListBeforeFilter = getOrderFromView(STATUS);
        orderingPage.setFilter(STATUS)
                .setFilterValue(StatusFilterValue.NONE)
                .clickApplyButton();
        ordersListAfterFilter = getOrderFromView(STATUS);
        loggingAssert.assertEquals(ordersListBeforeFilter.toString(), ordersListAfterFilter.toString(), STATUS + " " + NONE);

        ordersListBeforeFilter = getOrderFromView(ROLE);
        orderingPage.setFilter(ROLE)
                .clickApplyButton()
                .setFilterValue(RoleFilterValue.NONE)
                .clickApplyButton();
        ordersListAfterFilter = getOrderFromView(ROLE);
        loggingAssert.assertEquals(ordersListBeforeFilter.toString(), ordersListAfterFilter.toString(), ROLE + " " + NONE);


        orderingPage.clickLogOutButton();
    }

    /**
     * sets orderStatuses or userRoles depends on parameters
     *
     * @param filter
     * @return
     */
    private List<WebElement> getOrderFromView(OrderFilter filter) {
        switch (filter) {
            case STATUS:
                return driver.findElements(ORDER_STATUS_COLUMN.getBy());
            case ROLE:
                return driver.findElements(ROLE_COLUMN.getBy());
            default:
                return null;
        }
    }
}
