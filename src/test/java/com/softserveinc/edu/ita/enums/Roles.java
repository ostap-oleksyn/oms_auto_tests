package com.softserveinc.edu.ita.enums;

/**
 * Enum with roles names. Can generate random values
 */
public enum Roles {
    ADMINISTRATOR("Administrator"),
    CUSTOMER("Customer"),
    MERCHANDISER("Merchandiser"),
    SUPERVISOR("Supervisor"),
    ALL("All");

    private String name;

    Roles(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
