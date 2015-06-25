package com.softserveinc.edu.ita.domains;


import com.softserveinc.edu.ita.enums.OrderStatuses;
import com.softserveinc.edu.ita.enums.Roles;

import java.util.Date;

public class Order {

    private int id;
    private String orderName;
    private int maxDiscount;
    private int orderNumber;
    private Double totalPrice;
    private int assignee;
    private int customer;
    private int orderStatusReference;
    private String deliveryDate;
    private String preferableDeliveryDate;

    private Order() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getOrderStatusReference() {
        return orderStatusReference;
    }

    public void setOrderStatusReference(int orderStatusReference) {
        this.orderStatusReference = orderStatusReference;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPreferableDeliveryDate() {
        return preferableDeliveryDate;
    }

    public void setPreferableDeliveryDate(String preferableDeliveryDate) {
        this.preferableDeliveryDate = preferableDeliveryDate;
    }

    public String getStatusName() {
        return OrderStatuses.getStatusName(orderStatusReference);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", maxDiscount=" + maxDiscount +
                ", orderNumber=" + orderNumber +
                ", totalPrice=" + totalPrice +
                ", assignee=" + assignee +
                ", customer=" + customer +
                ", orderStatusReference=" + orderStatusReference +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", preferableDeliveryDate='" + preferableDeliveryDate + '\'' +
                '}';
    }

    public static IdStep newBuilder() {
        return new Builder();
    }

    public static interface IdStep {
        OrderNameStep withId(int id);
        OrderNameStep withoutId();
    }

    public static interface OrderNameStep {
        OrderNumberStep withOrderName(String orderName);
        OrderNumberStep withoutOrderName();
    }

    public static interface OrderNumberStep {
        TotalPriceStep withOrderNumber(int orderNumber);
    }

    public static interface TotalPriceStep {
        AssigneeStep withTotalPrice(Double totalPrice);
    }

    public static interface AssigneeStep {
        CustomerStep withAssignee(int assignee);
    }

    public static interface CustomerStep {
        OrderStatusReferenceStep withCustomer(int customer);
    }

    public static interface OrderStatusReferenceStep {
        BuildStep withOrderStatusReference(int orderStatusReference);
    }

    public static interface BuildStep {
        Order build();
    }

    public static class Builder implements IdStep, OrderNameStep, OrderNumberStep, TotalPriceStep, AssigneeStep,
            CustomerStep, OrderStatusReferenceStep, BuildStep {
        private int id;
        private String orderName;
        private int orderNumber;
        private Double totalPrice;
        private int assignee;
        private int customer;
        private int orderStatusReference;

        private Builder() {
        }

        public static IdStep order() {
            return new Builder();
        }

        @Override
        public OrderNameStep withId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public OrderNameStep withoutId() {
            return this;
        }

        @Override
        public OrderNumberStep withOrderName(String orderName) {
            this.orderName = orderName;
            return this;
        }

        @Override
        public OrderNumberStep withoutOrderName() {
            return this;
        }

        @Override
        public TotalPriceStep withOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        @Override
        public AssigneeStep withTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        @Override
        public CustomerStep withAssignee(int assignee) {
            this.assignee = assignee;
            return this;
        }

        @Override
        public OrderStatusReferenceStep withCustomer(int customer) {
            this.customer = customer;
            return this;
        }

        @Override
        public BuildStep withOrderStatusReference(int orderStatusReference) {
            this.orderStatusReference = orderStatusReference;
            return this;
        }

        @Override
        public Order build() {
            Order order = new Order();
            order.setId(this.id);
            order.setOrderName(this.orderName);
            order.setOrderNumber(this.orderNumber);
            order.setTotalPrice(this.totalPrice);
            order.setAssignee(this.assignee);
            order.setCustomer(this.customer);
            order.setOrderStatusReference(this.orderStatusReference);
            return order;
        }
    }
}
