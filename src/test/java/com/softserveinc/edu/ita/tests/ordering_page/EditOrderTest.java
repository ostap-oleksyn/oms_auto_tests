package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.enums.ordering_page.ItemsOrderStatus;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static com.softserveinc.edu.ita.locators.OrderingPageLocators.*;

public class EditOrderTest extends TestRunner {
    private List<WebElement> pricesList;
    private List<WebElement> quantiiesList;
    private List<WebElement> pricesPerLineList;

    private double price;
    private double quantity;
    private double pricePerLine;
    private int[] shownNumberOfItems = {1, 2, 5, 10, 25, 50};

    @Test
    public void testNavigation() {
        Random randomGenerator = new Random();
        Wait wait = new WebDriverWait(driver, 2);

        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn("gorbenko", "qwerty");
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField("gorbenko")
                .clickApplyButton();

        orderingPage.click(EDIT_LINK);
        for (int i = 0; i < shownNumberOfItems.length; i++) {
            orderingPage.setNumberOfElements(shownNumberOfItems[i]);
            wait.until(ExpectedConditions.presenceOfElementLocated(PRICE_COLUMN.getBy()));
            pricesList = driver.findElements(PRICE_COLUMN.getBy());
            quantiiesList = driver.findElements(QUANTITY_COLUMN.getBy());
            pricesPerLineList = driver.findElements(PRICE_PER_LINE.getBy());

            int item = randomGenerator.nextInt(pricesList.size());

            price = Double.valueOf(pricesList.get(item).getText());
            quantity = Double.valueOf(quantiiesList.get(item).getText());
            pricePerLine = Double.valueOf(pricesPerLineList.get(item).getText());

            loggingSoftAssert.assertTrue(shownNumberOfItems[i] >= pricesList.size(), "");
        }

        loggingSoftAssert.assertTrue(price * quantity == pricePerLine, "'Price Per Line' is correctly calculated");

        orderingPage.setNumberOfElements(shownNumberOfItems[0]);

        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_NEXT_PAGE_BUTTON), "'<b>Next Page</b>' button is enabled ");
        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON), "'<b>Last Page</b>' button is enabled ");

        do {
            pricesList = driver.findElements(PRICE_COLUMN.getBy());
            orderingPage.click(ITEM_NEXT_PAGE_BUTTON);
        } while (orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON));

        loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_NEXT_PAGE_BUTTON), "'<b>Next Page</b>' button is disabled ");
        loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON), "'<b>Last Page</b>' button is disabled ");

        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_FIRST_PAGE_BUTTON), "'<b>First Page</b>' button is enabled");
        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_PREVIOUS_PAGE_BUTTON), "'<b>Previous Page</b>' button is enabled");
    }

    @Test
    public void testOrderEdit(){
        Wait wait = new WebDriverWait(driver, 2);

        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn("gorbenko", "qwerty");
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField("gorbenko")
                .clickApplyButton();

        orderingPage.click(EDIT_LINK);
        wait.until(ExpectedConditions.presenceOfElementLocated(ITEM_ORDER_STATUS.getBy()));
        orderingPage.setItemOrderStatus(ItemsOrderStatus.DELIVERED);

        orderingPage.click(ITEM_CANCEL_BUTTON);
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ORDER_TABLE), "cancel and return to ordering page");

        orderingPage.click(EDIT_LINK);
        wait.until(ExpectedConditions.presenceOfElementLocated(ITEM_ORDER_STATUS.getBy()));
        orderingPage.setItemOrderStatus(ItemsOrderStatus.DELIVERED);
        orderingPage.click(ITEM_SAVE_BUTTON);
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ORDER_TABLE),"save and return to ordering page");

        loggingSoftAssert.assertAll();

        orderingPage.clickLogOutButton();
    }

    @Test
    public void testErrorOrderEdit(){
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn("gorbenko", "qwerty");
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField("iva")
                .clickApplyButton();

        orderingPage.click(EDIT_LINK);
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ITEM_ERROR_MESSAGE),"Error message is enabled " );

        orderingPage.click(ITEM_ERROR_SHOW_BUTTON);
        loggingSoftAssert.assertEquals(driver.findElement(ITEM_DETAILS_ABOUT_ERROR.getBy()).getText(),
                "You can not see order details with another assignee.",
                "show details about error");

        orderingPage.click(ITEM_GO_TO_HOME_BUTTON);
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ORDER_TABLE), "return to ordering page");

        loggingSoftAssert.assertAll();

        orderingPage.clickLogOutButton();
    }
}
