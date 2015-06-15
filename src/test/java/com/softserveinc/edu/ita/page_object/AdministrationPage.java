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

    public void closeAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public String getRadomLoginFromView() {
        Random randomGenerator = new Random();
        int randomLoginRow = randomGenerator.nextInt(4) + 1;

        WebElement loginCell = driver.findElement(By.xpath(
                String.format(AdministrationPageLocators.LOGIN_CELL, randomLoginRow)));

        return loginCell.getText();
    }

    public void clickLastButton() {
        driver.findElement(AdministrationPageLocators.LAST_BUTTON).click();
    }

//    public String getLastUserLogin() {
//        WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE);
//        int lastRowNumber = table.findElements(By.xpath(".//tbody/tr")).size();
//        String login = driver.findElement(By.xpath(String
//                .format(AdministrationPageLocators.LOGIN_CELL, lastRowNumber))).getText();
//        return login;
//    }


    public String getLastLogin() {
        WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE);
        int tableSize = table.findElements(By.xpath(".//tbody/tr")).size();
        String login = driver.findElement(By.xpath(String
                .format(AdministrationPageLocators.LOGIN_CELL, tableSize))).getText();
        return login;
    }

    public void clickDeleteLastUser() {
        WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE);
        int tableSize = table.findElements(By.xpath(".//tbody/tr")).size();
        driver.findElement(By.xpath(String
                .format(AdministrationPageLocators.DELETE_LINK, tableSize))).click();
    }
}
