package com.softserveinc.edu.ita.enums.item_management_page;

/**
 * Created by true on 01.07.2015.
 */
public enum ProductsTableColumns {
    NAME("Name"),
    DESCRIPTION("Description"),
    PRICE("Price");

    private String name;

    ProductsTableColumns(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
