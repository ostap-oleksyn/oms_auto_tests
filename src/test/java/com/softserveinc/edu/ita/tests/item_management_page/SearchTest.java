package com.softserveinc.edu.ita.tests.item_management_page;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.utils.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Class to check searching actions in "Item Management" page.
 */
public class SearchTest extends TestRunner {

    @Test(dataProvider = "getSupervisors", dataProviderClass = DataProviders.class)
    public void testSearch(User user) throws DAOException {
        final HomePage homePage = new HomePage(driver);
        final UserInfoPage userInfoPage = homePage.logIn(user.getLogin(), user.getPassword());
        final ItemManagementPage itemManagementPage = userInfoPage.clickItemManagementTab();
        itemManagementPage.clickResizeLink();

        for (int i = 0; i < 2; i++) {
            itemManagementPage.setFilters(i);
            final int columnsNumber = i + 1;
            getValidSearchTerms(columnsNumber).forEach(validSearchTerm ->
            {
                itemManagementPage.fillSearchField(validSearchTerm).clickSearchButton();
                loggingSoftAssert.assertTrue(
                        itemManagementPage.isElementDisplayed(
                                ItemManagementPageLocators.PRODUCTS_TABLE_CELL.modify(String.valueOf(columnsNumber), validSearchTerm)),
                        String.format("Searching by '%s' with valid search term is working.",
                                itemManagementPage.getElementText(ItemManagementPageLocators.SEARCH_CONDITION.modify(String.valueOf(columnsNumber)))));
                itemManagementPage.clearSearchField().clickSearchButton();
            });

            itemManagementPage.fillSearchField(getFalseSearchTerm()).clickSearchButton();
            loggingSoftAssert.assertFalse(itemManagementPage.isElementDisplayed(ItemManagementPageLocators.TABLES_GRID),
                    String.format("Searching by '%s' with false search term is working.",
                            itemManagementPage.getElementText(ItemManagementPageLocators.SEARCH_CONDITION.modify(String.valueOf(columnsNumber)))));
            itemManagementPage.clearSearchField().clickSearchButton();
        }

        itemManagementPage.clickLogOutButton();
        loggingSoftAssert.assertAll();
    }

    private List<String> getValidSearchTerms(int columnsNumber) {
        List<WebElement> webElements = driver.findElements(ItemManagementPageLocators.COLUMNS_ELEMENTS.modify(String.valueOf(columnsNumber)).getBy());
        List<String> validSearchTerms = new LinkedList<>();
        validSearchTerms.add(webElements.get(0).getText());
        validSearchTerms.add(webElements.get(RandomUtil.getRandomInteger(1, webElements.size()-1)).getText());
        validSearchTerms.add(webElements.get(webElements.size() - 1).getText());
        return validSearchTerms;
    }

    private String getFalseSearchTerm() {
        return RandomUtil.getRandomString("NameSymbols", 5, 10);
    }
}
