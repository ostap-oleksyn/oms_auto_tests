package com.softserveinc.edu.ita.locators;

import org.openqa.selenium.By;

public final class HomePage {

    private HomePage() {
    }

    public static final By LOGIN_USER_INPUT = By.xpath(".//*[@id='edit']//tr[1]/td[2]/input");
    public static final By LOGIN_PASSWORD_INPUT = By.xpath(".//*[@id='edit']//tr[2]/td[2]/input");
    public static final By LOG_OUT_BUTTON = By.xpath(".//*[@id='nav']//a");

}