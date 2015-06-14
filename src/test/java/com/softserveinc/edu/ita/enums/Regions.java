package com.softserveinc.edu.ita.enums;

import java.util.Random;

/**
 * Enum with regions names. Can generate random values
 */
public enum Regions {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

    private String region;

    Regions(String region) {
        this.region = region;
    }

    public static Regions getRandomRegion() {
        Random randomGenerator = new Random();
        return values()[randomGenerator.nextInt(values().length)];
    }

    @Override
    public String toString() {
        return this.region;
    }

}
