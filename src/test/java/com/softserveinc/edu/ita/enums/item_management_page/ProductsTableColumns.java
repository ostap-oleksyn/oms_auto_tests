package com.softserveinc.edu.ita.enums.item_management_page;

import org.openqa.selenium.WebElement;

import java.util.Objects;


/**
 * Created by true on 01.07.2015.
 */
public enum ProductsTableColumns {
    NAME("Name", ValueType.STRING),
    DESCRIPTION("Description", ValueType.STRING),
    PRICE("Price", ValueType.DOUBLE);

    private String name;
    private ValueType type;

    ProductsTableColumns(String name, ValueType type) {
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

    public ValueType getType() {
        return this.type;
    }

    public Object getValue(WebElement webElement){
        return this.type.getValue(webElement);
    }
}
