package com.softserveinc.edu.ita.utils;

/**
 * Class makes possible to set fields of the row belonging to "Ordering" table from application.
 */

public class OrderingsTableRow {
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

    private OrderingsTableRow(String orderName) {
        this.orderName = orderName.toLowerCase();
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    private void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice.toLowerCase();
    }

    public String getMaxDiscount() {
        return maxDiscount;
    }

    private void setMaxDiscount(String maxDiscount) {
        this.maxDiscount = maxDiscount.toLowerCase();
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    private void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate.toLowerCase();
    }

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status.toLowerCase();
    }

    public String getAssignee() {
        return assignee;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee.toLowerCase();
    }

    public String getRole() {
        return role;
    }

    private void setRole(String role) {
        this.role = role.toLowerCase();
    }

    public static OrderNameStep newBuilder() {
        return new Steps();
    }

    public interface OrderNameStep {
        TotalPriceStep setOrderName(String orderName);
    }

    public interface TotalPriceStep {
        MaxDiscountStep setTotalPrice(String totalPrice);
    }

    public interface MaxDiscountStep {
        DeliveryDateStep setMaxDiscount(String maxDiscount);
    }

    public interface DeliveryDateStep {
        StatusStep setDeliveryDate(String deliveryDate);
    }

    public interface StatusStep {
        AssigneeStep setStatus(String status);
    }

    public interface AssigneeStep {
        RoleStep setAssignee(String assignee);
    }

    public interface RoleStep {
        BuildStep setRole(String role);
    }

    public interface BuildStep {
        OrderingsTableRow build();
    }

    private static class Steps implements OrderNameStep, TotalPriceStep, MaxDiscountStep, DeliveryDateStep, StatusStep, AssigneeStep, RoleStep, BuildStep {
        private String orderName;
        private String totalPrice;
        private String maxDiscount;
        private String deliveryDate;
        private String status;
        private String assignee;
        private String role;

        public TotalPriceStep setOrderName(String orderName) {
            this.orderName = orderName;
            return this;
        }

        public MaxDiscountStep setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public DeliveryDateStep setMaxDiscount(String maxDiscount) {
            this.maxDiscount = maxDiscount;
            return this;
        }

        public StatusStep setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public AssigneeStep setStatus(String status) {
            this.status = status;
            return this;
        }

        public RoleStep setAssignee(String assignee) {
            this.assignee = assignee;
            return this;
        }

        public BuildStep setRole(String role) {
            this.role = role;
            return this;
        }

        public OrderingsTableRow build() {
            final OrderingsTableRow order = new OrderingsTableRow(orderName);
            order.setTotalPrice(totalPrice);
            order.setMaxDiscount(maxDiscount);
            if (deliveryDate != null) {
                order.setDeliveryDate(deliveryDate);
            } else {
                order.setDeliveryDate("");
            }
            order.setStatus(status);
            order.setAssignee(assignee);
            order.setRole(role);
            return order;
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "orderName='" + getOrderName() + '\'' +
                ", totalPrice='" + getTotalPrice() + '\'' +
                ", maxDiscount='" + getMaxDiscount() + '\'' +
                ", deliveryDate='" + getDeliveryDate() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", assignee='" + getAssignee() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}