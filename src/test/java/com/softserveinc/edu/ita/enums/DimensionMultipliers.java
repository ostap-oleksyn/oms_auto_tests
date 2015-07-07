package com.softserveinc.edu.ita.enums;


/**
 *  Enum with dimension multilpliers.
 */
public enum DimensionMultipliers {
    ITEM(1, "Item"),
    MERCHANDISER(5, "Box"),
    SUPERVISOR(10, "Package");

    private int itemsCount;
    private String dimensionName;

    DimensionMultipliers(final int itemsCount, final String dimensionName) {
        this.itemsCount = itemsCount;
        this.dimensionName = dimensionName;
    }

    @Override
    public String toString() {
        return "DimensionMultiplayers{" +
                "itemsCount=" + itemsCount +
                ", dimensionName='" + dimensionName + '\'' +
                '}';
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public static int getItemsCount(final String dimensionName) {
        for (final DimensionMultipliers multiplayer: DimensionMultipliers.values()) {
            if (multiplayer.dimensionName.equals(dimensionName)) {
                return multiplayer.getItemsCount();
            }
        }
        throw new IllegalArgumentException();
    }
}
