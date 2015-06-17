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

    Random randomGenerator = new Random();
    int randomUserLine = randomGenerator.nextInt(4) + 1;

    public String getRandomLoginFromView() {

        WebElement loginCell = driver.findElement(By.xpath(
                String.format(AdministrationPageLocators.LOGIN_CELL, randomUserLine)));

        return loginCell.getText();
    }

    public EditUserPage clickRandomEditButton() {

        WebElement editButton = driver.findElement(By.xpath(
                String.format(AdministrationPageLocators.EDIT_USER_LINK, randomUserLine)));
        editButton.click();

        return new EditUserPage(driver);
    }


}
