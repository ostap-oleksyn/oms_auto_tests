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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestRunner {

    protected WebDriver driver;
    final String driverPath = "src\\resources\\drivers\\";
    final protected LoggingAssert loggingAssert = new LoggingAssert();
    final protected LoggingSoftAssert loggingSoftAssert = new LoggingSoftAssert();

    protected TestRunner() {
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void startGrid() throws IOException, InterruptedException {
        final String remoteEnabled = PropertyLoader.getProperty("remote.enabled");

        if (remoteEnabled.equals("true")) {
            final int vmWait = Integer.parseInt(PropertyLoader.getProperty("vm.start.timeout.min", "virtualbox.properties")) * 60000;

            VirtualBoxUtil.startVirtualMachine();
            Thread.sleep(vmWait);

            VirtualBoxUtil.startHub();
            Thread.sleep(5000);

            VirtualBoxUtil.startNode();
            Thread.sleep(5000);
        }
    }

    @BeforeClass
    public void setUp() throws IOException {
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        BrowserTypes browserType;
        Platform platform;
        final String browser = PropertyLoader.getProperty("browser");
        final String remoteEnabled = PropertyLoader.getProperty("remote.enabled");
        final String remoteBrowserVersion = PropertyLoader.getProperty("remote.browser.version");
        final String remotePlatform = PropertyLoader.getProperty("remote.platform");
        final String hubUrl = PropertyLoader.getProperty("hub.url");

        if (remoteEnabled.equals("false")) {

            browserType = BrowserTypes.valueOf(browser.toUpperCase());

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
        } else {

            platform = Platform.valueOf(remotePlatform.toUpperCase());

            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(platform);
            capabilities.setBrowserName(browser.replace("_", " "));
            capabilities.setVersion(remoteBrowserVersion);

            driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(PropertyLoader.getProperty("oms.url"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void stopGrid() throws IOException {
        final String remoteEnabled = PropertyLoader.getProperty("remote.enabled");
        if (remoteEnabled.equals("true")) {
            VirtualBoxUtil.stopHub();
            VirtualBoxUtil.stopVirtualMachine();
        }
    }
}

