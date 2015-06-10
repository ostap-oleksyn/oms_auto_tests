package com.softserveinc.edu.ita.page_object;


import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import org.openqa.selenium.WebDriver;


public class UserInfoPage extends LoggedPageBase {

    public UserInfoPage(WebDriver driver) {
        super(driver);
    }

    public ItemManagementPage clickItemManagementTab() {
        driver.findElement(UserInfoPageLocators.USER_ITEM_MANAGEMENT_TAB).click();
        return new ItemManagementPage(driver);
    }

    public AdministrationPage clickAdministrationTab() {
        driver.findElement(UserInfoPageLocators.ADMINISTRATION_TAB).click();
        return new AdministrationPage(driver);
    }

    public OrderingPage clickOrderingTab() {
        driver.findElement(UserInfoPageLocators.ORDERING_TAB).click();
        return new OrderingPage(driver);
    }
}
