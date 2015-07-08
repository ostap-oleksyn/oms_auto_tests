package com.softserveinc.edu.ita.pageobjects;

import com.softserveinc.edu.ita.locators.interfaces.ILocator;
import com.softserveinc.edu.ita.locators.CommonLocators;
import org.openqa.selenium.WebDriver;

/**
 * PageObject class that represents User Info page.
 */
public class UserInfoPage extends LogOutBase {

    public UserInfoPage(final WebDriver driver) {
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

    public UserInfoPage clickLanguageLink(final ILocator locator){
        click(locator);

        return this;
    }
}
