package com.softserveinc.edu.ita.page_object;


import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class NewUserPage extends LogOutBase {

    public NewUserPage(WebDriver driver) {
        super(driver);
    }

    public void selectRegion(String regionName) {
        Select regionSelect = new Select(driver.findElement(NewUserPageLocators.REGION_SELECT));
        regionSelect.selectByVisibleText(regionName);
    }

    public AdministrationPage clickCreateButton() {
        driver.findElement(NewUserPageLocators.CREATE_BUTTON).click();
        return  new AdministrationPage(driver);
    }

    public AdministrationPage clickCancelButton() {
        driver.findElement(NewUserPageLocators.CANCEL_BUTTON).click();
        return  new AdministrationPage(driver);
    }

    public String getAlertTextAndClose() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;

    }
}
