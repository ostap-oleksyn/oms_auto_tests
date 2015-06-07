package com.softserveinc.edu.ita.page_object;


import org.openqa.selenium.WebDriver;

import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import org.openqa.selenium.WebElement;


public class UserInfoPage extends LogOutBase {

    public UserInfoPage(WebDriver driver) {
        super(driver);
    }


    public String getUserRoleLabel() {
        return driver.findElement(UserInfoPageLocators.USER_ROLE_LABEL).getText();
    }

    public WebElement getItemManagementTab() {
        return driver.findElement(UserInfoPageLocators.USER_ITEM_MANAGEMENT_TAB);
    }

    public ItemManagementPage clickItemManagementTab() {
        driver.findElement(UserInfoPageLocators.USER_ITEM_MANAGEMENT_TAB).click();
        return new ItemManagementPage(driver);
    }
}
