package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.interfaces.ILogOutAble;
import com.softserveinc.edu.ita.locators.CommonLocators;
import org.openqa.selenium.WebDriver;

public class LogOutBase extends PageObjectBase implements ILogOutAble {

    public LogOutBase(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogOutButton() {
        click(CommonLocators.LOG_OUT_BUTTON.setByWithoutParameter());
        return new HomePage(driver);
    }
}



