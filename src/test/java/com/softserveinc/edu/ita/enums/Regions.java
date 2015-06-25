package com.softserveinc.edu.ita.enums;

/**
 * Enum with regions names. Can generate random values
 */
public enum Regions {
    NORTH(1, "North"),
    EAST(2, "East"),
    SOUTH(3, "South"),
    WEST(4, "West"),
    ALL(5, "ALL");

    private int regionReference;
    private String regionName;

    Regions(int regionReference, String regionName) {
        this.regionReference = regionReference;
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public int getRegionReference() {
        return regionReference;
    }

    @Override
    public String toString() {
        return "Regions{" +
                "regionReference=" + regionReference +
                ", regionName='" + regionName + '\'' +
                '}';
    }

    public static String getRegionName(int regionRef) {

        for (Regions region: Regions.values()) {
            if (region.getRegionReference() == regionRef) {
                return region.getRegionName();
            }
        }

        return null;
    }
}
