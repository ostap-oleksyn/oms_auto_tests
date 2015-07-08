package com.softserveinc.edu.ita.enums;

public enum OrderStatuses {
    CREATED(1, "Created"),
    PENDING(2, "Pending"),
    ORDERED(3, "Ordered"),
    DELIVERED(4, "Delivered");

    private int statusReference;
    private String statusName;

    OrderStatuses(final int statusReference, final String statusName) {
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

    public static String getStatusName(final int statusReference) {

        for (final OrderStatuses orderStatus: OrderStatuses.values()) {
            if (orderStatus.getStatusReference() == statusReference) {
                return orderStatus.getStatusName();
            }
        }

        return null;
    }

    public static int getStatusReference(final String statusName) {

        for (final OrderStatuses orderStatus: OrderStatuses.values()) {
            if (orderStatus.getStatusName() == statusName) {
                return orderStatus.getStatusReference();
            }
        }

        return 0;
    }

}
