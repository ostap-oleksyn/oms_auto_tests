package com.softserveinc.edu.ita.dao_jdbc.domains;

/**
 * Created by student on 6/10/2015.
 */
public class Orders {
    private String orderName;
    private String totalPrice;
    private String maxDiscount;
    private String deliveryDate;
    private String status;
    private String assignee;
    private String role;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(String maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static interface OrderNameStep {
        TotalPriceStep withOrderName(String orderName);
    }

    public static interface TotalPriceStep {
        MaxDiscountStep withTotalPrice(String totalPrice);
    }

    public static interface MaxDiscountStep {
        DeliveryDateStep withMaxDiscount(String maxDiscount);
    }

    public static interface DeliveryDateStep {
        StatusStep withDeliveryDate(String deliveryDate);
    }

    public static interface StatusStep {
        AssigneeStep withStatus(String status);
    }

    public static interface AssigneeStep {
        RoleStep withAssignee(String assignee);
    }

    public static interface RoleStep {
        BuildStep withRole(String role);
    }

    public static interface BuildStep {
        Orders build();
    }

    public static class OrdersStepBuilder implements OrderNameStep, TotalPriceStep, MaxDiscountStep, DeliveryDateStep, StatusStep, AssigneeStep, RoleStep, BuildStep {
        private String orderName;
        private String totalPrice;
        private String maxDiscount;
        private String deliveryDate;
        private String status;
        private String assignee;
        private String role;

        public OrdersStepBuilder() {
        }

        public static OrderNameStep newOrders() {
            return new OrdersStepBuilder();
        }

        @Override
        public TotalPriceStep withOrderName(String orderName) {
            this.orderName = orderName;
            return this;
        }

        @Override
        public MaxDiscountStep withTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        @Override
        public DeliveryDateStep withMaxDiscount(String maxDiscount) {
            this.maxDiscount = maxDiscount;
            return this;
        }

        @Override
        public StatusStep withDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        @Override
        public AssigneeStep withStatus(String status) {
            this.status = status;
            return this;
        }

        @Override
        public RoleStep withAssignee(String assignee) {
            this.assignee = assignee;
            return this;
        }

        @Override
        public BuildStep withRole(String role) {
            this.role = role;
            return this;
        }

        @Override
        public Orders build() {
            Orders orders = new Orders();
            orders.setOrderName(this.orderName);
            orders.setTotalPrice(this.totalPrice);
            orders.setMaxDiscount(this.maxDiscount);
            orders.setDeliveryDate(this.deliveryDate);
            orders.setStatus(this.status);
            orders.setAssignee(this.assignee);
            orders.setRole(this.role);
            return orders;
        }
    }
}