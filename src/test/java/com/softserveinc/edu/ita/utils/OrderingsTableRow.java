package com.softserveinc.edu.ita.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Class makes possible to set fields of the row belonging to "Ordering" table from application.
 */
public class OrderingsTableRow {
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String orderName;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String totalPrice;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String maxDiscount;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String deliveryDate;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String status;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String assignee;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String role;

    public OrderingsTableRow(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "OrderingsTableRow{" +
                "orderName='" + getOrderName() + '\'' +
                ", totalPrice='" + getTotalPrice() + '\'' +
                ", maxDiscount='" + getMaxDiscount() + '\'' +
                ", deliveryDate='" + getDeliveryDate() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", assignee='" + getAssignee() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }

    private OrderingsTableRow() {
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
            final OrderingsTableRow orderingsTableRow = new OrderingsTableRow(orderName.toLowerCase());
            orderingsTableRow.setTotalPrice(totalPrice.toLowerCase());
            orderingsTableRow.setMaxDiscount(maxDiscount.toLowerCase());
            if (deliveryDate != null) {
                orderingsTableRow.setDeliveryDate(deliveryDate.toLowerCase());
            } else {
                orderingsTableRow.setDeliveryDate("");
            }
            orderingsTableRow.setStatus(status.toLowerCase());
            orderingsTableRow.setAssignee(assignee.toLowerCase());
            orderingsTableRow.setRole(role.toLowerCase());
            return orderingsTableRow;
        }
    }
}