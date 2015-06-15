package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.locators.EditUserPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class EditUserPage extends LogOutBase {

    public EditUserPage(WebDriver driver) {
        super(driver);
    }

    public AdministrationPage clickCreateButton() {
        driver.findElement(EditUserPageLocators.CREATE_BUTTON).click();
        return new AdministrationPage(driver);
    }

    public void changeFirstName (String newName) throws IOException {
        driver.findElement(EditUserPageLocators.FIRST_NAME_INPUT).sendKeys(newName);
    }

}
