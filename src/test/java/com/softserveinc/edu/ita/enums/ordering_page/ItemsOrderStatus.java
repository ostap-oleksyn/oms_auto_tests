package com.softserveinc.edu.ita.enums.ordering_page;

public enum ItemsOrderStatus {
    ORDERED("Ordered"),
    PENDING("Pending"),
    DELIVERED("Delivered");

    private String statusName;

    ItemsOrderStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }
}
