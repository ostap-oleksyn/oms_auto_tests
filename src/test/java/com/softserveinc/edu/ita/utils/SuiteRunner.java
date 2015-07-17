package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.enums.TestSuites;
import org.testng.SuiteRunner;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static com.softserveinc.edu.ita.enums.TestSuites.ALL;

/**
 * class with configuring and running TestNG
 */
public class SuiteRunner {
    public static void main(final String[] parameter) throws IOException, SAXException, ParserConfigurationException {
        final TestNG testNG = new TestNG();
        final List<Class> listenerClasses = Arrays.asList(TestListener.class, HTMLReporter.class);

        TestSuites testSuite = ALL;
        if (parameter.length > 0) {
            try {
                testSuite = TestSuites.valueOf(parameter[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal argument specified. Available(case insensitive): Administration, Item_management, Login, Ordering, Navigation, User_info, All");
                System.exit(0);
            }
        }
        System.out.println("Starting " + testSuite.getSuiteName() + " test suite: ");
        final InputStream xmlSuiteStream = getXmlSuiteStream(testSuite);

        testNG.setXmlSuites((List<XmlSuite>) new Parser(xmlSuiteStream).parse());
        testNG.setListenerClasses(listenerClasses);
        testNG.setUseDefaultListeners(false);
        testNG.setParallel("classes");
        testNG.setThreadCount(5);
        testNG.run();
    }

    public static InputStream getXmlSuiteStream(final TestSuites testSuite) {
        return org.testng.SuiteRunner.class.getClassLoader().getResourceAsStream(testSuite.getSuitePath());
    }
}
