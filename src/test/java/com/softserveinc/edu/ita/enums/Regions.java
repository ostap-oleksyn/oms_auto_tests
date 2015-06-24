package com.softserveinc.edu.ita.enums;

import java.util.Random;

/**
 * Enum with regions names. Can generate random values
 */
public enum Regions {
    NORTH(1, "North"),
    EAST(2, "East"),
    SOUTH(3, "South"),
    WEST(4, "West"),
    ALL(5, "ALL");

    private int regionRef;
    private String regionName;

    Regions(int regionRef, String regionName) {
        this.regionRef = regionRef;
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public int getRegionRef() {
        return regionRef;
    }

    @Override
    public String toString() {
        return "Regions{" +
                "regionRef=" + regionRef +
                ", regionName='" + regionName + '\'' +
                '}';
    }

    public static String getRegionName(int regionRef) {

        for (Regions region: Regions.values()) {
            if (region.getRegionRef() == regionRef) {
                return region.getRegionName();
            }
        }

        return null;
    }
}
