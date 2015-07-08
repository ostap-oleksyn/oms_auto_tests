package com.softserveinc.edu.ita.utils;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runner {
    public void runTests(Map<String, String> testNGParameters) {
        TestNG testNG = new TestNG();

        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("MySuit");

        XmlTest xmlTest = new XmlTest();
        xmlTest.setName("MyTest");

        xmlTest.setParameters(testNGParameters);

        List<XmlClass> xmlClasses = new ArrayList<>();
        xmlClasses.add(new XmlClass("ordering_page.FilterTest"));
    }
}
