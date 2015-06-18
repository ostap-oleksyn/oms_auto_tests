package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum NewUserPageLocators implements ILocator {

    LOGIN_NAME_INPUT(
            "Login name input",
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

    EMAIL_INPUT(
            "Email input",
            By.xpath(".//*[@id='email']")),

    REGION_SELECT(
            "Region select",
            By.xpath(".//*[@id='regionID']")),

    CREATE_BUTTON(
            "Create button",
            By.xpath(".//input[@type='submit'][@value='Create']")),

    LOGIN_NAME_ERROR_LABEL(
            "Login name error label",
            By.xpath(".//*[@id='nameError']")),

    FIRST_NAME_ERROR_LABEL(
            "First name error label",
            By.xpath(".//*[@id='firstNameError']")),

    LAST_NAME_ERROR_LABEL(
            "Last name error label",
            By.xpath(".//*[@id='lastNameError']")),

    PASSWORD_ERROR_LABEL(
            "Password error label",
            By.xpath(".//*[@id='passwordError']")),

    CONFIRM_PASSWORD_ERROR_LABEL(
            "Confirm password error label",
            By.xpath(".//*[@id='confirmError']")),

    EMAIL_ERROR_LABEL(
            "Email error label",
            By.xpath(".//*[@id='emailError']"));


    public static final String ROLE_SELECT = ".//label[.='%s']/preceding::input[1]";

    private String name;
    private By locator;

    NewUserPageLocators(String name, By locator) {
        this.name = name;
        this.locator = locator;
    }

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
