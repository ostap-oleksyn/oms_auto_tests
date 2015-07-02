package com.softserveinc.edu.ita.enums.item_management_page;

import org.openqa.selenium.WebElement;

/**
 * Enum makes possible to choose getValue method by type of returned value.
 * Different types have diverse way for sorting.
 */
public enum ValuesType {
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
