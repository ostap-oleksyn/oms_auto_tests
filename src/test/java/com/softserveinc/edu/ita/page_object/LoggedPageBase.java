package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.interfaces.LogOutAble;
import com.softserveinc.edu.ita.locators.HomePageLocators;
import com.softserveinc.edu.ita.locators.LogoutPageLocators;
import org.openqa.selenium.WebDriver;

public class LoggedPageBase extends PageObjectBase implements LogOutAble {

    public LoggedPageBase(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogOutButton() {
        driver.findElement(HomePageLocators.LOG_OUT_BUTTON).click();
        return new HomePage(driver);
    }

    public AdministrationPage clickAdministrationTab() {
        driver.findElement(LogoutPageLocators.ADMINISTRATION_TAB);
        return new AdministrationPage(driver);
    }
}



