package com.softserveinc.edu.ita.enums;


public enum Roles {
    ADMINISTRATOR("Administrator"),
    CUSTOMER("Customer"),
    MERCHANDISER("Merchandiser"),
    SUPERVISOR("Supervisor"),
    ALL("All");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
