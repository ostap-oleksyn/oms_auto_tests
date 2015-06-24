package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.locators.EditUserPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class EditUserPage extends LogOutBase {
    public EditUserPage(WebDriver driver) {
        super(driver);
    }

    public String getEditUserLogin() {
        WebElement editUserLogin = driver.findElement(EditUserPageLocators.LOGIN_INPUT.getBy());

        return editUserLogin.getText();
    }

    public void changeFirstName (String newName) throws IOException {
        driver.findElement(EditUserPageLocators.FIRST_NAME_INPUT.getBy()).sendKeys(newName);
    }

    public AdministrationPage clickCreateButton() {
        click(EditUserPageLocators.CREATE_BUTTON);
        return new AdministrationPage(driver);
    }
}
