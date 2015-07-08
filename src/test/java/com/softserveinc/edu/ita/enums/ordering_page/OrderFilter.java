package com.softserveinc.edu.ita.enums.ordering_page;

public enum OrderFilter {
    STATUS("Status"),
    ROLE("Role");

    private String filterName;

    OrderFilter(final String filterName) {
        this.filterName = filterName;
    }

    public String getFilterName() {
        return filterName;
    }

    @Override
    public String toString() {
        return filterName;
    }
}
