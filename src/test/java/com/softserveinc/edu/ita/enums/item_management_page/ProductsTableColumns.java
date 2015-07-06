package com.softserveinc.edu.ita.enums.item_management_page;

import org.openqa.selenium.WebElement;


/**
 * Enum to limit possible columns of product's table.
 */
public enum ProductsTableColumns {
    NAME("Name", ValuesType.STRING),
    DESCRIPTION("Description", ValuesType.STRING),
    PRICE("Price", ValuesType.DOUBLE);

    private String name;
    private ValuesType type;

    ProductsTableColumns(String name, ValuesType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public ValuesType getType() {
        return this.type;
    }

    public Object getValue(WebElement webElement){
        return this.type.getValue(webElement);
    }
}
