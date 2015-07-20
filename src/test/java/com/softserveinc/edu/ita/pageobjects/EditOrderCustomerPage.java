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

    public EditOrderCustomerPage fillOrderNumber(String number) {
        sendKeys(EditOrdeCustomerPageLocators.ORDER_NUMBER_FIELD,number);
        return this;
    }

    public EditOrderCustomerPage changeAssigneeUser() {
        click(EditOrdeCustomerPageLocators.ASSIGNEE_FIRST_USER);
        return this;
    }

    public EditOrderCustomerPage fillAssigneeUser(String assignee) {
        final Select assigneeSelect = new Select(driver.findElement(EditOrdeCustomerPageLocators.ASSIGNEE_LIST_USERS.getBy()));
        assigneeSelect.selectByVisibleText(assignee);
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected assagnee - <b>'%s'</b>", assignee));
        return this;
    }

    public String getValue(ILocator locator) {
        return getElementAttribute(locator, "value");
    }

}