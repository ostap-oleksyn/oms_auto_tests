package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum EditUserPageLocators implements ILocator {

    LOGIN_INPUT(
            "Login input",
            By.xpath(".//*[@id='login']")),

    FIRST_NAME_INPUT(
            "First name input",
            By.xpath(".//*[@id='firstName']")),

    LAST_NAME_INPUT(
            "Last name input",
            By.xpath(".//*[@id='lastName']")),

    PASSWORD_INPUT(
            "Password input",
            By.xpath(".//*[@id='password']")),

    CONFIRM_PASSWORD_INPUT(
            "Confirm password input",
            By.xpath(".//*[@id='confirmPassword']")),

    REGION_SELECT(
            "Region select",
            By.xpath(".//*[@id='regionID']")),

    ROLE_CUSTOMER_SELECT(
            "Role customer select",
            By.xpath(".//*[@id='roleID2']")),

    CREATE_BUTTON(
            "Create button",
            By.xpath(".//input[@type='submit'][@value='Create']"));

    EditUserPageLocators(String name, By locator) {
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