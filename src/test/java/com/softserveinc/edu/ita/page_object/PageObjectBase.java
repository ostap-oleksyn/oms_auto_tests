package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class PageObjectBase {
    protected WebDriver driver;

    public PageObjectBase(WebDriver driver) {
        this.driver = driver;
    }

    public String getElementText(By elementLocator) {
        return driver.findElement(elementLocator).getText();
    }

    public boolean isElementVisible(By elementLocator) {
        return driver.findElement(elementLocator).isDisplayed();
    }

    public UserInfoPage clickUserInfoTab() {
        driver.findElement(UserInfoPageLocators.USER_INFO_TAB).click();
        return new UserInfoPage(driver);
    }
}

