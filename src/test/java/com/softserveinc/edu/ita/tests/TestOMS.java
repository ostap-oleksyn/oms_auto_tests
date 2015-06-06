package com.softserveinc.edu.ita.tests;

import java.io.IOException;

import org.testng.annotations.Test;

public class TestOMS extends TestRunner {

    private final String OMS_URL = "localhost:8080/OMS";

    @Test
    public void testMethod() throws IOException {
        driver.get(OMS_URL);
    }
}
