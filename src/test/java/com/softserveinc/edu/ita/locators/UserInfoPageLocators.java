package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum UserInfoPageLocators implements ILocator {

    USER_INFO_LABEL(
            "Title User Info Table",
            "xpath",
            "//fieldset/legend"),
    FIRST_NAME_TITLE_LABEL(
            "Title first name label",
            "xpath",
            "//fieldset//tr[1]/td[1]"),
    FIRST_NAME_LABEL(
            "User first name label",
            "xpath",
            "//fieldset//tr[1]/td[2]"),
    LAST_NAME_LABEL(
            "User last name label",
            "xpath",
            "//fieldset//tr[2]/td[2]"),
    CUSTOMER_TYPE_LABEL(
            "User customer type label",
            "xpath",
            "//fieldset//tr[3]/td[2]"),
    USER_ROLE_LABEL(
            "User role label",
            "xpath",
            "//fieldset//tr[4]/td[2]");

    private String name;
    private String locatorsType;
    private String rowLocator;
    private String modifiedLocator;
    private By byLocator;

    UserInfoPageLocators(String name, String locatorsType, String rowLocator) {
        this.name = name;
        this.locatorsType = locatorsType;
        this.rowLocator = rowLocator;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public UserInfoPageLocators modify(String parameter) {
        this.name = parameter;
        this.modifiedLocator = String.format(this.rowLocator, parameter);
        return this;
    }

    @Override
    public By getBy() {
        String locator;
        if (this.modifiedLocator == null) {
            locator = this.rowLocator;
        } else {
            locator = this.modifiedLocator;
        }
        switch (this.locatorsType) {
            case ("className"):
                this.byLocator = By.className(locator);
                break;
            case ("cssSelector"):
                this.byLocator = By.cssSelector(locator);
                break;
            case ("id"):
                this.byLocator = By.id(locator);
                break;
            case ("name"):
                this.byLocator = By.name(locator);
                break;
            case ("tagName"):
                this.byLocator = By.tagName(locator);
                break;
            case ("xpath"):
                this.byLocator = By.xpath(locator);
                break;
            default:
                System.out.println("Locator's type is incorrect.");
                break;
        }
        return this.byLocator;
    }
}
