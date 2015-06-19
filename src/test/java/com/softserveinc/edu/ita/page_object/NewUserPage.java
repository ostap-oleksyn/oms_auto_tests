package com.softserveinc.edu.ita.page_object;


import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Regions;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.locators.NewUserPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * PageObject Class presents page for new User creating
 */
public class NewUserPage extends LogOutBase {

    public NewUserPage(WebDriver driver) {
        super(driver);
    }

    public AdministrationPage clickCreateButton() {
        click(NewUserPageLocators.CREATE_BUTTON);
        return new AdministrationPage(driver);
    }

    public void fillLoginNameField(String loginName) {
        sendKeys(NewUserPageLocators.LOGIN_NAME_INPUT, loginName);
    }

    public void fillFirstNameField(String firstName) {
        sendKeys(NewUserPageLocators.FIRST_NAME_INPUT, firstName);
    }

    public void fillLastNameField(String lastName) {
        sendKeys(NewUserPageLocators.LAST_NAME_INPUT, lastName);
    }

    public void fillPasswordField(String password) {
        sendKeys(NewUserPageLocators.PASSWORD_INPUT, password);
    }

    public void fillConfirmPasswordField(String password) {
        sendKeys(NewUserPageLocators.CONFIRM_PASSWORD_INPUT, password);
    }

    public void fillEmailField(String email) {
        sendKeys(NewUserPageLocators.EMAIL_INPUT, email);
    }

    public void clickRoleRadioButton(Roles roleName) {
        driver.findElement(By.xpath(String.format(NewUserPageLocators.ROLE_SELECT, roleName.ordinal() + 1))).click();
    }

    public void selectRegion(Regions regionName) {
        Select regionSelect = new Select(driver.findElement(NewUserPageLocators.REGION_SELECT.getBy()));
        regionSelect.selectByVisibleText(regionName.toString());
    }

    public void fillAllFields(User newUser) {

        fillLoginNameField(newUser.getLogin());
        fillFirstNameField(newUser.getFirstName());
        fillLastNameField(newUser.getLastName());
        fillPasswordField(newUser.getPassword());
        fillConfirmPasswordField(newUser.getPassword());
        fillEmailField(newUser.getEmail());
        selectRegion(Regions.valueOf(newUser.getRegionName().toUpperCase()));
        clickRoleRadioButton(Roles.valueOf(newUser.getRoleName().toUpperCase()));
    }
}
