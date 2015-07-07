package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.enums.Regions;
import com.softserveinc.edu.ita.locators.EditUserPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * PageObject class that represents Edit User page.
 */
public class EditUserPage extends LogOutBase {
    public EditUserPage(final WebDriver driver) {
        super(driver);
    }

    public String getUserLogin() {
        return getElementAttribute(EditUserPageLocators.LOGIN_INPUT, "value");
    }

    public EditUserPage fillFirstNameField(final String newFirstName) {
        sendKeys(EditUserPageLocators.FIRST_NAME_INPUT, newFirstName);
        return this;
    }

    public EditUserPage fillLastNameField(final String newLastName) {
        sendKeys(EditUserPageLocators.LAST_NAME_INPUT, newLastName);
        return this;
    }


    public EditUserPage fillEmailField(final String email) {
        sendKeys(EditUserPageLocators.EMAIL_NAME_INPUT, email);
        return this;
    }

    public EditUserPage fillPasswordFields(final String password) {
        sendKeys(EditUserPageLocators.PASSWORD_INPUT, password);
        sendKeys(EditUserPageLocators.CONFIRM_PASSWORD_INPUT, password);
        return this;
    }

        public EditUserPage selectRegion(final Regions region) {
            final Select regionSelect = new Select(driver.findElement(EditUserPageLocators.REGION_SELECT.getBy()));
            regionSelect.selectByVisibleText(region.getRegionName());
        return this;
    }

    public void selectMerchandiserRole(){
        click(EditUserPageLocators.ROLE_MERCHANDISER_SELECT);
    }

    public AdministrationPage clickCreateButton() {
        click(EditUserPageLocators.CREATE_BUTTON);
        return new AdministrationPage(driver);
    }
}
