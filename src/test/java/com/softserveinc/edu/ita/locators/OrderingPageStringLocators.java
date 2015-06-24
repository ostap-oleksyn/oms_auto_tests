package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

public enum OrderingPageStringLocators implements ILocator {

    TABLE_ROW_CELL(".//div[@id='list']/table/tbody/tr[%s]/td[1]"),
    TABLE_ROW(".//div[@id='list']/table/tbody/tr[%s]/td"),
    TABLE_COLUMN(".//*[@id='list']/table/tbody/tr[1]/th/a[contains(text(), '%s')]");

    OrderingPageStringLocators(String stringLocator){
        this.stringLocator = stringLocator;
    }

    private String stringLocator;
    private String name;
    private By byLocator;

    public OrderingPageStringLocators getByWithParameter(String parameter) {
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
