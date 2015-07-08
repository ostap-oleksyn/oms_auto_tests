package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.enums.ordering_page.ItemsOrderStatus;
import com.softserveinc.edu.ita.enums.ordering_page.ShownElementsNumber;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import static com.softserveinc.edu.ita.locators.EditOrderPageLocators.*;

/**
 * PageObject class that represents edit order page.
 */
public class EditOrderPage extends LogOutBase {

    public EditOrderPage(final WebDriver driver) {
        super(driver);
    }

    public EditOrderPage setNumberOfElements(final ShownElementsNumber number) {
        final Select select = new Select(driver.findElement(SHOW_NUMBER_OF_ELEMENTS_LINK.getBy()));
        select.selectByVisibleText(number.getName());
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected  - <b>'%s'</b>", SHOW_NUMBER_OF_ELEMENTS_LINK.getName()));
        return this;
    }

    public EditOrderPage setItemOrderStatus(final ItemsOrderStatus status) {
        final Select select = new Select(driver.findElement(ITEM_ORDER_STATUS.getBy()));
        select.selectByValue(String.valueOf(status));
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected  - <b>'%s'</b>", status.getStatusName()));
        return this;
    }

    public EditOrderPage clickItemNextPageButton() {
        click(ITEM_NEXT_PAGE_BUTTON);
        return this;
    }

    public EditOrderPage clickItemCancelButton() {
        click(ITEM_CANCEL_BUTTON);
        return this;
    }

    public EditOrderPage clickItemSaveButton() {
        click(ITEM_SAVE_BUTTON);
        return this;
    }

    public EditOrderPage clickItemErrorShowButton() {
        click(ITEM_ERROR_SHOW_BUTTON);
        return this;
    }

    public EditOrderPage clickItemGoToHomeButton() {
        click(ITEM_GO_TO_HOME_BUTTON);
        return this;
    }
}
