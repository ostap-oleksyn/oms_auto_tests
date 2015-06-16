package com.softserveinc.edu.ita.enums;

/**
 * This enum serves to limit choice of possible "Administration" table columns presented at "Administration" page. This is used in "clickAdministrationTableColumn" method.
 */
public enum UsersTableColumns {
    FIRST_NAME("First name"),
    LAST_NAME("Last name"),
    LOGIN("Login"),
    ROLE("Role"),
    REGION("Region");

    private String name;

    UsersTableColumns(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
