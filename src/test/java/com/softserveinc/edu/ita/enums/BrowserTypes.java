package com.softserveinc.edu.ita.enums;

public enum BrowserTypes {

    FIREFOX("firefox"),
    CHROME("chrome"),
    INTERNET_EXPLORER("internet_explorer"),
    PHANTOM_JS("phantom_js"),
    HEADLESS("headless");

    BrowserTypes(final String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}