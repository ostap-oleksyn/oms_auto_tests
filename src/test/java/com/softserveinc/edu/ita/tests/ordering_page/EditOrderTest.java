package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static com.softserveinc.edu.ita.locators.OrderingPageLocators.*;

public class EditOrderTest extends TestRunner {
    private List <WebElement> pricesList;
    private List<WebElement> quantiiesList;
    private List<WebElement> pricesPerLineList;

    private double price;
    private double quantity;
    private double pricePerLine;
    private int[] shownNumberOfItems = {1,2,5,10,25,50};
    @Test
    public void testEditOrder() {
        Random randomGenerator = new Random();

        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn("gorbenko", "qwerty");
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField("gorbenko")
                .clickApplyButton();

        orderingPage.click(EDIT_LINK);
        for (int i = 0; i<shownNumberOfItems.length; i++){
        orderingPage.setNumberOfElements(shownNumberOfItems[i]);

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
        while (pricesList.size()==1){
            pricesList = driver.findElements(PRICE_COLUMN.getBy());
            orderingPage.click(ITEM_NEXT_PAGE_BUTTON);
        }
            loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_NEXT_PAGE_BUTTON)," ");
            loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_LAST_PAGE_BUTTON)," ");


        loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_FIRST_PAGE_BUTTON), "'<b>First Page</b>' button is disabled");
        loggingSoftAssert.assertFalse(orderingPage.isElementEnabled(ITEM_PREVIOUS_PAGE_BUTTON), "'<b>Previous Page</b>' button is disabled");

        loggingSoftAssert.assertAll();

        orderingPage.clickLogOutButton();
    }
}
