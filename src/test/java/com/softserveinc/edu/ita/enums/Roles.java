package com.softserveinc.edu.ita.enums;

/**
 * Enum with roles names. Can generate random values
 */
public enum Roles {
    ADMINISTRATOR(1, "Administrator"),
    MERCHANDISER(2, "Merchandiser"),
    SUPERVISOR(3, "Supervisor"),
    CUSTOMER(4, "Customer"),
    ALL(5, "All");

    private int roleRef;
    private String roleName;

    Roles(int roleRef, String roleName) {
        this.roleRef = roleRef;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleRef=" + roleRef +
                ", name='" + roleName + '\'' +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public int getRoleRef() {
        return roleRef;
    }

    public static String getRoleName(int roleRef) {

        for (Roles role: Roles.values()) {
            if (role.roleRef == roleRef) {
                return role.getRoleName();
            }
        }

        return null;
    }
}
