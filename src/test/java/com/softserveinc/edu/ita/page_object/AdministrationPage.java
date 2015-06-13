package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import org.openqa.selenium.WebDriver;

public class AdministrationPage extends LogOutBase {

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    public NewUserPage clickCreateUserLink() {
        driver.findElement(AdministrationPageLocators.CREATE_NEW_USER_LINK).click();
        return new NewUserPage(driver);
    }
}
