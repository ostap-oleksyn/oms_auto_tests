package com.softserveinc.edu.ita.utils;

import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

/**
 * Wrapper class for testng hard asserts.
 */
public final class LoggingAssert extends Assertion {

    public LoggingAssert() {
        super();
    }

    /**
     * This method logs successful hard asserts.
     */
    @Override
    public void onAssertSuccess(IAssert assertCommand) {
        Reporter.log(String.format("<br><font color='green'>PASSED</font> - %s", assertCommand.getMessage()));
    }

    /**
     * This method logs failed hard asserts.
     */
    @Override
    public void onAssertFailure(IAssert assertCommand, AssertionError ex) {
        Reporter.log(String.format("<br><font color='red'>FAILED - %s</font>", assertCommand.getMessage()));
    }
}