package com.softserveinc.edu.ita.tests;

import com.softserveinc.edu.ita.enums.BrowserTypes;
import com.softserveinc.edu.ita.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestRunner {

    protected WebDriver driver;

    protected TestRunner() {
    }

    @BeforeClass
    public void setUp() throws IOException {
        BrowserTypes browserType;
        String configProperty = PropertyLoader.getProperty("browser");

        try {
            browserType = BrowserTypes.valueOf(configProperty.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Illegal browser type specified: " + configProperty);
        }
        String driverPath = "src\\resources\\drivers\\";

        // TODO add factory
        switch (browserType) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case INTERNET_EXPLORER:
                System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            case PHANTOM_JS:
            case HEADLESS:
                driver = new PhantomJSDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(PropertyLoader.getProperty("oms.url"));
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}

