package com.softserveinc.edu.ita.tests.item_management_page;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.enums.item_management_page.ItemFilter;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.ItemManagementPage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.utils.RandomUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Class to test searching actions in "Item Management" page.
 */
public class SearchTest extends TestRunner {

    ItemManagementPage itemManagementPage;

    @Test(dataProvider = "getProductTablesFilters", dataProviderClass = DataProviders.class)
    public void testSearch(final ItemFilter filter) throws DAOException {
        final HomePage homePage = new HomePage(driver);
        final User randomSupervisor = DBUtility.getRandomUserByRole(Roles.SUPERVISOR);
        final UserInfoPage userInfoPage = homePage.logIn(randomSupervisor.getLogin(), randomSupervisor.getPassword());
        itemManagementPage = userInfoPage.clickItemManagementTab();
        itemManagementPage.clickResizeLink();

        itemManagementPage.setFilters(filter);

        getValidSearchTerms(filter).forEach(validSearchTerm ->
        {
            itemManagementPage.fillSearchField(validSearchTerm).clickSearchButton();
            loggingAssert.assertTrue(itemManagementPage.isElementDisplayed(
                            ItemManagementPageLocators.TABLE_CELLS_WITH_TEXT.modify(validSearchTerm)),
                    String.format("Searching by '%s' is working.", filter.getFilterName()));
            itemManagementPage.clearSearchField().clickSearchButton();
        });

        final String invalidSearchTerm = RandomUtil.getRandomString("NameSymbols", 5, 10);
        itemManagementPage.fillSearchField(invalidSearchTerm).clickSearchButton();
        loggingAssert.assertFalse(itemManagementPage.isElementDisplayed(
                        ItemManagementPageLocators.TABLES_GRID),
                String.format("Searching by '%s' is working.", filter.getFilterName()));
        itemManagementPage.clickLogOutButton();
    }

    private List<String> getValidSearchTerms(final ItemFilter filter) {
        final List<WebElement> webElements = driver.findElements(ItemManagementPageLocators.COLUMNS_ELEMENTS.modify(String.valueOf(filter.ordinal() + 1)).getBy());
        final List<String> validSearchTerms = new LinkedList<>();
        validSearchTerms.add(webElements.get(0).getText());
        validSearchTerms.add(webElements.get(RandomUtil.getRandomInteger(1, webElements.size() - 1)).getText());
        validSearchTerms.add(webElements.get(webElements.size() - 1).getText());
        return validSearchTerms;
    }

    @AfterMethod
    public void logOut() {
        itemManagementPage.clickLogOutButton();
    }
}
