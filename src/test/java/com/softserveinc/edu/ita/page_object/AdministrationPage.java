package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class AdministrationPage extends LogOutBase {

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    // edit user: FirstName: Vitaliy, Login: antonov, Region: North
    // TODO make universal edition opportunity
    public EditUserPage clickEditUserLink() {

        Random random = new Random();
        int row = random.nextInt(5);
        final By EDIT_USER_LINK = By.xpath(".//*[@id='table']/tbody/tr[" + row + "]/td[6]/a[contains(text(), 'Edit')]");

        driver.findElement(EDIT_USER_LINK).click();
        return new EditUserPage(driver);
    }
}
