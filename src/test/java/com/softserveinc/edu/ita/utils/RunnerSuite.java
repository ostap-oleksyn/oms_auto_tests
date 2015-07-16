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

        List<String> testSuitesNameList = new ArrayList<>();
        testSuitesNameList.add(ADMINISTRATION.getSuiteName());
        testSuitesNameList.add(ITEM_MANAGEMENT.getSuiteName());
        testSuitesNameList.add(LOGIN.getSuiteName());
        testSuitesNameList.add(ORDERING.getSuiteName());
        testSuitesNameList.add(TABS_NAVIGATION.getSuiteName());
        testSuitesNameList.add(USER_INFO.getSuiteName());

        listenerClasses.add(TestListener.class);
        listenerClasses.add(HTMLReporter.class);
        String name = testSuite[0];
        
        if (testSuite.length > 0) {
            switch (name) {
                case "administration":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream(ADMINISTRATION.getSuitePath());
                    System.out.println(ADMINISTRATION.getInfo());
                    break;
                case "item_management":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream(ITEM_MANAGEMENT.getSuitePath());
                    System.out.println(ITEM_MANAGEMENT.getInfo());
                    break;
                case "login":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream(LOGIN.getSuitePath());
                    System.out.println(LOGIN.getInfo());
                    break;
                case "ordering":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream(ORDERING.getSuitePath());
                    System.out.println(ORDERING.getInfo());
                    break;
                case "tabs_navigation":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream(TABS_NAVIGATION.getSuitePath());
                    System.out.println(TABS_NAVIGATION.getInfo());
                    break;
                case "user_info":
                    xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream(USER_INFO.getSuitePath());
                    System.out.println(USER_INFO.getInfo());
                    break;
                default:
                    System.out.println("Input parameters are incorrect, please change from the below list: ");
                    testSuitesNameList.forEach(System.out::println);


            }
        } else {
            xmlSuiteStream = SuiteRunner.class.getClassLoader().getResourceAsStream(ALL_TESTS.getSuitePath());
            System.out.println(ALL_TESTS.getInfo());
        }
        testNG.setXmlSuites((List<XmlSuite>) new Parser(xmlSuiteStream).parse());

        testNG.setListenerClasses(listenerClasses);
        testNG.setUseDefaultListeners(false);
        testNG.setParallel("classes");
        testNG.setThreadCount(5);
        testNG.run();
    }
}
