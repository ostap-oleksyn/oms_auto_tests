package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AdministrationPageLocators implements ILocator {

    CREATE_NEW_USER_LINK(
            "Create new user link",
            By.xpath(".//*[@id='list']/a[contains(text(), 'Create New User')]"));

    AdministrationPageLocators(String name, By locator) {
        this.name = name;
        this.locator = locator;
    }

<<<<<<< HEAD
    private String name;
    private By locator;

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public By getBy() {
        return this.locator;
    }
=======
    public static final By CREATE_NEW_USER_LINK = By.xpath(".//*[@id='list']/a[contains(text(), 'Create New User')]");
    public static final By QUANTITY_OF_TABLE_PAGES = By.xpath(".//*[@id='pageCount']");
    public static final By TABLE_ROWS = By.xpath(".//*[@id='table']//tr");
    public static final By ROW_CELLS = By.tagName("td");
    public static final By FIRST_BUTTON = By.xpath(".//*[@id='first']");
    public static final By NEXT_BUTTON = By.xpath(".//input[@id='next']");
    public static final String TABLE_COLUMN = ".//*[@id='table']/thead/tr/th/a[contains(text(), '%s')]";
>>>>>>> IFAA-48-2
}
