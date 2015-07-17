package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.edu.ita.locators.OrderingPageLocators.*;

/**
 * Class to test searching in orders table.
 */
public class SearchTest extends TestRunner {

    @Test(dataProvider = "getOrderSearchTestData", dataProviderClass = DataProviders.class)
    public void testSearch(final User user, final String searchTerm) throws DAOException {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        for (final OrderSearchCondition condition : OrderSearchCondition.values()) {
            orderingPage.setSearchCondition(condition)
                    .fillSearchField(searchTerm)
                    .clickApplyButton();

            List<WebElement> columns = getColumnByName(condition);

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
     * Sets orderNames or orderStatuses or orderAssignees
     * in list depends on parameters
     *
     * @param condition
     * @return
     */
    private List<WebElement> getColumnByName(final OrderSearchCondition condition) {
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
