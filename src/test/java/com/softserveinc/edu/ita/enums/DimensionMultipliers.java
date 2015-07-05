package com.softserveinc.edu.ita.enums;

/**
 * Enum with roles names. Can generate random values
 */
public enum DimensionMultipliers {
    ITEM(1, "Item"),
    MERCHANDISER(5, "Box"),
    SUPERVISOR(10, "Package");

    private int itemsCount;
    private String dimensionName;

    DimensionMultipliers(int itemsCount, String dimensionName) {
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

    public static int getItemsCount(String dimensionName) {
        for (DimensionMultipliers multiplayer: DimensionMultipliers.values()) {
            if (multiplayer.dimensionName.equals(dimensionName)) {
                return multiplayer.getItemsCount();
            }
        }
        throw new IllegalArgumentException();
    }
}
