package com.softserveinc.edu.ita.enums.item_management_page;

public enum ItemFilter {
    PRODUCT_NAME("Product Name"),
    DESCRIPTION("Description");

    private String filterName;

    ItemFilter(String filterName) {
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
