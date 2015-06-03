package com.softserveinc.edu.ita;


import com.softserveinc.edu.ita.dataproviders.DataProviders;
import com.softserveinc.edu.ita.enums.Roles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("localhost:8080/OMS/");
    }

    public void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "getAdministratorCredentials", dataProviderClass = DataProviders.class)
    public void loginTest(String login, String pass) {
        driver.findElement(By.xpath(".//*[@id='edit']/fieldset/form/table/tbody/tr[1]/td[2]/input"))
                .sendKeys(login);
        driver.findElement(By.xpath(".//*[@id='edit']//input[@name = 'j_password']"))
                .sendKeys(pass);
        driver.findElement(By.xpath(".//*[@id='edit']//input[@name = 'submit']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("(.//*[@id='content']//td)[8]"))
                .getText()
                .equals(Roles.ADMINISTRATOR.toString()));
        driver.findElement(By.xpath(".//*[@id='nav']//img[@alt = 'logout']")).click();


    }
}
