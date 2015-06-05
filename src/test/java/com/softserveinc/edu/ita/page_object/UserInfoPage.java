package com.softserveinc.edu.ita.page_object;


import org.openqa.selenium.WebDriver;

import com.softserveinc.edu.ita.locators.UserInfoPageLocators;


public class UserInfoPage extends LogOutBase {

    public UserInfoPage(WebDriver driver) {
        super(driver);
    }


    public String getUserRoleLabel() {
        return driver.findElement(UserInfoPageLocators.USER_ROLE_LABEL).getText();
    }
    public String getFirstNameText() {
        return driver.findElement(UserInfoPageLocators.FIRST_NAME_TD).getText();
    }

    public String getLastNameText() {
        return driver.findElement(UserInfoPageLocators.LAST_NAME_TD).getText();
    }

    public String getCustomerTypeText() {
        return driver.findElement(UserInfoPageLocators.CUSTOMER_TYPE_TD).getText();
    }

    public String getUserRoleText() {
        return driver.findElement(UserInfoPageLocators.USER_ROLE_TD).getText();
    }
}
