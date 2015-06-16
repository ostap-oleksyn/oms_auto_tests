package com.softserveinc.edu.ita.page_object;


import com.softserveinc.edu.ita.locators.AdministrationPageLocators;
import org.openqa.selenium.WebDriver;

public class AdministrationPage extends LogOutBase {

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }


    public EditUserPage clickEditButton() {

        click(AdministrationPageLocators.EDIT_USER_LINK);
        return new EditUserPage(driver);
    }


}
