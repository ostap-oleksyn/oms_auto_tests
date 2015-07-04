package com.softserveinc.edu.ita.locators;


import com.softserveinc.edu.ita.interfaces.ILocator;
import org.openqa.selenium.By;

/**
 * This enum includes two type of locators:
 * the first type locators are used without preliminary preparation;
 * the second type locators can be used after advance modification.
 */
public enum UserInfoPageLocators implements ILocator {

    USER_INFO_LABEL(
            "Title User Info Table",
            LocatorsType.BY_XPATH,
            "//fieldset/legend"),
    FIRST_NAME_TITLE_LABEL(
            "Title first name label",
            LocatorsType.BY_XPATH,
            "(.//fieldset//td)[1]"),
    FIRST_NAME_LABEL(
            "User first name label",
            LocatorsType.BY_XPATH,
            "(.//fieldset//td)[2]"),
    LAST_NAME_LABEL(
            "User last name label",
            LocatorsType.BY_XPATH,
            "(.//fieldset//td)[4]"),
    CUSTOMER_TYPE_LABEL(
            "User customer type label",
            LocatorsType.BY_XPATH,
            "(.//fieldset//td)[6]"),
    USER_ROLE_LABEL(
            "User role label",
            LocatorsType.BY_XPATH,
            "(.//fieldset//td)[8]");

    private String name;
    private LocatorsType locatorsType;
    private String rawLocator;
    private String modifiedLocator;
    private By byLocator;

    //This constructor sets only 3 fields of object. The rest are prepared separately.
    UserInfoPageLocators(final String name, final LocatorsType locatorsType, final String rawLocator) {
        this.name = name;
        this.locatorsType = locatorsType;
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

    /**
     * This method prepares locator using additional parameter
     * by means of so called "string-format" method.
     */
    public UserInfoPageLocators modify(final String parameter) {
        this.modifiedLocator = String.format(this.rawLocator, parameter);
        return this;
    }

    @Override
    //This method converts locator into "By" format.
    public By getBy() {
        //This block of code is used to leave raw locator intact giving a possibility to use parameterized locator again.
        if (this.modifiedLocator == null) {
            this.byLocator = this.locatorsType.getBy(this.rawLocator);
        } else {
            this.byLocator = this.locatorsType.getBy(this.modifiedLocator);
        }
        return this.byLocator;
    }
}
