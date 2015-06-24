package com.softserveinc.edu.ita.enums;

import java.util.Random;

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

    public static Regions getRandomRegion() {
        Random randomGenerator = new Random();
        return values()[randomGenerator.nextInt(values().length - 1)];
    }

    @Override
    public String toString() {
        return this.region;
    }

}
