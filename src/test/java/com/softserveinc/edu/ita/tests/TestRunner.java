package com.softserveinc.edu.ita.tests;


import com.softserveinc.edu.ita.enums.BrowserTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestRunner {

    protected WebDriver driver;

    protected TestRunner() {
    }

    private final String OMS_URL = "localhost:8080/OMS";


    @BeforeClass
    public void setUp() throws IOException {

        //TODO refactor properties into separate class
        final Properties properties = new Properties();

        properties.load(new FileInputStream("src\\resources\\config.properties"));

        BrowserTypes browserType;
        try {
            browserType = BrowserTypes.valueOf(properties.getProperty("browser").toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Illegal browser type specified: " + properties.getProperty("browser"));
        }

        switch (browserType) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case INTERNET_EXPLORER:
                System.setProperty("webdriver.ie.driver", "src\\resources\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
           // case PHANTOM_JS:
           // case HEADLESS:
             //   DesiredCapabilities capabilities = new DesiredCapabilities();
              //  capabilities.setCapability("phantomjs.binary.path", "src\\resources\\...");
              //  driver = new PhantomJSDriver(capabilities);
              //  break;
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(OMS_URL);
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

