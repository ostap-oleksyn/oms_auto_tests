package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.domains.UserFromView;
import com.softserveinc.edu.ita.enums.AdministrationTabConditions;
import com.softserveinc.edu.ita.enums.AdministrationTabFilters;
import com.softserveinc.edu.ita.page_object.AdministrationPage;
import com.softserveinc.edu.ita.page_object.HomePage;
import com.softserveinc.edu.ita.page_object.UserInfoPage;
import com.softserveinc.edu.ita.utils.DBUtility;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.edu.ita.locators.AdministrationPageLocators.*;


public class AdministrationSearchTest extends TestRunner {
    List<UserFromView> expectedUsersList;
    List<User> actualUsersList;
    private String searchTerm;

    @Test(dataProvider = "getSearchFilters", dataProviderClass = DataProviders.class)
    public void testAllColumnsSearch(AdministrationTabFilters filters) throws DAOException {
        HomePage homePage = new HomePage(driver);
        User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        searchTerm = "ivanka";
        for (AdministrationTabConditions conditions : AdministrationTabConditions.values()) {
            setFilters(filters.getFilterName())
                    .setConditions(conditions)
                    .fillSearchField(searchTerm)
                    .clickSearchButton();

            expectedUsersList = administrationPage.getTableFromView();
            clearSearchField();
            actualUsersList = DBUtility.getUserDao().getUsersBy(filters.getValue(), conditions, searchTerm);

            loggingAssert.assertEquals(actualUsersList.size(), expectedUsersList.size(), filters + " " + conditions + " " + searchTerm);
        }
        administrationPage.clickLogOutButton();
    }
    
    /**
     * selects filter type
     *
     * @param filters
     * @return
     */
    private AdministrationSearchTest setFilters(String filters) {
        Select fieldSelect = new Select(driver.findElement(FILTER_SELECT.getBy()));
        fieldSelect.selectByVisibleText(filters.toString());
        return this;
    }

    /**
     * selects condition type
     *
     * @param conditions
     * @return
     */
    private AdministrationSearchTest setConditions(AdministrationTabConditions conditions) {
        Select conditionSelect = new Select(driver.findElement(CONDITION_SELECT.getBy()));
        conditionSelect.selectByVisibleText(conditions.toString());
        return this;
    }

    /**
     * inputs search text into search field
     *
     * @param searchTerm
     * @return
     */
    private AdministrationSearchTest fillSearchField(String searchTerm) {
        driver.findElement(SEARCH_FIELD.getBy()).sendKeys(searchTerm);
        return this;
    }

    /**
     * click on the search button
     */
    private void clickSearchButton() {
        driver.findElement(SEARCH_BUTTON.getBy()).click();
    }

    private void clearSearchField() {
        driver.findElement(SEARCH_FIELD.getBy()).clear();
    }
}
