package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

import static org.openqa.selenium.Keys.HOME;

/**
 * PageObject Class presents page for Users administration
 */
public class AdministrationPage extends LogOutBase {

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    public NewUserPage clickCreateUserLink() {
        driver.findElement(AdministrationPageLocators.CREATE_NEW_USER_LINK).click();
        return new NewUserPage(driver);
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    // TODO if somebody else need this method stay it here
    // else remove to test
    public String getRandomLoginFromView() {
        Random randomGenerator = new Random();
        int randomLoginRow = randomGenerator.nextInt(4) + 1;

        WebElement loginCell = driver.findElement(By.xpath(
                String.format(AdministrationPageLocators.LOGIN_CELL, randomLoginRow)));

        return loginCell.getText();
    }

    public void clickLastButton() {
        driver.findElement(AdministrationPageLocators.LAST_BUTTON).click();
    }

    public String getLastLogin() {
        WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE);
        int tableSize = table.findElements(AdministrationPageLocators.USERS_TABLE_ROWS).size();
        String login = driver.findElement(By.xpath(String
                .format(AdministrationPageLocators.LOGIN_CELL, tableSize))).getText();
        return login;
    }

    public void clickDeleteLastUser() {
        WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE);
        int tableSize = table.findElements(AdministrationPageLocators.USERS_TABLE_ROWS).size();
        driver.findElement(By.xpath(String
                .format(AdministrationPageLocators.DELETE_LINK, tableSize))).click();
    }
}
