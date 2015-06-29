package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * This enum includes two type of locators:
 * the first type locators are used without preliminary preparation;
 * the second type locators can be used after advance modification.
 */
public enum ItemManagementPageLocators implements ILocator {
    //TODO refactor into not using text label inside the locator
    ADD_PRODUCT_LINK(
            "Add product link",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='list']/a[contains(text(), 'Add Product')]"),
    SUPERVISOR_APPOINTED_LABEL(
            "Supervisor appointed Info label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='list']/h2"),
    SUPERVISOR_FILTER_LABEL(
            "Filter label",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='searchForm']/label"),
    SELECTED_FILTER(
            "Selected filter",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='field']//option[@selected='selected']"),
    SEARCH_FIELD(
            "Search field",
            SeleniumByMethods.BY_XPATH,
            ".//*[@id='searchField']");

    private String name;
    private SeleniumByMethods seleniumByMethod;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    ItemManagementPageLocators(String name, SeleniumByMethods seleniumByMethod, String rawLocator) {
        this.name = name;
        this.seleniumByMethod = seleniumByMethod;
        this.rawLocator = rawLocator;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //This method prepares locator using additional parameter by means of so called "string-format" method.
    public ItemManagementPageLocators modify(String parameter) {
        this.name = parameter;
        this.modifiedLocator = String.format(this.rawLocator, parameter);
        return this;
    }

    @Override
    //This method converts locator into "By" format.
    public By getBy() {
        //This block of code is used to leave raw locator intact giving a possibility to use parameterized locator again.
        if (this.modifiedLocator == null) {
            this.byLocator = this.seleniumByMethod.getBy(this.rawLocator);
        } else {
            this.byLocator = this.seleniumByMethod.getBy(this.modifiedLocator);
        }
        return this.byLocator;
    }
}
