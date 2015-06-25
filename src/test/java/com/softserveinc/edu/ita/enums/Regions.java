package com.softserveinc.edu.ita.enums;

/**
 * Enum with regions names. Can generate random values
 */
public enum Regions {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West"),
    ALL("ALL");

    private String region;

    Regions(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return this.region;
    }
}
