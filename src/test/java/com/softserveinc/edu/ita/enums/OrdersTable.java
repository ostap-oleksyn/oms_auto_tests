package com.softserveinc.edu.ita.enums;
/**
 * This enum serves to limit possible columns of 'Ordering' table in 'Ordering' page.
 */
public enum OrdersTable {
    ORDER_NAME("Order Name"),
    TOTAL_PRICE("Total price"),
    MAX_DISCOUNT("Max Discount "),
    DELIVERY_DATE("Delivery date"),
    STATUS("Status"),
    ASSIGNEE("Assignee"),
    ROLE("Role");


    private String ordersTable;

    OrdersTable(String ordersTable) {
        this.ordersTable = ordersTable;
    }

    @Override
    public String toString() {
        return this.ordersTable;
    }
}
