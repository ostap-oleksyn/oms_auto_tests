package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.enums.item_management_page.ItemFilter;
import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import static com.softserveinc.edu.ita.locators.AdministrationPageLocators.FILTER_SELECT;
import static com.softserveinc.edu.ita.locators.AdministrationPageLocators.SEARCH_BUTTON;

public class ItemManagementPage extends LogOutBase {

    public ItemManagementPage(WebDriver driver) {
        super(driver);
    }

    public void setFilters(ItemFilter filter) {
        Select fieldSelect = new Select(driver.findElement(FILTER_SELECT.getBy()));
        fieldSelect.selectByVisibleText(filter.getFilterName());
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected filter - <b>'%s'</b>", filter.getFilterName()));
    }

    public void fillSearchField(String searchTerm) {
        sendKeys(ItemManagementPageLocators.SEARCH_FIELD, searchTerm);
    }

    public String getSelectedFilter() {
        return getElementText(ItemManagementPageLocators.SELECTED_FILTER);
    }

    public void clickSearchButton() {
        click(SEARCH_BUTTON);
    }

    public String getSearchFieldText() {
        return getElementAttribute(ItemManagementPageLocators.SEARCH_FIELD, "value");
    }
}
