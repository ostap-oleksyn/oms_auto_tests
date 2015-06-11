package com.softserveinc.edu.ita.locators;


import org.openqa.selenium.By;

public final class OrderingPageLocators {

    private OrderingPageLocators() {
    }

    public static final By CREATE_NEW_ORDER_LINK = By.xpath(".//*[@id='content']/a[contains(text(), 'Create new order')]");
    public static final By QUANTITY_OF_ROWS = By.xpath(".//*[@id='allFoundAndFiltered']");
    public static final By QUANTITY_OF_ROWS_PER_PAGE = By.xpath(".//*[@id='list']/table/tbody/tr/td[1]");
    public static final By CLICK_NEXT_BUTTON = By.xpath(".//*[@name='nextPage']");
    public static final By CLICK_FIRST_BUTTON = By.xpath(".//*[@name='firstPage']");
    public static final String TABLE_ROW_CELL = ".//div[@id='list']/table/tbody/tr[%s]/td[1]";
    public static final String TABLE_ROW = ".//div[@id='list']/table/tbody/tr[%s]/td";
    public static final String TABLE_COLUMN = ".//*[@id='list']/table/tbody/tr[1]/th/a[contains(text(), '%s')]";

}
