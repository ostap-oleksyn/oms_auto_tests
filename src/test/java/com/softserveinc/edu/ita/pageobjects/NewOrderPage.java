package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.locators.AddItemPageLocators;
import com.softserveinc.edu.ita.locators.NewOrderPageLocators;
import com.softserveinc.edu.ita.utils.RandomUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * PageObject Class presents page for new Order creating
 */
public class NewOrderPage extends LogOutBase {

    //private Random randomGenerator = new Random();

    public NewOrderPage(WebDriver driver) {
        super(driver);
    }

    public AddItemPage clickAddItemButton() {
        click(NewOrderPageLocators.ADD_ITEM_BUTTON);
        return new AddItemPage(driver);
    }

    public NewOrderPage selectRandomAssignee() {
        final Select assigneeSelect = new Select(driver.findElement(NewOrderPageLocators.ASSIGNEE_SELECT.getBy()));
        final int randomAssigneeIndex = RandomUtil.getRandomInteger(1, assigneeSelect.getOptions().size());
        assigneeSelect.selectByIndex(randomAssigneeIndex);
        return this;
    }

    public NewOrderPage fillRandomCreditCardNumber() {
        final String creditCardNumber = "4" + RandomUtil.getRandomString("Digits", 14, 14);
        sendKeys(NewOrderPageLocators.CREDIT_CARD_NUMBER_INPUT, creditCardNumber);
        return this;
    }

    public NewOrderPage fillCVV2NumberField(String CVV2) {
        sendKeys(NewOrderPageLocators.CVV2_INPUT, CVV2);
        return this;
    }

    // fills Preferable Delivery Date field with date later of current for 0-30 days
    public NewOrderPage fillRandomPreferableDeliveryDate() {
        final int MAX_DAY_OFFSET = 30;
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, RandomUtil.getRandomInteger(0, MAX_DAY_OFFSET));
        final Date deliveryDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        sendKeys(NewOrderPageLocators.PREFERABLE_DELIVERY_DATE_INPUT, dateFormat.format(deliveryDate));
        return this;
    }

    public void clickSaveButton() {
        click(NewOrderPageLocators.SAVE_BUTTON);
    }

    public int getOrderNumber() {
        return Integer.valueOf(getElementAttribute(NewOrderPageLocators.ORDER_NUMBER_INPUT, "value"));
    }

    public boolean isItemSelectionSectionPresent() {
        return (isElementDisplayed(NewOrderPageLocators.ADD_ITEM_BUTTON)) &&
                (isElementDisplayed(NewOrderPageLocators.SHOW_LINES_COUNT_SELECT)) &&
                (isElementDisplayed(NewOrderPageLocators.ITEMS_LIST)) &&
                (isElementDisplayed(NewOrderPageLocators.NAVIGATION_FIRST_BUTTON)) &&
                (isElementDisplayed(NewOrderPageLocators.NAVIGATION_BACKWARD_BUTTON)) &&
                (isElementDisplayed(NewOrderPageLocators.NAVIGATION_FORWARD_BUTTON)) &&
                (isElementDisplayed(NewOrderPageLocators.NAVIGATION_LAST_BUTTON));
    }

    public boolean isFirstButtonEnabled() {
        return isElementEnabled(NewOrderPageLocators.NAVIGATION_FIRST_BUTTON);
    }

    public boolean isBackwardButtonEnabled() {
        return isElementEnabled(NewOrderPageLocators.NAVIGATION_BACKWARD_BUTTON);
    }

    public boolean isForwardButtonEnabled() {
        return isElementEnabled(NewOrderPageLocators.NAVIGATION_FORWARD_BUTTON);
    }

    public boolean isLastButtonEnabled() {
        return isElementEnabled(NewOrderPageLocators.NAVIGATION_LAST_BUTTON);
    }

    public void clickForwardButton() {
        click(NewOrderPageLocators.NAVIGATION_FORWARD_BUTTON);
    }

    public void selectShowLinesCount(int option) {
        Select showLinesCountSelect = new Select(driver
                .findElement(NewOrderPageLocators.SHOW_LINES_COUNT_SELECT.getBy()));
        switch (option) {
            case 10:
                showLinesCountSelect.selectByIndex(0);
                break;
            case 25:
                showLinesCountSelect.selectByIndex(1);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public List<String[]> getItemsTable() {
        final List<WebElement> itemsRows = driver.findElements(NewOrderPageLocators.ITEMS_TABLE_ROWS.getBy());

        List<WebElement> rowsCells;
        List<String[]> itemsList = new ArrayList<>();

        for (WebElement itemRow : itemsRows) {
            rowsCells = itemRow.findElements(AddItemPageLocators.ITEMS_ROW_CELL.getBy());
            String[] item = {rowsCells.get(0).getText(), rowsCells.get(1).getText(),
                    rowsCells.get(2).getText(), rowsCells.get(3).getText(),
                    rowsCells.get(4).getText(), rowsCells.get(5).getText(),
                    rowsCells.get(6).getText()};
            itemsList.add(item);
        }
        return itemsList;
    }

    public double getTotalPrice() {
        return Double.valueOf(getElementText(NewOrderPageLocators.TOTAL_PRICE_CELL));
    }

    public AddItemPage clickEditLink() {
        click(NewOrderPageLocators.EDIT_ITEM_LINK);
        return new AddItemPage(driver);
    }
}
