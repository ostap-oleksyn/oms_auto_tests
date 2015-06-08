package com.softserveinc.edu.ita.locators;

import org.openqa.selenium.By;

public final class AdministrationPageLocators {

    private AdministrationPageLocators() {
    }

    public static final By ADMINISTRATION_PAGE_TAB = By.xpath(".//*[@id='nav']/li[1]/a");
    public static final By ADMINISTRATION_PAGE_TEXT = By.xpath(".//*[@id='list']/h2");
    public static final By CREATE_NEW_USER_BUTTON = By.xpath(".//*[@id='list']/a");
}
