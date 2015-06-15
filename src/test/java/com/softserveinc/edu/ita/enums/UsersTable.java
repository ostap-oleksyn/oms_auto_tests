package com.softserveinc.edu.ita.enums;

import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;

/**
 * This enum serves to limit choice of possible "Administration" table columns presented at "Administration" page. This is used in "clickAdministrationTableColumn" method.
 * Also it limits choice of possible "getter" methods defined in "UserFromView" class. This is used in "verifyEqualityOfTablesByColumn" method.
 */
public enum UsersTable {
    FIRST_NAME("First name", "getFirstName"),
    LAST_NAME("Last name", "getLastName"),
    LOGIN("Login", "getLogin"),
    ROLE("Role", "getRole"),
    REGION("Region", "getRegion");

    private String name;
    private String methodName;

    UsersTable(String name, String methodName) {
        this.name = name;
        this.methodName = methodName;

    }

    public String getName() {
        return this.name;
    }

    public String getMethodName() {
        return this.methodName;
    }


}
