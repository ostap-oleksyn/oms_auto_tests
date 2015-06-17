package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum EditUserPageLocators implements ILocator {

    CREATE_BUTTON(
            "Create button",
            By.xpath(".//input[@type='submit'][@value='Create']")),

    EDIT_PAGE_MESSAGE(
            "Message of edit page",
            By.xpath(".//*[@id='edit']/h3")),

    FIRST_NAME_INPUT(
            "First name input",
            By.xpath(".//*[@id='firstName']")),

    PASSWORD_INPUT(
            "Password input",
            By.xpath(".//*[@id='password']")),
    CONFIRM_PASSWORD_INPUT(
            "Confirm password input",
            By.xpath(".//*[@id='confirmPassword']"));

    public static final String ROLE_SELECT = ".//label[.='%s']/preceding::input[1]";

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
        return null;
    }

    @Override
    public By getBy() {
        return null;
    }
}
