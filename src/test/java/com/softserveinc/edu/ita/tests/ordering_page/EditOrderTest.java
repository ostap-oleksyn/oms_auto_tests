package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ordering_page.ItemsOrderStatus;
import com.softserveinc.edu.ita.enums.ordering_page.ShownElementsNumber;
import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
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

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testNavigation(User merchandiser) {
        Random randomGenerator = new Random();
        Wait wait = new WebDriverWait(driver, 2);

        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField(merchandiser.getLogin())
                .clickApplyButton();

        orderingPage.clickEditLink();

        for (ShownElementsNumber shownElementsNumber : ShownElementsNumber.values()) {
            orderingPage.setNumberOfElements(shownElementsNumber);
            wait.until(ExpectedConditions.presenceOfElementLocated(PRICE_COLUMN.getBy()));

            pricesList = driver.findElements(PRICE_COLUMN.getBy());
            quantiiesList = driver.findElements(QUANTITY_COLUMN.getBy());
            pricesPerLineList = driver.findElements(PRICE_PER_LINE.getBy());

            int item = randomGenerator.nextInt(pricesList.size());

            price = Double.valueOf(pricesList.get(item).getText());
            quantity = Double.valueOf(quantiiesList.get(item).getText());
            pricePerLine = Double.valueOf(pricesPerLineList.get(item).getText());

            loggingSoftAssert.assertTrue(shownElementsNumber.getNumber() >= pricesList.size(), "Elements in tables are shown correctly");
        }

        loggingSoftAssert.assertTrue(price * quantity == pricePerLine, "'Price Per Line' is correctly calculated");

        orderingPage.setNumberOfElements(ShownElementsNumber.ELEMENTS_1);

        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_NEXT_PAGE_BUTTON), "'<b>Next Page</b>' button is enabled ");
        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON), "'<b>Last Page</b>' button is enabled ");

        do {
            pricesList = driver.findElements(PRICE_COLUMN.getBy());
            orderingPage.clickItemNextPageButton();
        } while (orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON));

        loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_NEXT_PAGE_BUTTON), "'<b>Next Page</b>' button is disabled ");
        loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON), "'<b>Last Page</b>' button is disabled ");

        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_FIRST_PAGE_BUTTON), "'<b>First Page</b>' button is enabled");
        loggingSoftAssert.assertTrue(orderingPage.isElementEnabled(ITEM_PREVIOUS_PAGE_BUTTON), "'<b>Previous Page</b>' button is enabled");

        orderingPage.clickLogOutButton();
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testOrderEdit(User merchandiser) {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField(merchandiser.getLogin())
                .clickApplyButton();

        orderingPage.clickEditLink()
                .setItemOrderStatus(ItemsOrderStatus.DELIVERED)
                .clickItemCancelButton();
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ORDER_TABLE), "cancel and return to ordering page");

        orderingPage.clickEditLink()
                .setItemOrderStatus(ItemsOrderStatus.DELIVERED)
                .clickItemSaveButton();

        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ORDER_TABLE), "save and return to ordering page");

        driver.navigate().back();

        orderingPage.clickLogOutButton();

        loggingSoftAssert.assertAll();
    }

    @Test(dataProvider = "getMerchandisers", dataProviderClass = DataProviders.class)
    public void testErrorOrderEdit(User merchandiser) {
        HomePage homePage = new HomePage(driver);
        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(merchandiser.getLogin(), merchandiser.getPassword());
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField(admin.getLogin())
                .clickApplyButton();

        orderingPage.clickEditLink();
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ITEM_ERROR_MESSAGE), "Error message is enabled ");

        orderingPage.clickItemErrorShowButton();
        loggingSoftAssert.assertEquals(driver.findElement(ITEM_DETAILS_ABOUT_ERROR.getBy()).getText(),
                "You can not see order details with another assignee.",
                "show details about error");

        orderingPage.clickItemGoToHomeButton();
        loggingSoftAssert.assertTrue(orderingPage.isElementDisplayed(ORDER_TABLE), "return to ordering page");

        driver.navigate().back();

        orderingPage.clickLogOutButton();

        loggingSoftAssert.assertAll();
    }
}
