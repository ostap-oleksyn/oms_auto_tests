package com.softserveinc.edu.ita.domains;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Class represents the OrderItem model
 */
public class OrderItem {

    @Getter @Setter(AccessLevel.PRIVATE) private int id;
    @Getter @Setter(AccessLevel.PRIVATE) private Double cost;
    @Getter @Setter(AccessLevel.PRIVATE) private Double itemPrice;
    @Getter @Setter(AccessLevel.PRIVATE) private int quantity;
    @Getter @Setter(AccessLevel.PRIVATE) private int dimensionReference;
    @Getter @Setter(AccessLevel.PRIVATE) private int orderReference;
    @Getter @Setter(AccessLevel.PRIVATE) private int productReference;

    private OrderItem() {
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", cost=" + cost +
                ", itemPrice=" + itemPrice +
                ", quantity=" + quantity +
                ", dimensionReference=" + dimensionReference +
                ", orderReference=" + orderReference +
                ", productReference=" + productReference +
                '}';
    }

    public static IdStep newBuilder() {
        return new Builder();
    }

    public interface IdStep {
        CostStep withId(int id);
    }

    public interface CostStep {
        ItemPriceStep withCost(Double cost);
    }

    public interface ItemPriceStep {
        QuantityStep withItemPrice(Double itemPrice);
    }

    public interface QuantityStep {
        DimensionReferenceStep withQuantity(int quantity);
    }

    public interface DimensionReferenceStep {
        OrderReferenceStep withDimensionReference(int dimensionReference);
    }

    public interface OrderReferenceStep {
        ProductReferenceStep withOrderReference(int orderReference);
    }

    public interface ProductReferenceStep {
        BuildStep withProductReference(int productReference);
    }

    public interface BuildStep {
        OrderItem build();
    }


    public static class Builder implements IdStep, CostStep, ItemPriceStep, QuantityStep, DimensionReferenceStep, OrderReferenceStep, ProductReferenceStep, BuildStep {
        private int id;
        private Double cost;
        private Double itemPrice;
        private int quantity;
        private int dimensionReference;
        private int orderReference;
        private int productReference;

        private Builder() {
        }

        public static IdStep orderItem() {
            return new Builder();
        }

        @Override
        public CostStep withId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public ItemPriceStep withCost(Double cost) {
            this.cost = cost;
            return this;
        }

        @Override
        public QuantityStep withItemPrice(Double itemPrice) {
            this.itemPrice = itemPrice;
            return this;
        }

        @Override
        public DimensionReferenceStep withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        @Override
        public OrderReferenceStep withDimensionReference(int dimensionReference) {
            this.dimensionReference = dimensionReference;
            return this;
        }

        @Override
        public ProductReferenceStep withOrderReference(int orderReference) {
            this.orderReference = orderReference;
            return this;
        }

        @Override
        public BuildStep withProductReference(int productReference) {
            this.productReference = productReference;
            return this;
        }

        @Override
        public OrderItem build() {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(this.id);
            orderItem.setCost(this.cost);
            orderItem.setItemPrice(this.itemPrice);
            orderItem.setQuantity(this.quantity);
            orderItem.setDimensionReference(this.dimensionReference);
            orderItem.setOrderReference(this.orderReference);
            orderItem.setProductReference(this.productReference);
            return orderItem;
        }
    }
}
