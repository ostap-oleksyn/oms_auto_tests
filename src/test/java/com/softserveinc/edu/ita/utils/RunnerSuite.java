package com.softserveinc.edu.ita.utils;

import org.testng.SuiteRunner;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * class with configuring and running TestNG
 */
public class RunnerSuite {
    public static void main(final String[] testSuite) throws IOException, SAXException, ParserConfigurationException {
        final TestNG testNG = new TestNG();
        final List<Class> listenerClasses = Lists.newArrayList();
        InputStream xmlSuiteStream = null;

        listenerClasses.add(TestListener.class);
        listenerClasses.add(HTMLReporter.class);

        if (testSuite.length > 0) {
            switch (testSuite[0]) {
                case "administration":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream("suites/AdministrationPageTests.xml");
                    System.out.println("Running administration page tests:");
                    break;
                case "item_management":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream("suites/ItemManagementPageTests.xml");
                    System.out.println("Running item management page tests:");
                    break;
                case "login":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream("suites/LoginPageTests.xml");
                    System.out.println("Running login page tests:");
                    break;
                case "ordering":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream("suites/OrderingPageTests.xml");
                    System.out.println("Running ordering page tests:");
                    break;
                case "tabs_navigation":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream("suites/TabsNavigationTests.xml");
                    System.out.println("Running tabs navigation tests:");
                    break;
                case "user_info":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream("suites/UserInfoPageTests.xml");
                    System.out.println("Running user info page tests:");
                    break;
            }
        } else {
            xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream("suites/AllTests.xml");
            System.out.println("Running all tests:");
        }
        testNG.setXmlSuites((List<XmlSuite>) new Parser(xmlSuiteStream).parse());

        testNG.setListenerClasses(listenerClasses);
        testNG.setUseDefaultListeners(false);
        testNG.setParallel("classes");
        testNG.setThreadCount(5);
        testNG.run();
    }
}
