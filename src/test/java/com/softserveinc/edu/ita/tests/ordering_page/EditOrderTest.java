package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.enums.ordering_page.OrderSearchCondition;
import com.softserveinc.edu.ita.locators.OrderingPageLocators;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.OrderingPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by student on 6/30/2015.
 */
public class EditOrderTest extends TestRunner {
    @Test
    public void testEditOrder() {
        HomePage homePage = new HomePage(driver);
        UserInfoPage userInfoPage = homePage.logIn("gorbenko", "qwerty");
        OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        orderingPage.setSearchCondition(OrderSearchCondition.ASSIGNEE)
                .fillSearchField("gorbenko")
                .clickApplyButton();

        orderingPage.click(OrderingPageLocators.EDIT_LINK);
        orderingPage.setNumberOfElements(25);

        List <WebElement> prices = driver.findElements(OrderingPageLocators.PRICE_COLUMN.getBy());
        List<WebElement> quantiies = driver.findElements(OrderingPageLocators.QUANTITY_COLUMN.getBy());
        List<WebElement> pricesPerLine = driver.findElements(OrderingPageLocators.PRICE_PER_LINE.getBy());

        System.out.println(prices.size());
        System.out.println(quantiies.size());
        System.out.println(pricesPerLine.size());


        orderingPage.clickLogOutButton();
    }
}
