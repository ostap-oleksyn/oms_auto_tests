package com.softserveinc.edu.ita.locators;


import org.openqa.selenium.By;

public final class UserInfoPageLocators {

    private UserInfoPageLocators() {
    }

    // UserInfoPage_Locators
    public static final By FIRST_NAME_LABEL = By.xpath("//fieldset//tr[1]/td[2]");
    public static final By LAST_NAME_LABEL = By.xpath("//fieldset//tr[2]/td[2]");
    public static final By CUSTOMER_TYPE_LABEL = By.xpath("//fieldset//tr[3]/td[2]");
    public static final By USER_ROLE_LABEL = By.xpath("//fieldset//tr[4]/td[2]");
}
