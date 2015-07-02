package com.softserveinc.edu.ita.enums.item_management_page;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

/**
 * Created by student on 7/2/2015.
 */
public enum ValueType {
    DOUBLE {
        @Override
        public Object getValue(WebElement element) {
            return Double.valueOf(element.getText());
        }
    },
    STRING {
        @Override
        public Object getValue(WebElement element) {
            return element.getText();
        }
    };

    abstract Object getValue(WebElement element);
}
