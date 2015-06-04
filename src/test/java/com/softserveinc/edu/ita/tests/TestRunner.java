package com.softserveinc.edu.ita.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestRunner {

    protected WebDriver driver;

    protected TestRunner() {
    }

    private final String OMS_URL = "localhost:8080/OMS";

    @BeforeClass
    public void setUp() {
        // TODO add multiple browsers support
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(OMS_URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

