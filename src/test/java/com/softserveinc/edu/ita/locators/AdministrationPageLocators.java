package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.page_object.AdministrationPage;
import org.openqa.selenium.By;
//TODO remove Locators from name
public final class AdministrationPageLocators {

    private AdministrationPageLocators() {
    }

    public static final By CREATE_NEW_USER_LINK = By.xpath(".//*[@id='list']/a[contains(text(), 'Create New User')]");
    public static final By EDIT_USER_LINK = By.xpath(".//*[@id='table']/tbody/tr[4]/td[6]/a[contains(text(), 'Edit')]");
}
