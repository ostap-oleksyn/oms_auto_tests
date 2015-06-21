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

    private Order (String orderName) {
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
        TotalPriceStep orderName(String orderName);
    }

    public interface TotalPriceStep {
        MaxDiscountStep totalPrice(String totalPrice);
    }

    public interface MaxDiscountStep {
        DeliveryDateStep maxDiscount(String maxDiscount);
    }

    public interface DeliveryDateStep {
        StatusStep deliveryDate(String deliveryDate);
    }

    public interface StatusStep {
        AssigneeStep status(String status);
    }

    public interface AssigneeStep {
        RoleStep assignee(String assignee);
    }

    public interface RoleStep {
        BuildStep role(String role);
    }

    public interface BuildStep {
        Order build();
    }

    private static class Steps implements OrderNameStep, TotalPriceStep, MaxDiscountStep, DeliveryDateStep, StatusStep, AssigneeStep, RoleStep, BuildStep {
        private String orderName;
        private String totalPrice;
        private String maxDiscount;
        private String deliveryDate;
        private String status;
        private String assignee;
        private String role;

        public TotalPriceStep orderName(String orderName) {
            this.orderName = orderName;
            return this;
        }

        public MaxDiscountStep totalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public DeliveryDateStep maxDiscount(String maxDiscount) {
            this.maxDiscount = maxDiscount;
            return this;
        }

        public StatusStep deliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public AssigneeStep status(String status) {
            this.status = status;
            return this;
        }

        public RoleStep assignee(String assignee) {
            this.assignee = assignee;
            return this;
        }

        public BuildStep role(String role) {
            this.role = role;
            return this;
        }

        public Order build(){
            final Order order = new Order(orderName);
            order.setTotalPrice(totalPrice);
            order.setMaxDiscount(maxDiscount);
            order.setDeliveryDate(deliveryDate);
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