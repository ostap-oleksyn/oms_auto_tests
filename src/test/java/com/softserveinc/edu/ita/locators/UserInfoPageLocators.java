package com.softserveinc.edu.ita.locators;


import org.openqa.selenium.By;

public final class UserInfoPageLocators {

    private UserInfoPageLocators() {
    }

    public static final By USER_ROLE_LABEL = By.xpath("(.//*[@id='content']//td)[8]");
    public static final By USER_INFO_TAB = By.xpath(".//*[@id='nav']//a[contains(text(), 'User Info')]");
    public static final By USER_ITEM_MANAGEMENT_TAB = By.xpath(".//*[@id='nav']//a[contains(text(), 'Item Management')]");

}
