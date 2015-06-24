package com.softserveinc.edu.ita.domains;


import java.util.Date;

public class Order {

    private int id;
    private String orderName;
    private int orderNumber;
    private double totalPrice;
    private int assignee;
    private int customer;
    private int orderStatusReference;

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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", orderNumber=" + orderNumber +
                ", totalPrice=" + totalPrice +
                ", assignee=" + assignee +
                ", customer=" + customer +
                ", orderStatusReference=" + orderStatusReference +
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
        AssigneeStep withTotalPrice(double totalPrice);
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
        private double totalPrice;
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
        public AssigneeStep withTotalPrice(double totalPrice) {
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
