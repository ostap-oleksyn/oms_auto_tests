package com.softserveinc.edu.ita.domains;


public class Product {

    private int id;
    private int status;
    private String productName;
    private String productDescription;
    private Double productPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
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
