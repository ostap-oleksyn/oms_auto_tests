package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.locators.HomePageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends PageObjectBase {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public UserInfoPage logIn(String login, String password) {
        driver.findElement(HomePageLocators.LOGIN_USER_INPUT)
                .sendKeys(login);
        driver.findElement(HomePageLocators.LOGIN_PASSWORD_INPUT)
                .sendKeys(password);
        driver.findElement(HomePageLocators.LOGIN_SUBMIT_BUTTON)
                .click();
        return new UserInfoPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(HomePageLocators.LOGIN_ERROR_MESSAGE).getText();
    }

    public HomePage clickSubmitButton() {
        driver.findElement(HomePageLocators.LOGIN_SUBMIT_BUTTON).click();
        return new HomePage(driver);
    }

    public WebElement getLoginInputField(){
        return driver.findElement(HomePageLocators.LOGIN_USER_INPUT);
    }


}
