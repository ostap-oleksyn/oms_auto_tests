package com.softserveinc.edu.ita.locators;

/**
 * Created by student on 6/24/2015.
 */

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AdministrationPageStringLocators implements ILocator {

    TABLE_COLUMN(
            "Table column",
            ".//*[@id='table']/thead/tr/th/a[contains(text(), '%s')]"),
    DELETE_LINK(
            "Delete link",
            ".//*[@id='table']/tbody/tr[%s]/td[7]/a"),
    LOGIN_CELL(
            "Login cell",
            ".//*[@id='table']/tbody/tr[%s]/td[3]");

    AdministrationPageStringLocators(String name, String stringLocator) {
        this.name = name;
        this.stringLocator = stringLocator;
    }

    private String name;
    private String stringLocator;
    private By byLocator;

    public void setByWithParameter(String parameter) {
        this.name = parameter;
        this.byLocator = By.xpath(String.format(this.stringLocator, parameter));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public By getBy() {
        return this.byLocator;
    }
}
