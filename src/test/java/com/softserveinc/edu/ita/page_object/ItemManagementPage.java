package com.softserveinc.edu.ita.page_object;

import com.softserveinc.edu.ita.locators.ItemManagementPageLocators;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ItemManagementPage extends LogOutBase {

    public ItemManagementPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getItemManagementTab() {
        return driver.findElement(UserInfoPageLocators.USER_ITEM_MANAGEMENT_TAB);
    }

    public WebElement getAddProductLink() {
        return driver.findElement(ItemManagementPageLocators.ADD_PRODUCT_LINK);
    }
}
