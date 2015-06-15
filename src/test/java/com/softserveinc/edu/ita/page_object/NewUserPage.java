package com.softserveinc.edu.ita.page_object;


import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.edu.ita.utils.StringsGenerator.generateString;
import static org.openqa.selenium.Keys.HOME;

/**
 * PageObject Class presents page for new User creating
 */
public class NewUserPage extends LogOutBase {

    public NewUserPage(WebDriver driver) {
        super(driver);
    }

    public AdministrationPage clickCreateButtonForValidData() {
        driver.findElement(NewUserPageLocators.CREATE_BUTTON).click();
        return new AdministrationPage(driver);
    }

    public void clickCreateButtonForNotValidData() {
        driver.findElement(NewUserPageLocators.CREATE_BUTTON).click();
    }

    public void fillUserDataInput(By inputLocator, String inputValue) {
        WebElement input = driver.findElement(inputLocator);
        input.sendKeys(inputValue);
    }

    public void closeAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void fillAllUserData(User newUser) {

        WebElement loginInput = driver.findElement(NewUserPageLocators.LOGIN_NAME_INPUT);
        loginInput.sendKeys(newUser.getLogin());

        WebElement firstNameInput = driver.findElement(NewUserPageLocators.FIRST_NAME_INPUT);
        firstNameInput.sendKeys(newUser.getFirstName());

        WebElement lastNameInput = driver.findElement(NewUserPageLocators.LAST_NAME_INPUT);
        lastNameInput.sendKeys(newUser.getLastName());

        WebElement passwordInput = driver.findElement(NewUserPageLocators.PASSWORD_INPUT);
        passwordInput.sendKeys(newUser.getPassword());

        WebElement confirmPasswordInput = driver.findElement(NewUserPageLocators.CONFIRM_PASSWORD_INPUT);
        confirmPasswordInput.sendKeys(newUser.getPassword());

        WebElement emailInput = driver.findElement(NewUserPageLocators.EMAIL_INPUT);
        emailInput.sendKeys(newUser.getEmail());

        Select regionSelect = new Select(driver.findElement(NewUserPageLocators.REGION_SELECT));
        regionSelect.selectByVisibleText(newUser.getRegionName());

        WebElement roleButton = driver.findElement(By.xpath(
                String.format(NewUserPageLocators.ROLE_SELECT, newUser.getRoleName())));
        roleButton.click();
    }

    public boolean isErrorDisplayed(By messageLocator) {
        WebElement errorMessageLabel = driver.findElement(messageLocator);
        return errorMessageLabel.isDisplayed();
    }
}
