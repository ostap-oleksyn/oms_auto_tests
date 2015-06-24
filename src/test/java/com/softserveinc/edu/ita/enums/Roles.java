package com.softserveinc.edu.ita.enums;

import java.util.Random;

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

    public static Roles getRandomRole() {
        Random randomGenerator = new Random();
        return values()[randomGenerator.nextInt(values().length - 1)];
    }
}
