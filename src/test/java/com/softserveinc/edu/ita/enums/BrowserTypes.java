package com.softserveinc.edu.ita.enums;

public enum BrowserTypes {

    FIREFOX("firefox"),
    CHROME("chrome"),
    INTERNET_EXPLORER("internet_explorer"),
    PHANTOM_JS("phantom_js"),
    HEADLESS("headless"),
    FIREFOX_REMOTE_WINDOWS("firefox_remote_windows"),
    CHROME_REMOTE_WINDOWS("chrome_remote_windows"),
    IE_REMOTE_WINDOWS("ie_remote_windows");

    BrowserTypes(final String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}