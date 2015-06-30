package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.locators.EditUserPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;


public class EditUserPage extends LogOutBase {
    public EditUserPage(WebDriver driver) {
        super(driver);
    }

    public String getEditUserLogin() {
        return getElementAttribute(EditUserPageLocators.LOGIN_LABEL, "value");
    }

    public EditUserPage fillFirstNameField(String newFirstName) throws IOException {
        sendKeys(EditUserPageLocators.FIRST_NAME_INPUT, newFirstName);
        return this;
    }

    public EditUserPage fillLastNameField(String newLastName) {
        sendKeys(EditUserPageLocators.LAST_NAME_INPUT, newLastName);
        return this;
    }

    public EditUserPage fillPasswordFields(String password) {
        sendKeys(EditUserPageLocators.PASSWORD_INPUT, password);
        sendKeys(EditUserPageLocators.CONFIRM_PASSWORD_INPUT, password);
        return this;
    }

        public EditUserPage selectRegion(int regionId) {
            Select regionSelect = new Select(driver.findElement(EditUserPageLocators.REGION_SELECT.getBy()));
            regionSelect.selectByIndex(regionId);
        return this;
    }

    public void selectRole(Roles customer) {
        WebElement customerRoleButton = driver.findElement(EditUserPageLocators.ROLE_CUSTOMER_SELECT.getBy());
        customerRoleButton.click();
    }

    public AdministrationPage clickCreateButton() {
        click(EditUserPageLocators.CREATE_BUTTON);
        return new AdministrationPage(driver);
    }
}
