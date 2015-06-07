package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class PageObjectBase {
    protected WebDriver driver;

    public PageObjectBase(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getActiveTab() {
        return driver.findElement(UserInfoPageLocators.ACTIVE_TAB);
    }

    public WebElement getUserInfoTab() {
        return driver.findElement(UserInfoPageLocators.USER_INFO_TAB);
    }

    public UserInfoPage clickUserInfoTab() {
        driver.findElement(UserInfoPageLocators.USER_INFO_TAB).click();
        return new UserInfoPage(driver);
    }
}

