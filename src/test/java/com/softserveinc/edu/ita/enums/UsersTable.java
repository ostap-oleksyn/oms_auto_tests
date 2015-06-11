package com.softserveinc.edu.ita.enums;

/**
 * This enum serves to limit possible columns of 'Ordering' table in 'Ordering' page.
 */
public enum UsersTable {
    FIRST_NAME("First name"),
    LAST_NAME("Last name"),
    LOGIN("Login"),
    ROLE("Delivery date"),
    REGION("Status");

    private String name;

    UsersTable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
