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


public class SearchTest extends TestRunner {
    List<WebElement> orderNamesList;

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

            orderNamesList = orderingPage.getOrderFromView(condition);

        if (orderNamesList.isEmpty()) {
            loggingAssert.assertTrue(orderNamesList.isEmpty(),
                    condition + " " + searchTerm + " not found");
        } else {
            loggingAssert.assertEquals(orderNamesList.get(0).getText().toLowerCase(), searchTerm.toLowerCase(),
                    condition + " " + searchTerm);
        }
        orderingPage.clearSearchField();
        }
        orderingPage.clickLogOutButton();
    }
}
