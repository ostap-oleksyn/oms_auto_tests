package com.softserveinc.edu.ita.enums.ordering_page;


public enum StatusFilterValue {
    NONE("None"),
    CREATED("Created"),
    ORDERED("Ordered"),
    PENDING("Pending"),
    DELIVERED("Delivered");

    private String filterValue;

    StatusFilterValue(String filterName) {
        this.filterValue = filterName;
    }

    @Override
    public String toString() {
        return filterValue;
    }
}
