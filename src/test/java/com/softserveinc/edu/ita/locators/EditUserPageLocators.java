package com.softserveinc.edu.ita.locators;

import org.openqa.selenium.By;

/**
 * Created by Olia on 15.06.2015.
 */
public class EditUserPageLocators {


    private EditUserPageLocators() {
    }

    public static final By EDIT_PAGE_MESSAGE = By.xpath(".//*[@id='edit']/h3[contains(text()," +
            " 'This page is appointed for creating new user for particular role.')]");
    public static final By REGION_SELECT = By.xpath(".//*[@id='regionID']");
    public static final By CREATE_BUTTON = By.xpath(".//input[@type='submit'][@value='Create']");
    public static final By FIRST_NAME_INPUT = By.xpath(".//*[@id='firstName']");
    public static final By PASSWORD_INPUT = By.xpath(".//*[@id='password']");
    public static final By CONFIRM_PASSWORD_INPUT = By.xpath(".//*[@id='confirmPassword']");
}

