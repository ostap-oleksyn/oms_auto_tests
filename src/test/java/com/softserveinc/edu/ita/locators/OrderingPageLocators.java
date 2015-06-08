package com.softserveinc.edu.ita.locators;


import org.openqa.selenium.By;

public final class OrderingPageLocators {

    private OrderingPageLocators() {
    }

    public static final By CREATE_NEW_ORDER_LINK = By.xpath(".//*[@id='content']/a[contains(text(), 'Create new order')]");

}
