package com.softserveinc.edu.ita.locators;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class NewUserPageLocators {

    public static final By LOGIN_NAME_INPUT = By.xpath(".//*[@id='login']");
    public static final By FIRST_NAME_INPUT = By.xpath(".//*[@id='firstName']");
    public static final By LAST_NAME_INPUT = By.xpath(".//*[@id='lastName']");
    public static final By PASSWORD_INPUT = By.xpath(".//*[@id='password']");
    public static final By CONFIRM_PASSWORD_INPUT = By.xpath(".//*[@id='confirmPassword']");
    public static final By EMAIL_INPUT = By.xpath(".//*[@id='email']");
    public static final By REGION_SELECT = By.xpath(".//*[@id='regionID']");
    public static final String ROLE_SELECT = ".//label[.='%s']/preceding::input[1]";
    public static final By CREATE_BUTTON = By.xpath(".//input[@type='submit'][@value='Create']");
    public static final By LOGIN_NAME_ERROR_LABEL = By.xpath(".//*[@id='nameError']");
    public static final By FIRST_NAME_ERROR_LABEL = By.xpath(".//*[@id='firstNameError']");
    public static final By LAST_NAME_ERROR_LABEL = By.xpath(".//*[@id='lastNameError']");
    public static final By PASSWORD_ERROR_LABEL = By.xpath(".//*[@id='passwordError']");
    public static final By CONFIRM_PASSWORD_ERROR_LABEL = By.xpath(".//*[@id='confirmError']");
    public static final By EMAIL_ERROR_LABEL = By.xpath(".//*[@id='emailError']");
}
