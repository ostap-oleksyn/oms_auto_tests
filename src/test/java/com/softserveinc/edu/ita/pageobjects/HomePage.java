package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.locators.HomePageLocators;
import org.openqa.selenium.WebDriver;

/**
 * PageObject class that represents Home Page.
 */
public class HomePage extends PageObjectBase {

    public HomePage(final WebDriver driver) {
        super(driver);
    }

    public UserInfoPage logIn(final String login, final String password) {
        sendKeys(HomePageLocators.LOGIN_USER_INPUT, login);
        sendKeys(HomePageLocators.LOGIN_PASSWORD_INPUT, password);
        click(HomePageLocators.LOGIN_SUBMIT_BUTTON);
        return new UserInfoPage(driver);
    }

    public HomePage clickSubmitButton() {
        click(HomePageLocators.LOGIN_SUBMIT_BUTTON);
        return new HomePage(driver);
    }
}
