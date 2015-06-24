package com.softserveinc.edu.ita.locators;

/**
 * Created by student on 6/24/2015.
 */

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum AdministrationPageStringLocators implements ILocator {

    TABLE_COLUMN(".//*[@id='table']/thead/tr/th/a[contains(text(), '%s')]"),
    DELETE_LINK(".//*[@id='table']/tbody/tr[%s]/td[7]/a"),
    LOGIN_CELL(".//*[@id='table']/tbody/tr[%s]/td[3]");

    AdministrationPageStringLocators(String stringLocator){
        this.stringLocator = stringLocator;
    }

    private String stringLocator;
    private String name;
    private By byLocator;

    public AdministrationPageStringLocators getByWithParameter(String parameter) {
        this.name = parameter;
        this.byLocator = By.xpath(String.format(this.stringLocator, parameter));
        return this;
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
