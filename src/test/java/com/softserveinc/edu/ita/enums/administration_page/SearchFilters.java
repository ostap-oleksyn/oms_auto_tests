package com.softserveinc.edu.ita.enums.administration_page;

public enum SearchFilters {
    ALL_COLUMNS("All Columns"),
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

