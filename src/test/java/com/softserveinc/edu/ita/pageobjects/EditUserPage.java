package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.enums.Regions;
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
        WebElement editUserLogin = driver.findElement(EditUserPageLocators.LOGIN_INPUT.getBy());

        return editUserLogin.getText();
    }

    public void changeFirstName (String newFirstName) throws IOException {
        WebElement firstNameField = driver.findElement(EditUserPageLocators.FIRST_NAME_INPUT.getBy());
        firstNameField.clear();
        firstNameField.sendKeys(newFirstName);
    }

    public void changeLastName (String newLastName) throws IOException {
        WebElement firstNameField = driver.findElement(EditUserPageLocators.LAST_NAME_INPUT.getBy());
        firstNameField.clear();
        firstNameField.sendKeys(newLastName);
    }

    public void fillPasswordFields (String password) throws IOException {
        sendKeys(EditUserPageLocators.PASSWORD_INPUT, password);
        sendKeys(EditUserPageLocators.CONFIRM_PASSWORD_INPUT, password);
    }

    public void selectRegion(Regions regionName) {
        Select regionSelect = new Select(driver.findElement(EditUserPageLocators.REGION_SELECT.getBy()));
        regionSelect.selectByVisibleText(regionName.toString());
    }

    public void changeRole(Roles customer) {
        WebElement customerRoleButton = driver.findElement(EditUserPageLocators.ROLE_CUSTOMER_SELECT.getBy());
        customerRoleButton.click();
    }

    public AdministrationPage clickCreateButton() {
        click(EditUserPageLocators.CREATE_BUTTON);
        return new AdministrationPage(driver);
    }
}
