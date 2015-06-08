package com.softserveinc.edu.ita.locators;


import org.openqa.selenium.By;

public final class UserInfoPageLocators {

    private UserInfoPageLocators() {
    }

    public static final By FIRST_NAME_LABEL = By.xpath("//fieldset//tr[1]/td[2]");
    public static final By LAST_NAME_LABEL = By.xpath("//fieldset//tr[2]/td[2]");
    public static final By CUSTOMER_TYPE_LABEL = By.xpath("//fieldset//tr[3]/td[2]");
    public static final By USER_ROLE_LABEL = By.xpath("//fieldset//tr[4]/td[2]");
    public static final By USER_INFO_TAB = By.xpath(".//*[@id='nav']//a[contains(text(), 'User Info')]");
    public static final By USER_ITEM_MANAGEMENT_TAB = By.xpath(".//*[@id='nav']//a[contains(text(), 'Item Management')]");
    public static final By ACTIVE_TAB = By.xpath(".//a[parent::li[@class='cur']]");
    public static final By ADMINISTRATION_TAB = By.xpath(".//*[@id='nav']//a[contains(text(), 'Administration')]");
    public static final By ORDERING_TAB = By.xpath(".//*[@id='nav']//a[contains(text(), 'Ordering')]");

}
