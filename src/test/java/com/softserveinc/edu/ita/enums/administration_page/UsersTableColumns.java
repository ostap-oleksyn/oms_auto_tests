package com.softserveinc.edu.ita.enums.administration_page;

public enum UsersTableColumns {
    FIRST_NAME("First name"),
    LAST_NAME("Last name"),
    LOGIN("Login"),
    ROLE("Role"),
    REGION("Region");

    private String name;

    UsersTableColumns(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
