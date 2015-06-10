package com.softserveinc.edu.ita.enums;


import java.util.Random;

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

    public static Roles getRandomRole () {
        Random randomGenerator = new Random();
        return values()[randomGenerator.nextInt(values().length - 1)];
    }
}
