package com.softserveinc.edu.ita.page_object;


import com.softserveinc.edu.ita.locators.CommonLocators;
import org.openqa.selenium.WebDriver;


public class UserInfoPage extends LogOutBase {

    public UserInfoPage(WebDriver driver) {
        super(driver);
    }

    public ItemManagementPage clickItemManagementTab() {
        click(CommonLocators.ITEM_MANAGEMENT_TAB);
        return new ItemManagementPage(driver);
    }

    public AdministrationPage clickAdministrationTab() {
        click(CommonLocators.ADMINISTRATION_TAB);
        return new AdministrationPage(driver);
    }

    public OrderingPage clickOrderingTab() {
        click(CommonLocators.ORDERING_TAB);
        return new OrderingPage(driver);
    }
}
