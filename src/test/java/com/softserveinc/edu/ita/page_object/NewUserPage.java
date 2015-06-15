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

    public AdministrationPage clickCreateButton() {
        driver.findElement(NewUserPageLocators.CREATE_BUTTON).click();
        return new AdministrationPage(driver);
    }

    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void closeAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void fillNamesWithDigits() {
        List<By> nameInputs = new ArrayList<By>() {{
            add(NewUserPageLocators.LOGIN_NAME_INPUT);
            add(NewUserPageLocators.FIRST_NAME_INPUT);
            add(NewUserPageLocators.LAST_NAME_INPUT);
        }};

        WebElement nameInput;
        for (int i = 0; i < 3; i++) {
            nameInput = driver.findElement(nameInputs.get(i));
            nameInput.clear();
            // String.valueOf(HOME) added for prevent selenium/firefox bug
            nameInput.sendKeys(generateString("digits", 1, 13));
        }
    }

    public void fillNamesWithLongStrings() {
        List<By> nameInputs = new ArrayList<By>() {{
            add(NewUserPageLocators.LOGIN_NAME_INPUT);
            add(NewUserPageLocators.FIRST_NAME_INPUT);
            add(NewUserPageLocators.LAST_NAME_INPUT);
        }};

        WebElement nameInput;
        for (int i = 0; i < 3; i++) {
            nameInput = driver.findElement(nameInputs.get(i));
            nameInput.clear();
            // String.valueOf(HOME) added for prevent selenium/firefox bug
            nameInput.sendKeys(generateString("name_symbols", 14, 20));
        }
    }

    public void fillPasswordWithShortString() {
        WebElement passwordInput = driver.findElement(NewUserPageLocators.PASSWORD_INPUT);
        passwordInput.clear();
        // String.valueOf(HOME) added for prevent selenium/firefox bug
        passwordInput.sendKeys(generateString("password_symbols", 1, 3));
    }

    public void fillPasswordWithLongString() {
        WebElement passwordInput = driver.findElement(NewUserPageLocators.PASSWORD_INPUT);
        passwordInput.clear();
        // String.valueOf(HOME) added for prevent selenium/firefox bug
        passwordInput.sendKeys(generateString("password_symbols", 14, 20));
    }

    public void fillPasswordWithNotEqual() {
        WebElement confirmPasswordInput = driver.findElement(NewUserPageLocators.CONFIRM_PASSWORD_INPUT);
        confirmPasswordInput.clear();
        // String.valueOf(HOME) added for prevent selenium/firefox bug
        confirmPasswordInput.sendKeys(generateString("password_symbols", 1, 13));
    }

    public void fillEmail() {
        WebElement emailInput = driver.findElement(NewUserPageLocators.EMAIL_INPUT);
        emailInput.clear();
        // String.valueOf(HOME) added for prevent selenium/firefox bug
        emailInput.sendKeys(generateString("digits", 1, 10));
    }

    public void fillLogin(String login) {
        // + HOME added for prevent selenium/firefox bug
        WebElement loginName = driver.findElement(NewUserPageLocators.LOGIN_NAME_INPUT);
        loginName.clear();
        loginName.sendKeys(login);
    }

    public void fillUserData(User newUser) {

        WebElement loginInput = driver.findElement(NewUserPageLocators.LOGIN_NAME_INPUT);
        loginInput.clear();
        loginInput.sendKeys(newUser.getLogin());

        WebElement firstNameInput = driver.findElement(NewUserPageLocators.FIRST_NAME_INPUT);
        firstNameInput.clear();
        firstNameInput.sendKeys(newUser.getFirstName());

        WebElement lastNameInput = driver.findElement(NewUserPageLocators.LAST_NAME_INPUT);
        lastNameInput.clear();
        lastNameInput.sendKeys(newUser.getLastName());

        WebElement passwordInput = driver.findElement(NewUserPageLocators.PASSWORD_INPUT);
        passwordInput.clear();
        passwordInput.sendKeys(newUser.getPassword());

        WebElement confirmPasswordInput = driver.findElement(NewUserPageLocators.CONFIRM_PASSWORD_INPUT);
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(newUser.getPassword());

        WebElement emailInput = driver.findElement(NewUserPageLocators.EMAIL_INPUT);
        emailInput.clear();
        emailInput.sendKeys(newUser.getEmail());

        Select regionSelect = new Select(driver.findElement(NewUserPageLocators.REGION_SELECT));
        regionSelect.selectByVisibleText(newUser.getRegionName());

        WebElement roleButton = driver.findElement(By.xpath(
                String.format(NewUserPageLocators.ROLE_SELECT, newUser.getRoleName())));
        roleButton.click();
    }
}
