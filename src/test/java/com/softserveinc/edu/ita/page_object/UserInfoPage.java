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

}
