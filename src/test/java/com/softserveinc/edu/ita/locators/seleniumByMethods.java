package com.softserveinc.edu.ita.locators;

import org.openqa.selenium.By;

/**
 * Created by true on 27.06.2015.
 */
public enum SeleniumByMethods {
    BY_CLASS_NAME{
        @Override
        public By getBy(String locator) {
            return By.className(locator);
        }
    },
    BY_CSS_SELECTOR {
        @Override
        public By getBy(String locator) {
            return By.cssSelector(locator);
        }
    },
    BY_ID {
        @Override
        public By getBy(String locator) {
            return By.id(locator);
        }
    },
    BY_LINK_TEXT {
        @Override
        public By getBy(String locator) {
            return By.linkText(locator);
        }
    },
    BY_NAME {
        @Override
        public By getBy(String locator) {
            return By.name(locator);
        }
    },
    BY_TAG_NAME {
        @Override
        public By getBy(String locator) {
            return By.tagName(locator);
        }
    },
    BY_XPATH {
        @Override
        public By getBy(String locator) {
            return By.xpath(locator);
        }
    };
    abstract By getBy(String locator);
}
