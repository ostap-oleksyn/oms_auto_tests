package com.softserveinc.edu.ita.enums;

import com.softserveinc.edu.ita.dao_jdbc.domains.UserFromView;

/**
 * This enum serves to limit choice of possible "Administration" table columns presented at "Administration" page. This is used in "clickAdministrationTableColumn" method.
 * Also it limits choice of possible "getter" methods defined in "UserFromView" class. This is used in "verifyEqualityOfTablesByColumn" method.
 */
//todo rename
public enum UsersTable {
    FIRST_NAME("First name"),
    LAST_NAME("Last name"),
    LOGIN("Login"),
    ROLE("Role"),
    REGION("Region");

    private String name;

    UsersTable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
