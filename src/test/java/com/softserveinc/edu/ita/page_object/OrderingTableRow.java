package com.softserveinc.edu.ita.page_object;

/**
 * Created by true on 08.06.2015.
 */
public class OrderingTableRow {
    private String orderName;
    private String totalPrice;
    private String maxDiscount;
    private String deliveryDate;
    private String status;
    private String assignee;
    private String role;
    private String edit;
    private String delete;

    public String getOrderName() {
        return orderName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getMaxDiscount() {
        return maxDiscount;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getRole() {
        return role;
    }

    public String getEdit() {
        return edit;
    }

    public String getDelete() {
        return delete;
    }

    public OrderingTableRow(String orderName, String totalPrice, String maxDiscount,
                            String deliveryDate, String status, String assignee,
                            String role, String edit, String delete) {
        this.orderName = orderName;
        this.totalPrice = totalPrice;
        this.maxDiscount = maxDiscount;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.assignee = assignee;
        this.role = role;
        this.edit = edit;
        this.delete = delete;
    }
    @Override
    public String toString(){
        return this.orderName + " " + this.totalPrice + " " + this.maxDiscount + " " + this.deliveryDate + " " + this.status
                + " " + this.assignee + " " + this.role + " " + this.edit + " " + this.delete;
    }
}
