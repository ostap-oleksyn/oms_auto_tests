package com.softserveinc.edu.ita.enums;

/**
 * Created by 1 on 03.06.2015.
 */
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
