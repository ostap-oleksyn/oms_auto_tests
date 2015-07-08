package com.softserveinc.edu.ita.enums.ordering_page;

public enum OrdersTableColumns {
    ORDER_NAME("Order Name"),
    TOTAL_PRICE("Total price"),
    MAX_DISCOUNT("Max Discount "),
    DELIVERY_DATE("Delivery date"),
    STATUS("Status"),
    ASSIGNEE("Assignee"),
    ROLE("Role");

    private String name;

    OrdersTableColumns(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
