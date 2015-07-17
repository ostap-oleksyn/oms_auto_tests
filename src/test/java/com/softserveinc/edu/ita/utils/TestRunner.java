package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.enums.BrowserTypes;
import com.softserveinc.edu.ita.logging.LoggingAssert;
import com.softserveinc.edu.ita.logging.LoggingSoftAssert;
import org.apache.commons.lang3.SystemUtils;
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

        if ("true".equals(remoteEnabled)) {
            final double vmWait = Double.parseDouble(PropertyLoader.getProperty("vm.start.timeout.min", "virtualbox.properties")) * 60000;
            final int gridStartUpTime = Integer.parseInt(PropertyLoader.getProperty("grid.startup.time.sec", "virtualbox.properties")) * 1000;

            VirtualBoxUtil.startVirtualMachine();
            Thread.sleep((int) vmWait);

            VirtualBoxUtil.startHub();
            Thread.sleep(gridStartUpTime);

            VirtualBoxUtil.startNode();
            Thread.sleep(gridStartUpTime);
        }
    }

    @BeforeClass
    public void setUp() throws IOException {
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        final String driverPath = "src\\resources\\drivers\\";

        BrowserTypes browserType;
        Platform platform;
        final String browser = PropertyLoader.getProperty("browser");
        final String remoteEnabled = PropertyLoader.getProperty("remote.enabled");
        final String remoteBrowserVersion = PropertyLoader.getProperty("remote.browser.version");
        final String remotePlatform = PropertyLoader.getProperty("remote.platform");
        final String hubUrl = PropertyLoader.getProperty("hub.url");

        if ("false".equals(remoteEnabled)) {

            browserType = BrowserTypes.valueOf(browser.toUpperCase());

            switch (browserType) {
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                case CHROME:
                    if (SystemUtils.IS_OS_WINDOWS) {
                        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                    } else if (SystemUtils.IS_OS_LINUX) {
                        System.setProperty("webdriver.chrome.driver", driverPath.replace("\\","/") + "chromedriver");
                    }
                    driver = new ChromeDriver();
                    break;
                case INTERNET_EXPLORER:
                    if (SystemUtils.IS_OS_WINDOWS) {
                        System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
                    }else if (SystemUtils.IS_OS_LINUX){
                        throw new IllegalStateException("Internet explorer is not supported in Linux.");
                    }
                    driver = new InternetExplorerDriver();
                    break;
                case PHANTOM_JS:
                case HEADLESS:
                    if (SystemUtils.IS_OS_WINDOWS) {
                        System.setProperty("phantomjs.binary.path", driverPath + "phantomjs.exe");
                    } else if (SystemUtils.IS_OS_LINUX){
                        System.setProperty("phantomjs.binary.path", driverPath.replace("\\","/") + "phantomjs");
                    }
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
        if ("true".equals(remoteEnabled)) {
            VirtualBoxUtil.stopHub();
            VirtualBoxUtil.stopVirtualMachine();
        }
    }
}

