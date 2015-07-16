package com.softserveinc.edu.ita.enums;

import org.testng.SuiteRunner;

import java.io.InputStream;

public enum TestSuites {
    ADMINISTRATION("administration", "Running administration page tests:", "suites/AdministrationPageTests.xml"),
    ITEM_MANAGEMENT("item_management", "Running item management page tests:", "suites/ItemManagementPageTests.xml"),
    LOGIN("login", "Running login page tests:", "suites/LoginPageTests.xml"),
    ORDERING("ordering", "Running ordering page tests:", "suites/OrderingPageTests.xml"),
    TABS_NAVIGATION("tabs_navigation", "Running tabs navigation tests:", "suites/TabsNavigationTests.xml"),
    USER_INFO("user_info", "Running user info page tests:", "suites/UserInfoPageTests.xml"),
    ALL_TESTS("all_tests", "Running all tests:", "suites/AllTests.xml");

    private String suiteName;
    private String info;
    private String suitePath;

    TestSuites(String suiteName, String info, String suitePath) {
        this.suiteName = suiteName;
        this.info = info;
        this.suitePath = suitePath;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public String getInfo() {
        return info;
    }

    public String getSuitePath() {
        return suitePath;
    }

    public static InputStream getXmlSuite(TestSuites testSuite) {
        System.out.println(testSuite.getInfo());
        return SuiteRunner.class.getClassLoader().getResourceAsStream(testSuite.getSuitePath());
    }

    @Override
    public String toString() {
        return "TestSuites{" +
                "suiteName='" + suiteName + '\'' +
                ", info='" + info + '\'' +
                ", suitePath='" + suitePath + '\'' +
                '}';
    }
}
