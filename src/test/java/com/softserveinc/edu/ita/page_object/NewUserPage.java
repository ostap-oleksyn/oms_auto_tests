package com.softserveinc.edu.ita.page_object;


import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.interfaces.LogOutAble;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import org.openqa.selenium.*;
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

    public AdministrationPage clickCreateButton() {
        driver.findElement(NewUserPageLocators.CREATE_BUTTON).click();
        return new AdministrationPage(driver);
    }

    public void fillUserDataInput(By inputLocator, String inputValue) {
        WebElement input = driver.findElement(inputLocator);
        input.sendKeys(inputValue);
    }

    public void fillLoginNameField(String loginName) {
        WebElement input = driver.findElement(NewUserPageLocators.LOGIN_NAME_INPUT);
        input.sendKeys(loginName);
    }

    public void fillFirstNameField(String firstName) {
        WebElement input = driver.findElement(NewUserPageLocators.FIRST_NAME_INPUT);
        input.sendKeys(firstName);
    }

    public void fillLastNameField(String lastName) {
        WebElement input = driver.findElement(NewUserPageLocators.LAST_NAME_INPUT);
        input.sendKeys(lastName);
    }

    public void fillPasswordField(String password) {
        WebElement input = driver.findElement(NewUserPageLocators.PASSWORD_INPUT);
        input.sendKeys(password);
    }

    public void fillConfirmPasswordField(String password) {
        WebElement input = driver.findElement(NewUserPageLocators.CONFIRM_PASSWORD_INPUT);
        input.sendKeys(password + "_");
    }

    public void fillEmailField(String email) {
        WebElement input = driver.findElement(NewUserPageLocators.EMAIL_INPUT);
        input.sendKeys(email);
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void fillAllFields(User newUser) {

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
}
