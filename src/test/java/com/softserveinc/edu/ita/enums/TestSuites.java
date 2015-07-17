package com.softserveinc.edu.ita.enums;

public enum TestSuites {
    ADMINISTRATION("Administration", "suites/AdministrationPageTests.xml"),
    ITEM_MANAGEMENT("Item_management", "suites/ItemManagementPageTests.xml"),
    LOGIN("Login", "suites/LoginPageTests.xml"),
    ORDERING("Ordering", "suites/OrderingPageTests.xml"),
    NAVIGATION("Navigation", "suites/TabsNavigationTests.xml"),
    USER_INFO("User_info", "suites/UserInfoPageTests.xml"),
    LOCALIZATION("Localization", "suites/LocalizationTests.xml"),
    ALL("All_tests", "suites/AllTests.xml");

    private String suiteName;
    private String suitePath;

    TestSuites(final String suiteName, final String suitePath) {
        this.suiteName = suiteName;
        this.suitePath = suitePath;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public String getSuitePath() {
        return suitePath;
    }
}
