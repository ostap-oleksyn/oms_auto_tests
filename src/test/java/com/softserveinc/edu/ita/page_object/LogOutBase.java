package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.interfaces.LogOutAble;
<<<<<<< HEAD
import com.softserveinc.edu.ita.locators.HomePageLocators;
import org.openqa.selenium.WebDriver;

=======
import org.openqa.selenium.WebDriver;

import static com.softserveinc.edu.ita.locators.HomePage.*;

>>>>>>> f6ed398dc18e4c1e4a0f2eeef2d78ef539d6415b
public class LogOutBase extends PageObjectBase implements LogOutAble {

    public LogOutBase(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogOutButton() {
        driver.findElement(HomePageLocators.LOG_OUT_BUTTON).click();
        return new HomePage(driver);
    }
}



