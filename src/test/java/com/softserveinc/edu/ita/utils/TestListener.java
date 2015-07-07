package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.tests.TestRunner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Custom listener for taking and logging screenshots on failed tests.
 */
public final class TestListener extends TestListenerAdapter {

    /**
     * This method takes and logs a screenshot every time when a test fails.
     *
     * @param result - test result
     */
    @Override
    public void onTestFailure(final ITestResult result) {

        //Timeout to wait for the page to load completely, before taking a screenshot
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final Object currentClass = result.getInstance();
        final WebDriver driver = ((TestRunner) currentClass).getDriver();

        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss - ");
        final Date date = new Date();
        final String testMethodName = result.getMethod().getMethodName();
        final String currentTime = dateFormat.format(date);

        final File temporaryFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenShotFile;
        try {
            FileUtils.copyFile(temporaryFile, screenShotFile = new File(
                    String.format("test-output//html//screenShots//%s%s.png", currentTime, testMethodName)));
            final String screenShotFileName = screenShotFile.getAbsoluteFile().getName();

            Reporter.log(String.format("<br><a href='screenShots/%1$s'>Open screenshot</a>", screenShotFileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
