package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum UserInfoPageLocators implements ILocator {

    USER_INFO_TITLE_TEXT (
            "Title User Info Table",
            By.xpath("//fieldset/legend")),
    FIRST_NAME_TITLE_TEXT (
            "Title first name label",
            By.xpath("//fieldset//tr[1]/td[1]")),
    FIRST_NAME_LABEL(
            "User first name label",
            By.xpath("//fieldset//tr[1]/td[2]")),
    LAST_NAME_LABEL(
            "User last name label",
            By.xpath("//fieldset//tr[2]/td[2]")),
    CUSTOMER_TYPE_LABEL(
            "User customer type label",
            By.xpath("//fieldset//tr[3]/td[2]")),
    USER_ROLE_LABEL(
            "User role label",
            By.xpath("//fieldset//tr[4]/td[2]"));



    UserInfoPageLocators(String name, By locator) {
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
