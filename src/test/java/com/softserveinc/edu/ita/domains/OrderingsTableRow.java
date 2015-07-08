package com.softserveinc.edu.ita.domains;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Class is for setting row fields from "Ordering" table in OMS.
 */
public class OrderingsTableRow {
    @Getter @Setter(AccessLevel.PRIVATE) private String orderName;
    @Getter @Setter(AccessLevel.PRIVATE) private String totalPrice;
    @Getter @Setter(AccessLevel.PRIVATE) private String maxDiscount;
    @Getter @Setter(AccessLevel.PRIVATE) private String deliveryDate;
    @Getter @Setter(AccessLevel.PRIVATE) private String status;
    @Getter @Setter(AccessLevel.PRIVATE) private String assignee;
    @Getter @Setter(AccessLevel.PRIVATE) private String role;

    public OrderingsTableRow(final String orderName) {
        this.orderName = orderName.toLowerCase();
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

        public TotalPriceStep setOrderName(final String orderName) {
            this.orderName = orderName.toLowerCase();
            return this;
        }

        public MaxDiscountStep setTotalPrice(final String totalPrice) {
            this.totalPrice = totalPrice.toLowerCase();
            return this;
        }

        public DeliveryDateStep setMaxDiscount(final String maxDiscount) {
            this.maxDiscount = maxDiscount.toLowerCase();
            return this;
        }

        public StatusStep setDeliveryDate(final String deliveryDate) {
            this.deliveryDate = deliveryDate.toLowerCase();
            return this;
        }

        public AssigneeStep setStatus(final String status) {
            this.status = status.toLowerCase();
            return this;
        }

        public RoleStep setAssignee(final String assignee) {
            this.assignee = assignee.toLowerCase();
            return this;
        }

        public BuildStep setRole(final String role) {
            this.role = role.toLowerCase();
            return this;
        }

        public OrderingsTableRow build() {
            final OrderingsTableRow orderingsTableRow = new OrderingsTableRow(orderName);
            if (totalPrice != null) {
                orderingsTableRow.setTotalPrice(totalPrice);
            } else {
                orderingsTableRow.setTotalPrice("");
            }
            if (maxDiscount != null) {
                orderingsTableRow.setMaxDiscount(maxDiscount);
            } else {
                orderingsTableRow.setMaxDiscount("");
            }
            if (deliveryDate != null) {
                orderingsTableRow.setDeliveryDate(deliveryDate);
            } else {
                orderingsTableRow.setDeliveryDate("");
            }
            if (status != null) {
                orderingsTableRow.setStatus(status);
            } else {
                orderingsTableRow.setStatus("");
            }
            if (assignee != null) {
                orderingsTableRow.setAssignee(assignee);
            } else {
                orderingsTableRow.setAssignee("");
            }
            if (role != null) {
                orderingsTableRow.setRole(role);
            } else {
                orderingsTableRow.setRole("");
            }
            return orderingsTableRow;
        }
    }
}
