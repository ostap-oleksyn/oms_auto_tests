package com.softserveinc.edu.ita.enums;

import java.util.Random;

/**
 * Enum with customer types. Can generate random values
 */
public enum CustomerTypes {
    STANDARD(1, "Standart"),
    SILVER(2, "Silver"),
    GOLD(3, "Gold"),
    PLATINUM(4, "Platinum"),
    ALL(5, "All");

    private int customerTypeRef;
    private String customerTypeName;

    CustomerTypes(int customerTypeRef, String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    @Override
    public String toString() {
        return "CustomerTypes{" +
                "customerTypeRef=" + customerTypeRef +
                ", customerTypeName='" + customerTypeName + '\'' +
                '}';
    }

    public int getCustomerTypeRef() {
        return customerTypeRef;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public static String getCustomerTypeName(int customerTypeRef) {

        for (CustomerTypes customerType: CustomerTypes.values()) {
            if (customerType.customerTypeRef == customerTypeRef) {
                return customerType.getCustomerTypeName();
            }
        }

        return null;
    }

}
