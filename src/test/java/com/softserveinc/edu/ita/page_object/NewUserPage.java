package com.softserveinc.edu.ita.page_object;


import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class NewUserPage extends LoggedPageBase {

    public NewUserPage(WebDriver driver) {
        super(driver);
    }

    public void selectRegion(String regionName) {
        Select regionSelect = new Select(driver.findElement(NewUserPageLocators.REGION_SELECT));
        regionSelect.selectByVisibleText(regionName);
    }
}
