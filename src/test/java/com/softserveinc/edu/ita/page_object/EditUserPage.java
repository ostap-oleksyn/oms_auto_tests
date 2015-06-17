package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.locators.EditUserPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class EditUserPage extends LogOutBase {

    public EditUserPage(WebDriver driver) {
        super(driver);
    }

    public AdministrationPage clickCreateButton() {
        click(EditUserPageLocators.CREATE_BUTTON);
       return new AdministrationPage(driver);
    }

     public void changeFirstName (String newName) throws IOException {

         driver.findElement(EditUserPageLocators.FIRST_NAME_INPUT.getBy()).sendKeys(newName);
   }

    public void changeRole(String roleName) {
        driver.findElement(By.xpath(String.format(EditUserPageLocators.ROLE_SELECT, roleName))).click();
    }



}
