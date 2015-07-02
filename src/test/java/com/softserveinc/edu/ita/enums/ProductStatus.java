package com.softserveinc.edu.ita.enums;


public enum ProductStatus {
    ACTIVE(1),
    INACTIVE(0);

    private int status;

    ProductStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ProductStatus{" +
                "status=" + status +
                '}';
    }
}
