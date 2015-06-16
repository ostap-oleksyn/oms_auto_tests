package com.softserveinc.edu.ita.domains;

/**
 * Class to initiate order using "Step Builder" Pattern.
 */
public class Order {
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

    private void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    private void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMaxDiscount() {
        return maxDiscount;
    }

    private void setMaxDiscount(String maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    private void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getRole() {
        return role;
    }

    private void setRole(String role) {
        this.role = role;
    }

    public interface OrderNameStep {
        TotalPriceStep withOrderName(String orderName);
    }

    public interface TotalPriceStep {
        MaxDiscountStep withTotalPrice(String totalPrice);
    }

    public interface MaxDiscountStep {
        DeliveryDateStep withMaxDiscount(String maxDiscount);
    }

    public interface DeliveryDateStep {
        StatusStep withDeliveryDate(String deliveryDate);
    }

    public interface StatusStep {
        AssigneeStep withStatus(String status);
    }

    public interface AssigneeStep {
        RoleStep withAssignee(String assignee);
    }

    public interface RoleStep {
        BuildStep withRole(String role);
    }

    public interface BuildStep {
        Order build();
    }

    public static class Builder implements OrderNameStep, TotalPriceStep, MaxDiscountStep, DeliveryDateStep, StatusStep, AssigneeStep, RoleStep, BuildStep {
        private String orderName;
        private String totalPrice;
        private String maxDiscount;
        private String deliveryDate;
        private String status;
        private String assignee;
        private String role;

        public Builder() {
        }

        public static OrderNameStep newOrders() {
            return new Builder();
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
        public Order build() {
            Order orders = new Order();
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