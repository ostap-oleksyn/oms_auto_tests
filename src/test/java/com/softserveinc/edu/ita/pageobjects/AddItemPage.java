package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.locators.AddItemPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * PageObject Class presents page for adding Item (Product) to Order
 */
public class AddItemPage extends LogOutBase {

    private Random randomGenerator = new Random();

    public AddItemPage(WebDriver driver) {
        super(driver);
    }

    public int getItemsCount() {
        return driver.findElements(AddItemPageLocators.ITEMS_TABLE_ROWS.getBy()).size();
    }

    public AddItemPage clickRandomSelectItemLink() {
        int rowNumber = randomGenerator.nextInt(getItemsCount() - 1) + 1;
        driver.findElement(By.xpath(String.format(AddItemPageLocators.SELECT_ITEM_LINK, rowNumber))).click();
        return this;
    }

    public AddItemPage clickSelectItemLink(int rowNumber) {
        driver.findElement(By.xpath(String.format(AddItemPageLocators.SELECT_ITEM_LINK, rowNumber))).click();
        return this;
    }

    public AddItemPage fillRandomQuantity(int maxQuantity) {
        final int quantity = randomGenerator.nextInt(maxQuantity - 1) + 1;
        final WebElement quantityInput = driver.findElement(AddItemPageLocators.QUANTITY_INPUT.getBy());
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        return this;
    }

    public AddItemPage selectRandomDimension() {
        Select dimensionSelect = new Select(driver.findElement(AddItemPageLocators.DIMENSION_SELECT.getBy()));
        dimensionSelect.selectByIndex(randomGenerator.nextInt(2) + 1);
        return this;
    }

    public NewOrderPage clickDoneButton() {
        click(AddItemPageLocators.DONE_BUTTON);
        return new NewOrderPage(driver);
    }

    public AddItemPage fillSearchInput(String searchTerm) {
        WebElement searchInput = driver.findElement(AddItemPageLocators.SEARCH_INPUT.getBy());
        searchInput.clear();
        searchInput.sendKeys(searchTerm);
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

    public AddItemPage selectSearchMethod(SearchMethods searchMethod) {
        Select searchMethodSelect = new Select(driver.findElement(AddItemPageLocators.SEARCH_METHOD_SELECT.getBy()));

        switch (searchMethod) {
            case SEARCH_BY_NAME:
                searchMethodSelect.selectByIndex(0);
                break;
            case SEARCH_BY_DESCRIPTION:
                searchMethodSelect.selectByIndex(1);
                break;
        }

        return this;
    }

    public boolean isItemsListSorted(List<String[]> itemsList, List<Product> productList) {
        for (int i = 0; i < itemsList.size(); i++) {
            // used trim() and replace() cause oms trims spaces in text
            if (!itemsList.get(i)[0].equals(productList.get(i).getProductName().trim().replace("  ", " "))
                    || !itemsList.get(i)[1].equals(productList.get(i).getProductDescription()
                    .trim().replace("  ", " "))) {
                return false;
            }
        }
        return true;
    }

    public List<Product> sortProductList(List<Product> productsList, SortFields sortFields, SortType sortType) {

        switch (sortFields) {
            case SORT_BY_NAME:
                Collections.sort(productsList, (p1, p2) -> p1.getProductName()
                        .compareTo(p2.getProductName()));
                break;
            case SORT_BY_DESCRIPTION:
                Collections.sort(productsList, (p1, p2) -> p1.getProductDescription()
                        .compareTo(p2.getProductDescription()));
                break;
        }

        if (SortType.DESC.equals(sortType)) {
            Collections.reverse(productsList);
        }

        return productsList;
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

    public enum SearchMethods {
        SEARCH_BY_NAME,
        SEARCH_BY_DESCRIPTION
    }

    public enum SortFields {
        SORT_BY_NAME,
        SORT_BY_DESCRIPTION
    }

    public enum SortType {
        ASC,
        DESC
    }

}
