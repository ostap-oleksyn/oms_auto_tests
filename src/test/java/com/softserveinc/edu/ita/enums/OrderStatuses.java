package com.softserveinc.edu.ita.enums;

/**
 * Enum with regions names. Can generate random values
 */
public enum OrderStatuses {
    CREATED(1, "Created"),
    PENDING(2, "Pending"),
    ORDERED(3, "Ordered"),
    DELIVERED(4, "Delivered");

    private int statusReference;
    private String statusName;

    OrderStatuses(int statusReference, String statusName) {
        this.statusReference = statusReference;
        this.statusName = statusName;
    }

    public int getStatusReference() {
        return statusReference;
    }

    public String getStatusName() {
        return statusName;
    }

    @Override
    public String toString() {
        return "OrderStatuses{" +
                "statusReference=" + statusReference +
                ", statusName='" + statusName + '\'' +
                '}';
    }

    public static String getStatusName(int statusReference) {

        for (OrderStatuses orderStatus: OrderStatuses.values()) {
            if (orderStatus.getStatusReference() == statusReference) {
                return orderStatus.getStatusName();
            }
        }

        return null;
    }
}
