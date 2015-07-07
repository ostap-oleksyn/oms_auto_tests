package com.softserveinc.edu.ita.domains;


import com.softserveinc.edu.ita.enums.OrderStatuses;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Order {

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private int id;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String orderName;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Double maxDiscount;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private int orderNumber;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Double totalPrice;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private int assignee;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private int customer;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private int orderStatusReference;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String deliveryDate;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String preferableDeliveryDate;

    @Deprecated
    private String Role;

    public String getStatusName() {
        return OrderStatuses.getStatusName(orderStatusReference);
    }

    public String getRole() {
        return Role;
    }

    public void setRole(final String role) {
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

    public interface IdStep {
        OrderNameStep withId(int id);

        OrderNameStep withoutId();
    }

    public interface OrderNameStep {
        OrderNumberStep withOrderName(String orderName);

        OrderNumberStep withoutOrderName();
    }

    public interface OrderNumberStep {
        TotalPriceStep withOrderNumber(int orderNumber);

        TotalPriceStep withoutOrderNumber();
    }

    public interface TotalPriceStep {
        AssigneeStep withTotalPrice(Double totalPrice);

        AssigneeStep withoutTotalPrice();
    }

    public interface AssigneeStep {
        CustomerStep withAssignee(int assignee);

        CustomerStep withoutAssignee();
    }

    public interface CustomerStep {
        OrderStatusReferenceStep withCustomer(int customer);

        OrderStatusReferenceStep withoutCustomer();
    }

    public interface OrderStatusReferenceStep {
        MaxDiscountStep withOrderStatusReference(int orderStatusReference);

        MaxDiscountStep withoutOrderStatusReference();
    }

    public interface MaxDiscountStep {
        DeliveryDateStep withMaxDiscount(Double maxDiscount);

        DeliveryDateStep withoutMaxDiscount();
    }

    public interface DeliveryDateStep {
        PreferableDeliveryDateStep withDeliveryDate(String deliveryDate);

        PreferableDeliveryDateStep withoutDeliveryDate();
    }

    public interface PreferableDeliveryDateStep {
        BuildStep withPreferableDeliveryDate(String preferableDeliveryDate);

        BuildStep withoutPreferableDeliveryDate();
    }

    public interface BuildStep {
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
        public OrderNameStep withId(final int id) {
            this.id = id;
            return this;
        }

        @Override
        public OrderNameStep withoutId() {
            return this;
        }

        @Override
        public OrderNumberStep withOrderName(final String orderName) {
            this.orderName = orderName;
            return this;
        }

        @Override
        public OrderNumberStep withoutOrderName() {
            return this;
        }

        @Override
        public TotalPriceStep withOrderNumber(final int orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        @Override
        public TotalPriceStep withoutOrderNumber() {
            return this;
        }

        @Override
        public AssigneeStep withTotalPrice(final Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        @Override
        public AssigneeStep withoutTotalPrice() {
            return this;
        }

        @Override
        public CustomerStep withAssignee(final int assignee) {
            this.assignee = assignee;
            return this;
        }

        @Override
        public CustomerStep withoutAssignee() {
            return this;
        }

        @Override
        public OrderStatusReferenceStep withCustomer(final int customer) {
            this.customer = customer;
            return this;
        }

        @Override
        public OrderStatusReferenceStep withoutCustomer() {
            return this;
        }

        @Override
        public MaxDiscountStep withOrderStatusReference(final int orderStatusReference) {
            this.orderStatusReference = orderStatusReference;
            return this;
        }

        @Override
        public MaxDiscountStep withoutOrderStatusReference() {
            return this;
        }

        @Override
        public DeliveryDateStep withMaxDiscount(final Double maxDiscount) {
            this.maxDiscount = maxDiscount;
            return this;
        }

        @Override
        public DeliveryDateStep withoutMaxDiscount() {
            return this;
        }

        @Override
        public PreferableDeliveryDateStep withDeliveryDate(final String deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        @Override
        public PreferableDeliveryDateStep withoutDeliveryDate() {
            return this;
        }

        @Override
        public BuildStep withPreferableDeliveryDate(final String preferableDeliveryDate) {
            this.preferableDeliveryDate = preferableDeliveryDate;
            return this;
        }

        @Override
        public BuildStep withoutPreferableDeliveryDate() {
            return this;
        }

        @Override
        public Order build() {
            final Order order = new Order();
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
