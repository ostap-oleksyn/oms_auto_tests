package com.softserveinc.edu.ita.enums.ordering_page;

/**
 * Created by student on 7/1/2015.
 */
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
