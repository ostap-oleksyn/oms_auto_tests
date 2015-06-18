package com.softserveinc.edu.ita.enums;

/**
 * Created by Ihor-Dynka on 18.06.2015.
 */
public enum AdministrationTabFilters {
    ALL_COLUMNS("All Columns",""),
    FIRST_NAME("First Name","FirstName"),
    LAST_NAME("Last Name","LastName"),
    ROLE("Role","RoleName"),
    LOGIN_NAME("Login Name","Login");

    private String filterName;
    private String value;

    AdministrationTabFilters(String filterName, String value) {
        this.filterName = filterName;
        this.value = value;
    }

    public String getFilterName() {
        return filterName;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return filterName;
    }
}

