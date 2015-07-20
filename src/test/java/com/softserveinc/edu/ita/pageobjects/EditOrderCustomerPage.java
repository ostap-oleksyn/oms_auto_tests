package com.softserveinc.edu.ita.pageobjects;


import com.softserveinc.edu.ita.locators.EditOrderCustomerPageLocators;
import com.softserveinc.edu.ita.locators.interfaces.ILocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class EditOrderCustomerPage extends LogOutBase {

    public EditOrderCustomerPage (final WebDriver driver) {
        super(driver);
    }

    public EditOrderCustomerPage clickSaveButton() {
        click(EditOrderCustomerPageLocators.SAVE_ORDER_BUTTON);
        return this;
    }

    public EditOrderCustomerPage fillOrderNumber(final String number) {
        sendKeys(EditOrderCustomerPageLocators.ORDER_NUMBER_FIELD,number);
        return this;
    }

    public EditOrderCustomerPage changeAssigneeUser() {
        click(EditOrderCustomerPageLocators.ASSIGNEE_FIRST_USER);
        return this;
    }

    public EditOrderCustomerPage fillAssigneeUser(final String assignee) {
        final Select assigneeSelect = new Select(driver.findElement(EditOrderCustomerPageLocators.ASSIGNEE_LIST_USERS.getBy()));
        assigneeSelect.selectByVisibleText(assignee);
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Selected assagnee - <b>'%s'</b>", assignee));
        return this;
    }

    public String getValue(final ILocator locator) {
        return getElementAttribute(locator, "value");
    }

}