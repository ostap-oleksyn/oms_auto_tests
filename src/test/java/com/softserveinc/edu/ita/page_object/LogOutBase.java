package com.softserveinc.edu.ita.page_object;

import org.openqa.selenium.WebDriver;

import static com.softserveinc.edu.ita.locators.Locators.*;

public class LogOutBase extends PageObjectPage {

    public LogOutBase(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogOutButton() {
        driver.findElement(LOG_OUT_BUTTON).click();
        return new HomePage(driver);
    }
}

