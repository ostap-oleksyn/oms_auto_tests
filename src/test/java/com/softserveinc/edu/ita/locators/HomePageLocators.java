package com.softserveinc.edu.ita.locators;

import org.openqa.selenium.By;

public final class HomePageLocators {

    private HomePageLocators() {
    }

    public static final By LOGIN_USER_INPUT = By.xpath(".//*[@id='edit']//input[@name = 'j_username']");
    public static final By LOGIN_PASSWORD_INPUT = By.xpath(".//*[@id='edit']//input[@name = 'j_password']");
    public static final By LOGIN_SUBMIT_BUTTON = By.xpath(".//*[@id='edit']//input[@name = 'submit']");
    public static final By LOG_OUT_BUTTON = By.xpath("//img[@alt='logout']");
    public static final By LOGIN_ERROR_MESSAGE = By.xpath(".//*[@id='edit']/fieldset/font");

}