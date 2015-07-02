package com.softserveinc.edu.ita.domains;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import static com.softserveinc.edu.ita.utils.StringsGenerator.generateString;

public class Product {

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private int id;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private int status;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String productName;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String productDescription;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Double productPrice;

    public static Product createRandomProduct() {
        return Product.newBuilder()
                .withoutId()
                .withoutStatus()
                .withProductName(generateString("NameSymbols", 5, 13))
                .withProductDescription(generateString("NameSymbols", 10, 25))
                .withProductPrice(Double.parseDouble(generateString("Digits", 1, 3)))
                .build();
    }

    public static IdStep newBuilder() {
        return new Builder();
    }

    public interface IdStep {
        StatusStep withId(int id);

        StatusStep withoutId();

    }

    public interface StatusStep {
        ProductNameStep withStatus(int status);

        ProductNameStep withoutStatus();
    }

    public interface ProductNameStep {
        ProductDescriptionStep withProductName(String productName);
    }

    public interface ProductDescriptionStep {
        ProductPriceStep withProductDescription(String productDescription);
    }

    public interface ProductPriceStep {
        BuildStep withProductPrice(Double productPrice);
    }

    public interface BuildStep {
        Product build();
    }

    public static class Builder implements IdStep, StatusStep, ProductNameStep, ProductDescriptionStep, ProductPriceStep, BuildStep {
        private int id;
        private int status;
        private String productName;
        private String productDescription;
        private Double productPrice;

        private Builder() {
        }

        public static IdStep product() {
            return new Builder();
        }

        @Override
        public StatusStep withId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public StatusStep withoutId() {
            return this;
        }

        @Override
        public ProductNameStep withStatus(int status) {
            this.status = status;
            return this;
        }

        @Override
        public ProductNameStep withoutStatus() {
            return this;
        }

        @Override
        public ProductDescriptionStep withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        @Override
        public ProductPriceStep withProductDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        @Override
        public BuildStep withProductPrice(Double productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        @Override
        public Product build() {
            Product product = new Product();
            product.setId(this.id);
            product.setStatus(this.status);
            product.setProductName(this.productName);
            product.setProductDescription(this.productDescription);
            product.setProductPrice(this.productPrice);
            return product;
        }
    }
}
