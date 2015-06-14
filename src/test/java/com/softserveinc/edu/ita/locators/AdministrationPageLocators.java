package com.softserveinc.edu.ita.locators;


import org.openqa.selenium.By;
//TODO remove Locators from name
public final class AdministrationPageLocators {

    private AdministrationPageLocators() {
    }

    public static final By CREATE_NEW_USER_LINK = By.xpath(".//*[@id='list']/a[contains(text(), 'Create New User')]");
    public static final By USERS_TABLE = By.xpath(".//*[@id='table']");
    public static final By LAST_BUTTON = By.xpath(".//*[@id='last']");
    public static final String DELETE_LINK = ".//*[@id='table']/tbody/tr[%s]/td[7]/a";
}
