package com.softserveinc.edu.ita.page_object;

import org.openqa.selenium.WebDriver;

public abstract class PageObjectBase {
    protected WebDriver driver;

    public PageObjectBase(WebDriver driver) {
        this.driver = driver;
    }
}

