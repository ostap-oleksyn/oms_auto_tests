package com.softserveinc.edu.ita.pageobjects;


import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * PageObject class that represents User Creating page.
 */
public class NewUserPage extends LogOutBase {

    public NewUserPage(final WebDriver driver) {
        super(driver);
    }

    public AdministrationPage clickCreateButton() {
        click(NewUserPageLocators.CREATE_BUTTON);
        return new AdministrationPage(driver);
    }

    public void fillLoginNameField(final String loginName) {
        sendKeys(NewUserPageLocators.LOGIN_NAME_INPUT, loginName);
    }

    public void fillFirstNameField(final String firstName) {
        sendKeys(NewUserPageLocators.FIRST_NAME_INPUT, firstName);
    }

    public void fillLastNameField(final String lastName) {
        sendKeys(NewUserPageLocators.LAST_NAME_INPUT, lastName);
    }

    public void fillPasswordField(final String password) {
        sendKeys(NewUserPageLocators.PASSWORD_INPUT, password);
    }

    public void fillConfirmPasswordField(final String password) {
        sendKeys(NewUserPageLocators.CONFIRM_PASSWORD_INPUT, password);
    }

    public void fillEmailField(final String email) {
        sendKeys(NewUserPageLocators.EMAIL_INPUT, email);
    }

    public void clickRoleRadioButton(final int roleRef) {
        click(NewUserPageLocators.ROLE_SELECT.modify(String.valueOf(roleRef)));
    }

    public void selectRegion(final int regionRef) {
        final Select regionSelect = new Select(driver.findElement(NewUserPageLocators.REGION_SELECT.getBy()));
        regionSelect.selectByIndex(regionRef);
    }

    public void fillAllFields(final User newUser) {

        fillLoginNameField(newUser.getLogin());
        fillFirstNameField(newUser.getFirstName());
        fillLastNameField(newUser.getLastName());
        fillPasswordField(newUser.getPassword());
        fillConfirmPasswordField(newUser.getPassword());
        fillEmailField(newUser.getEmail());
        selectRegion(newUser.getRegionReference());
        clickRoleRadioButton(newUser.getRoleReference());
    }
}
