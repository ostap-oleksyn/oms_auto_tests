package com.softserveinc.edu.ita.pageobjects;


import com.softserveinc.edu.ita.interfaces.ILocator;
import com.softserveinc.edu.ita.locators.CommonLocators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public abstract class PageObjectBase {

    protected WebDriver driver;

    public PageObjectBase(WebDriver driver) {
        this.driver = driver;
    }

    public String getElementText(ILocator locator) {
        return driver.findElement(locator.getBy()).getText();
    }

    public boolean isElementDisplayed(ILocator locator) {
        return driver.findElements(locator.getBy()).size() != 0;
    }

    public boolean isElementDisplayed(By elementLocator) {
        return driver.findElements(elementLocator).size() != 0;
    }

    public UserInfoPage clickUserInfoTab() {
        click(CommonLocators.USER_INFO_TAB);
        return new UserInfoPage(driver);
    }

    /**
     * This method clicks on an WebElement and logs the action.
     *
     * @param locator - WebElement locator
     */
    public void click(ILocator locator) {
        driver.findElement(locator.getBy()).click();
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Clicked <b>%s</b>", locator.getName()));
    }

    /**
     * This sends text into input fields and logs the action.
     *
     * @param locator - WebElement locator
     * @param text    - input text
     */
    public void sendKeys(ILocator locator, String text) {
        driver.findElement(locator.getBy()).clear();
        driver.findElement(locator.getBy()).sendKeys(text);
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Typed '%s' in <b>%s</b>", text, locator.getName()));
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Reporter.log(String.format("INFO   - Accepted alert: %s", alertText));
    }

    public boolean isElementEnabled(ILocator locator) {
        return driver.findElement(locator.getBy()).isEnabled();
    }

    public String getElementAttribute(ILocator locator, String attribute) {
        return driver.findElement(locator.getBy()).getAttribute(attribute);
    }

}

