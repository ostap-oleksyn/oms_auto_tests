package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.enums.BrowserTypes;
import com.softserveinc.edu.ita.logging.LoggingAssert;
import com.softserveinc.edu.ita.logging.LoggingSoftAssert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestRunner {

    protected WebDriver driver;
    final protected LoggingAssert loggingAssert = new LoggingAssert();
    final protected LoggingSoftAssert loggingSoftAssert = new LoggingSoftAssert();
    private DesiredCapabilities capabilities;

    protected TestRunner() {
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() throws IOException {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        BrowserTypes browserType;
        final String configProperty = PropertyLoader.getProperty("browser");
        final String hubUrl = PropertyLoader.getProperty("hub.url");

        try {
            browserType = BrowserTypes.valueOf(configProperty.toUpperCase());

        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Illegal browser type specified: " + configProperty);
        }
        final String driverPath = "src\\resources\\drivers\\";

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
            case FIREFOX_REMOTE_WINDOWS:
                capabilities = new DesiredCapabilities();
                capabilities.setPlatform(Platform.WINDOWS);
                capabilities.setBrowserName("firefox");
                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
                break;
            case CHROME_REMOTE_WINDOWS:
                capabilities = new DesiredCapabilities();
                capabilities.setPlatform(Platform.WINDOWS);
                capabilities.setBrowserName("chrome");
                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
                break;
            case IE_REMOTE_WINDOWS:
                capabilities = new DesiredCapabilities();
                capabilities.setPlatform(Platform.WINDOWS);
                capabilities.setBrowserName("internet explorer");
                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
                break;
            case FIREFOX_REMOTE_LINUX:
                capabilities = new DesiredCapabilities();
                capabilities.setPlatform(Platform.LINUX);
                capabilities.setBrowserName("firefox");
                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
                break;
            case CHROME_REMOTE_LINUX:
                capabilities = new DesiredCapabilities();
                capabilities.setPlatform(Platform.LINUX);
                capabilities.setBrowserName("chrome");
                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
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
        driver.quit();
    }
}

