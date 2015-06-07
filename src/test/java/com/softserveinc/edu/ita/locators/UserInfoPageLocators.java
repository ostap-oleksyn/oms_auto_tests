package com.softserveinc.edu.ita.locators;


import org.openqa.selenium.By;

public final class UserInfoPageLocators {

    private UserInfoPageLocators() {
    }

    public static final By USER_ROLE_LABEL = By.xpath("(.//*[@id='content']//td)[8]");
    public static final By USER_INFO_ELEMENT = By.xpath(".//*[@id='content']/div/fieldset/legend");

}
