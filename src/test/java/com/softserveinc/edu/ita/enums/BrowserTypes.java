package com.softserveinc.edu.ita.enums;

/**
 * Created by student on 6/5/2015.
 */
public enum BrowserTypes {

    FIREFOX ("firefox"),
    CHROME ("chrome"),
    INTERNET_EXPLORER ("internet_explorer"),
    PHANTOM_JS ("phantom_js"),
    HEADLESS ("headless");

    BrowserTypes (String name) {
        this.name = name;
    }
    private String name;
}
