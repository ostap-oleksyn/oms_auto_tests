package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class AdministrationPage extends LogOutBase {

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    public NewUserPage clickCreateUserLink() {
        click(AdministrationPageLocators.CREATE_NEW_USER_LINK);
        return new NewUserPage(driver);
    }

    public String getRandomLoginFromView() {
        Random randomGenerator = new Random();
        int randomLoginRow = randomGenerator.nextInt(4) + 1;

        WebElement loginCell = driver.findElement(By.xpath(
                String.format(AdministrationPageLocators.LOGIN_CELL, randomLoginRow)));

        return loginCell.getText();
    }

    public void clickLastButton() {
        click(AdministrationPageLocators.LAST_BUTTON);
    }

    public String getLastLogin() {
        WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE.getBy());
        int tableSize = table.findElements(AdministrationPageLocators.USERS_TABLE_ROWS.getBy()).size();
        String login = driver.findElement(By.xpath(String
                .format(AdministrationPageLocators.LOGIN_CELL, tableSize))).getText();
        return login;
    }

    public void clickDeleteLastUserLink() {
        WebElement table = driver.findElement(AdministrationPageLocators.USERS_TABLE.getBy());
        int tableSize = table.findElements(AdministrationPageLocators.USERS_TABLE_ROWS.getBy()).size();
        driver.findElement(By.xpath(String
                .format(AdministrationPageLocators.DELETE_LINK, tableSize))).click();
    }

}
