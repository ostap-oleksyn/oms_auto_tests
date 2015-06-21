package com.softserveinc.edu.ita.enums;

/**
 * Created by Ihor-Dynka on 18.06.2015.
 */
public enum SearchFilters {
    FIRST_NAME("First Name"),
    LAST_NAME("Last Name"),
    ROLE("Role"),
    LOGIN_NAME("Login Name");

    private String filterName;

    SearchFilters(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterName() {
        return filterName;
    }

    @Override
    public String toString() {
        return filterName;
    }
}

