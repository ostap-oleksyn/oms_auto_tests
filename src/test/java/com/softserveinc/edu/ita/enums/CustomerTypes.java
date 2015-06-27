package com.softserveinc.edu.ita.enums;

/**
 * Enum with customer types. Can generate random values
 */
public enum CustomerTypes {
    STANDARD(1, "Standart"),
    SILVER(2, "Silver"),
    GOLD(3, "Gold"),
    PLATINUM(4, "Platinum");

    private int customerTypeReference;
    private String customerTypeName;

    CustomerTypes(int customerTypeReference, String customerTypeName) {
        this.customerTypeReference = customerTypeReference;
        this.customerTypeName = customerTypeName;
    }

    @Override
    public String toString() {
        return "CustomerTypes{" +
                "customerTypeReference=" + customerTypeReference +
                ", customerTypeName='" + customerTypeName + '\'' +
                '}';
    }

    public int getCustomerTypeReference() {
        return customerTypeReference;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public static String getCustomerTypeName(int customerTypeReference) {

        for (CustomerTypes customerType: CustomerTypes.values()) {
            if (customerType.customerTypeReference == customerTypeReference) {
                return customerType.getCustomerTypeName();
            }
        }

        return null;
    }

}
