package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.edu.ita.locators.OrderingPageLocators.ORDER_ASSIGNEE_COLUMN;
import static com.softserveinc.edu.ita.locators.OrderingPageLocators.ORDER_NAME_COLUMN;
import static com.softserveinc.edu.ita.locators.OrderingPageLocators.ORDER_STATUS_COLUMN;

public class SearchTest extends TestRunner {
    private List<WebElement> columns;

    @Test(dataProvider = "getOrderSearchTerms", dataProviderClass = DataProviders.class)
    public void testSearch(String searchTerm) throws DAOException {
        HomePage homePage = new HomePage(driver);
        User merchandiser = DBUtility.getMerchandiser();
        UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        for (OrderSearchCondition condition : OrderSearchCondition.values()) {
            orderingPage.setSearchCondition(condition)
                    .fillSearchField(searchTerm)
                    .clickApplyButton();

            columns = getColumnByName(condition);

            if (columns.isEmpty()) {
                loggingSoftAssert.assertTrue(columns.isEmpty(),
                        condition + " " + searchTerm + " not found");
            } else {
                loggingSoftAssert.assertEquals(columns.get(0).getText().toLowerCase(), searchTerm.toLowerCase(),
                        condition + " " + searchTerm);
            }
            loggingSoftAssert.assertAll();
            orderingPage.clearSearchField();
        }
        orderingPage.clickLogOutButton();
    }

    /**
     * sets orderNames or orderStatuses or orderAssignees in list depends on parameters
     *
     * @param condition
     * @return
     */
    private List<WebElement> getColumnByName(OrderSearchCondition condition) {
        switch (condition) {
            case ORDER_NAME:
                return driver.findElements(ORDER_NAME_COLUMN.getBy());
            case STATUS:
                return driver.findElements(ORDER_STATUS_COLUMN.getBy());
            default:
                return driver.findElements(ORDER_ASSIGNEE_COLUMN.getBy());
        }
    }
}
