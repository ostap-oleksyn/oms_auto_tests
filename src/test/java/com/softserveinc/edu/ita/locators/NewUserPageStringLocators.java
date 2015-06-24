package com.softserveinc.edu.ita.locators;

import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * Created by true on 24.06.2015.
 */
public enum NewUserPageStringLocators implements ILocator {

    ROLE_SELECT(".//*[@id='roleID%s']");

    NewUserPageStringLocators(String stringLocator){
        this.stringLocator = stringLocator;
    }

    private String stringLocator;
    private String name;
    private By byLocator;

    public NewUserPageStringLocators getByWithParameter(String parameter) {
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
