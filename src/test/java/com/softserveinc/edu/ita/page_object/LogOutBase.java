package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.interfaces.LogOutAble;
import org.openqa.selenium.WebDriver;

import static com.softserveinc.edu.ita.locators.HomePage.*;

public class LogOutBase extends PageObjectBase implements LogOutAble {

    public LogOutBase(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogOutButton() {
        driver.findElement(LOG_OUT_BUTTON).click();
        return new HomePage(driver);
    }
}



