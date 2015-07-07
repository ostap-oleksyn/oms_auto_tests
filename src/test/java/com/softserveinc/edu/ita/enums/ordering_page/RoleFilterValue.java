package com.softserveinc.edu.ita.enums.ordering_page;


public enum RoleFilterValue {
    NONE("None"),
    ADMINISTRATOR("Administrator"),
    CUSTOMER("Customer"),
    MERCHANDISER("Merchandiser"),
    SUPERVISOR("Supervisor");

    private String filterValue;

    RoleFilterValue(final String filterValue) {
        this.filterValue = filterValue;
    }

    @Override
    public String toString() {
        return filterValue;
    }
}
