package com.softserveinc.edu.ita.locators;


import org.openqa.selenium.By;

public final class OrderingPageLocators {

    private OrderingPageLocators() {
    }

    public static final By CREATE_NEW_ORDER_LINK = By.xpath(".//*[@id='content']/a[contains(text(), 'Create new order')]");
    public static final By QUANTITY_OF_ROWS = By.xpath(".//*[@id='allFoundAndFiltered']");
    public static final By CLICK_NEXT = By.xpath(".//*[@name='nextPage']");
    public static final By CLICK_FIRST = By.xpath(".//*[@name='firstPage']");




}
