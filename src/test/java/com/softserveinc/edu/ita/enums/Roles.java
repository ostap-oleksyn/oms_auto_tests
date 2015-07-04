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

    private int roleReference;
    private String roleName;

    Roles(final int roleReference, final String roleName) {
        this.roleReference = roleReference;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleReference=" + roleReference +
                ", name='" + roleName + '\'' +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }


    public int getRoleReference() {
        return roleReference;
    }

    public static String getRoleName(final int roleReference) {

        for (final Roles role: Roles.values()) {
            if (role.roleReference == roleReference) {
                return role.getRoleName();
            }
        }

        return null;
    }
}
