package com.softserveinc.edu.ita.pageobjects;


import com.softserveinc.edu.ita.interfaces.ILocator;
import com.softserveinc.edu.ita.locators.CommonLocators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public abstract class PageObjectBase {

    protected WebDriver driver;

    public PageObjectBase(final WebDriver driver) {
        this.driver = driver;
    }

    public String getElementText(final ILocator locator) {
        return driver.findElement(locator.getBy()).getText();
    }

    public boolean isElementDisplayed(final ILocator locator) {
        return driver.findElements(locator.getBy()).size() != 0;
    }

    public boolean isElementDisplayed(final By elementLocator) {
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
    public void click(final ILocator locator) {
        driver.findElement(locator.getBy()).click();
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Clicked <b>%s</b>", locator.getName()));
    }

    /**
     * This sends text into input fields and logs the action.
     *
     * @param locator - WebElement locator
     * @param text    - input text
     */
    public void sendKeys(final ILocator locator, final String text) {
        driver.findElement(locator.getBy()).clear();
        driver.findElement(locator.getBy()).sendKeys(text);
        Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Typed '%s' in <b>%s</b>", text, locator.getName()));
    }

    public void acceptAlert() {
        final Alert alert = driver.switchTo().alert();
        final String alertText = alert.getText();
        alert.accept();
        Reporter.log(String.format("INFO   - Accepted alert: %s", alertText));
    }

    public boolean isElementEnabled(final ILocator locator) {
        return driver.findElement(locator.getBy()).isEnabled();
    }

    public String getElementAttribute(final ILocator locator, final String attribute) {
        return driver.findElement(locator.getBy()).getAttribute(attribute);
    }

}

