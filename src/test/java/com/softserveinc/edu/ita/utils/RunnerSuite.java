package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.enums.TestSuites;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.edu.ita.enums.TestSuites.*;

/**
 * class with configuring and running TestNG
 */
public class RunnerSuite {
    public static void main(final String[] testSuite) throws IOException, SAXException, ParserConfigurationException {
        final TestNG testNG = new TestNG();
        final List<Class> listenerClasses = Lists.newArrayList();
        InputStream xmlSuiteStream = null;

        final List<String> testSuitesNameList = new ArrayList<>();
        testSuitesNameList.add(ADMINISTRATION.getSuiteName());
        testSuitesNameList.add(ITEM_MANAGEMENT.getSuiteName());
        testSuitesNameList.add(LOGIN.getSuiteName());
        testSuitesNameList.add(ORDERING.getSuiteName());
        testSuitesNameList.add(TABS_NAVIGATION.getSuiteName());
        testSuitesNameList.add(USER_INFO.getSuiteName());

        listenerClasses.add(TestListener.class);
        listenerClasses.add(HTMLReporter.class);


        if (testSuite.length > 0) {
            switch (testSuite[0]) {
                case "administration":
                    xmlSuiteStream = TestSuites.getXmlSuite(ADMINISTRATION);
                    break;
                case "item_management":
                    xmlSuiteStream = TestSuites.getXmlSuite(ITEM_MANAGEMENT);
                    break;
                case "login":
                    xmlSuiteStream = TestSuites.getXmlSuite(LOGIN);
                    break;
                case "ordering":
                    xmlSuiteStream = TestSuites.getXmlSuite(ORDERING);
                    break;
                case "tabs_navigation":
                    xmlSuiteStream = TestSuites.getXmlSuite(TABS_NAVIGATION);
                    break;
                case "user_info":
                    xmlSuiteStream = TestSuites.getXmlSuite(USER_INFO);
                    break;
                default:
                    System.out.println("Input parameters are incorrect, please choose ones from the below list: ");
                    testSuitesNameList.forEach(System.out::println);
            }
        } else {
            xmlSuiteStream = TestSuites.getXmlSuite(ALL_TESTS);
        }
        testNG.setXmlSuites((List<XmlSuite>) new Parser(xmlSuiteStream).parse());
        testNG.setListenerClasses(listenerClasses);
        testNG.setUseDefaultListeners(false);
        testNG.setParallel("classes");
        testNG.setThreadCount(5);
        testNG.run();
    }
}
