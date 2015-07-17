package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.enums.ordering_page.ShownElementsNumber;
import com.softserveinc.edu.ita.pageobjects.EditOrderPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.utils.RandomUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.edu.ita.locators.EditOrderPageLocators.*;
import static com.softserveinc.edu.ita.locators.OrderingPageLocators.EDIT_LINK;

/**
 * Class to test order navigation functionality.
 */
public class NavigationEditOrderTest extends TestRunner {


    // test fail expected, no orders for customer
    @Test(dataProvider = "getMerchandiserAndCustomer", dataProviderClass = DataProviders.class)
    public void testNavigation(final User user) {
        double price = 0;
        double quantity = 0;
        double pricePerLine = 0;

        final Wait wait = new WebDriverWait(driver, 2);

        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField(user.getLogin())
                .clickApplyButton();
        if (!orderingPage.isElementDisplayed(EDIT_LINK)) {
            loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(EDIT_LINK),
                    "There are no orders which assign for " + user.getLogin());
            orderingPage.clickLogOutButton();
        }
        final EditOrderPage editOrderPage = orderingPage.clickEditLink();

        List<WebElement> pricesList;
        for (final ShownElementsNumber shownElementsNumber : ShownElementsNumber.values()) {
            editOrderPage.setNumberOfElements(shownElementsNumber);

            wait.until(ExpectedConditions.presenceOfElementLocated(PRICE_COLUMN.getBy()));

            pricesList = driver.findElements(PRICE_COLUMN.getBy());
            List<WebElement> quantitiesList = driver.findElements(QUANTITY_COLUMN.getBy());
            List<WebElement> pricesPerLineList = driver.findElements(PRICE_PER_LINE.getBy());

            final int item = RandomUtil.getRandomInteger(0, pricesList.size());

            price = Double.valueOf(pricesList.get(item).getText());
            quantity = Double.valueOf(quantitiesList.get(item).getText());
            pricePerLine = Double.valueOf(pricesPerLineList.get(item).getText());

            loggingSoftAssert.assertTrue(shownElementsNumber.getNumber() >= pricesList.size(), "Elements in tables are shown correctly");
        }

        loggingSoftAssert.assertTrue(price * quantity == pricePerLine, "'Price Per Line' is correctly calculated");

        editOrderPage.setNumberOfElements(ShownElementsNumber.ELEMENTS_1);

        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_NEXT_PAGE_BUTTON), "'<b>Next Page</b>' button is enabled ");
        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON), "'<b>Last Page</b>' button is enabled ");

        do {
            pricesList = driver.findElements(PRICE_COLUMN.getBy());
            editOrderPage.clickItemNextPageButton();
        } while (orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON));

        loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_NEXT_PAGE_BUTTON), "'<b>Next Page</b>' button is disabled ");
        loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON), "'<b>Last Page</b>' button is disabled ");

        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_FIRST_PAGE_BUTTON), "'<b>First Page</b>' button is enabled");
        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_PREVIOUS_PAGE_BUTTON), "'<b>Previous Page</b>' button is enabled");

        editOrderPage.clickLogOutButton();
    }
}
