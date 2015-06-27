package com.softserveinc.edu.ita.domains;


import com.softserveinc.edu.ita.enums.OrderStatuses;

public class Order {

    private int id;
    private String orderName;
    private Double maxDiscount;
    private int orderNumber;
    private Double totalPrice;
    private int assignee;
    private int customer;
    private int orderStatusReference;
    private String deliveryDate;
    private String preferableDeliveryDate;
    @Deprecated
    private String Role;

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

    public Double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Double maxDiscount) {
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

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
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
        TotalPriceStep withoutOrderNumber();
    }

    public static interface TotalPriceStep {
        AssigneeStep withTotalPrice(Double totalPrice);
        AssigneeStep withoutTotalPrice();
    }

    public static interface AssigneeStep {
        CustomerStep withAssignee(int assignee);
        CustomerStep withoutAssignee();
    }

    public static interface CustomerStep {
        OrderStatusReferenceStep withCustomer(int customer);
        OrderStatusReferenceStep withoutCustomer();
    }

    public static interface OrderStatusReferenceStep {
        MaxDiscountStep withOrderStatusReference(int orderStatusReference);
        MaxDiscountStep withoutOrderStatusReference();
    }

    public static interface MaxDiscountStep {
        DeliveryDateStep withMaxDiscount(Double maxDiscount);
        DeliveryDateStep withoutMaxDiscount();
    }

    public static interface DeliveryDateStep {
        PreferableDeliveryDateStep withDeliveryDate(String deliveryDate);
        PreferableDeliveryDateStep withoutDeliveryDate();
    }

    public static interface PreferableDeliveryDateStep {
        BuildStep withPreferableDeliveryDate(String preferableDeliveryDate);
        BuildStep withoutPreferableDeliveryDate();
    }

    public static interface BuildStep {
        Order build();
    }


    public static class Builder implements IdStep, OrderNameStep, OrderNumberStep, TotalPriceStep, AssigneeStep, CustomerStep, OrderStatusReferenceStep, MaxDiscountStep, DeliveryDateStep, PreferableDeliveryDateStep, BuildStep {
        private int id;
        private String orderName;
        private int orderNumber;
        private Double totalPrice;
        private int assignee;
        private int customer;
        private int orderStatusReference;
        private Double maxDiscount;
        private String deliveryDate;
        private String preferableDeliveryDate;

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
        public TotalPriceStep withoutOrderNumber() {
            return this;
        }

        @Override
        public AssigneeStep withTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        @Override
        public AssigneeStep withoutTotalPrice() {
            return this;
        }

        @Override
        public CustomerStep withAssignee(int assignee) {
            this.assignee = assignee;
            return this;
        }

        @Override
        public CustomerStep withoutAssignee() {
            return this;
        }

        @Override
        public OrderStatusReferenceStep withCustomer(int customer) {
            this.customer = customer;
            return this;
        }

        @Override
        public OrderStatusReferenceStep withoutCustomer() {
            return this;
        }

        @Override
        public MaxDiscountStep withOrderStatusReference(int orderStatusReference) {
            this.orderStatusReference = orderStatusReference;
            return this;
        }

        @Override
        public MaxDiscountStep withoutOrderStatusReference() {
            return this;
        }

        @Override
        public DeliveryDateStep withMaxDiscount(Double maxDiscount) {
            this.maxDiscount = maxDiscount;
            return this;
        }

        @Override
        public DeliveryDateStep withoutMaxDiscount() {
            return this;
        }

        @Override
        public PreferableDeliveryDateStep withDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        @Override
        public PreferableDeliveryDateStep withoutDeliveryDate() {
            return this;
        }

        @Override
        public BuildStep withPreferableDeliveryDate(String preferableDeliveryDate) {
            this.preferableDeliveryDate = preferableDeliveryDate;
            return this;
        }

        @Override
        public BuildStep withoutPreferableDeliveryDate() {
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
            order.setMaxDiscount(this.maxDiscount);
            order.setDeliveryDate(this.deliveryDate);
            order.setPreferableDeliveryDate(this.preferableDeliveryDate);
            return order;
        }
    }
}
