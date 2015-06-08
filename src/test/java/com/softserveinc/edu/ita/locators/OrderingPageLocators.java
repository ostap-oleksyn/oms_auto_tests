package com.softserveinc.edu.ita.locators;

import org.openqa.selenium.By;

public class OrderingPageLocators {

    private OrderingPageLocators() {
    }

    public static final By ORDERING_PAGE_TAB_ = By.xpath(".//*[@id='nav']/li[2]/a");
    public static final By ORDERING_TABLE_FIRST_ELEMENT_TEXT = By.xpath(".//*[@id='list']/table/tbody/tr[1]/th[1]/a");
    public static final By CREATE_NEW_ORDER_BUTTON = By.xpath(".//*[@id='content']/a");
}
