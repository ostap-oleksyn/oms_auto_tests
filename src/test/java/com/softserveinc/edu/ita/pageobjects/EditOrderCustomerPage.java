package com.softserveinc.edu.ita.pageobjects;


import com.softserveinc.edu.ita.locators.EditOrdeCustomerPageLocators;
import com.softserveinc.edu.ita.locators.interfaces.ILocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class EditOrderCustomerPage extends LogOutBase {

    public EditOrderCustomerPage (WebDriver driver) {
        super(driver);
    }

    public EditOrderCustomerPage clickSaveButton() {
        click(EditOrdeCustomerPageLocators.SAVE_ORDER_BUTTON);
        return this;
    }

    public EditOrderCustomerPage fillOrderNumber(String num) {
        sendKeys(EditOrdeCustomerPageLocators.ORDER_NUMBER_FIELD,num);
        return this;
    }

    public EditOrderCustomerPage changeAssigneeUser() {
        click(EditOrdeCustomerPageLocators.ASSIGNEE_FIRST_USER);
        return this;
    }

    public EditOrderCustomerPage fillAssigneeUser(String assegn) {
        final Select assagneeSelect = new Select(driver.findElement(EditOrdeCustomerPageLocators.ASSIGNEE_LIST_USERS.getBy()));
        assagneeSelect.selectByVisibleText(assegn);
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected assagnee - <b>'%s'</b>", assegn));
        return this;
    }

    public String getValue(ILocator locator) {
        return getElementAttribute(locator, "value");
    }

}