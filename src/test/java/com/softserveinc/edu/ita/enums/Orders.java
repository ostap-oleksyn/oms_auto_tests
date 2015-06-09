package com.softserveinc.edu.ita.enums;
/**
 * Created by student on 6/9/2015.
 */
public enum Orders {
    ORDERNAME("Order Name"),
    TOTALPRICE("Total price"),
    MAXDISCOUNT("Max Discount "),
    DELIVERYDATE("Delivery date"),
    STATUS("Status"),
    ASSIGNEE("Assignee"),
    ROLE("Role");


    private String order;

    Orders(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return this.order;
    }
}
