package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum HomePageLocators implements ILocator {
    LOGIN_USER_INPUT(
            "Login input field",
            By.xpath(".//*[@id='edit']//input[@name = 'j_username']")),
    LOGIN_PASSWORD_INPUT(
            "Password input field",
            By.xpath(".//*[@id='edit']//input[@name = 'j_password']")),
    LOGIN_SUBMIT_BUTTON(
            "Login submit button",
            By.xpath(".//*[@id='edit']//input[@name = 'submit']")),
    LOGIN_ERROR_MESSAGE(
            "Login error message",
            By.xpath(".//*[@id='edit']/fieldset/font"));

    HomePageLocators(String name, By locator) {
        this.name = name;
        this.locator = locator;
    }

    private String name;
    private By locator;

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public By getBy() {
        return this.locator;
    }
}
