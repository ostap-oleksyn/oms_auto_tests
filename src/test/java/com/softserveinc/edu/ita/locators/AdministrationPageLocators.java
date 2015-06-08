package com.softserveinc.edu.ita.locators;


import org.openqa.selenium.By;

public final class AdministrationPageLocators {

    private AdministrationPageLocators() {
    }

    public static final By CREATE_NEW_USER_LINK = By.xpath(".//*[@id='list']/a[contains(text(), 'Create New User')]");
}
