package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.enums.ordering_page.SearchConditions;
import com.softserveinc.edu.ita.enums.ordering_page.SortFields;
import com.softserveinc.edu.ita.locators.AddItemPageLocators;
import com.softserveinc.edu.ita.utils.RandomUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * PageObject Class presents page for adding Item (Product) to Order
 */
public class AddItemPage extends LogOutBase {

    public AddItemPage(WebDriver driver) {
        super(driver);
    }

    public int getItemsCount() {
        return driver.findElements(AddItemPageLocators.ITEMS_TABLE_ROWS.getBy()).size();
    }

    public AddItemPage clickRandomSelectItemLink() {
        final int rowNumber = RandomUtil.getRandomInteger(1, getItemsCount());
        click(AddItemPageLocators.SELECT_ITEM_LINK.modify(String.valueOf(rowNumber)));
        return this;
    }

    public AddItemPage clickSelectItemLink(int rowNumber) {
        click(AddItemPageLocators.SELECT_ITEM_LINK.modify(String.valueOf(rowNumber)));
        return this;
    }

    public AddItemPage fillRandomQuantity(int maxQuantity) {
        final int quantity = RandomUtil.getRandomInteger(1, maxQuantity);
        sendKeys(AddItemPageLocators.QUANTITY_INPUT, String.valueOf(quantity));
        return this;
    }

    public AddItemPage selectRandomDimension() {
        Select dimensionSelect = new Select(driver.findElement(AddItemPageLocators.DIMENSION_SELECT.getBy()));
        dimensionSelect.selectByIndex(RandomUtil.getRandomInteger(0, 2));
        return this;
    }

    public NewOrderPage clickDoneButton() {
        click(AddItemPageLocators.DONE_BUTTON);
        return new NewOrderPage(driver);
    }

    public AddItemPage fillSearchInput(String searchTerm) {
        sendKeys(AddItemPageLocators.SEARCH_INPUT, searchTerm);
        return this;
    }

    public AddItemPage clickSearchButton() {
        click(AddItemPageLocators.SEARCH_BUTTON);
        return this;
    }

    public List<String[]> getItemsTable() {
        final List<WebElement> itemsRows = driver.findElements(AddItemPageLocators.ITEMS_TABLE_ROWS.getBy());

        List<WebElement> rowsCells;
        List<String[]> itemsList = new ArrayList<>();

        for (WebElement itemRow : itemsRows) {
            rowsCells = itemRow.findElements(AddItemPageLocators.ITEMS_ROW_CELL.getBy());
            String[] item = {rowsCells.get(0).getText(), rowsCells.get(1).getText()};
            itemsList.add(item);
        }
        return itemsList;
    }

    public AddItemPage selectSearchCondition(SearchConditions searchCondition) {
        Select searchConditionSelect = new Select(driver.findElement(AddItemPageLocators.SEARCH_CONDITION_SELECT.getBy()));

        switch (searchCondition) {
            case SEARCH_BY_NAME:
                searchConditionSelect.selectByIndex(0);
                break;
            case SEARCH_BY_DESCRIPTION:
                searchConditionSelect.selectByIndex(1);
                break;
        }

        return this;
    }

    public void clickSortLink(SortFields sortField) {
        switch (sortField) {
            case SORT_BY_NAME:
                click(AddItemPageLocators.SORT_BY_NAME_HEADER_LINK);
                break;
            case SORT_BY_DESCRIPTION:
                click(AddItemPageLocators.SORT_BY_DESCRIPTION_HEADER_LINK);
                break;
        }
    }
}
