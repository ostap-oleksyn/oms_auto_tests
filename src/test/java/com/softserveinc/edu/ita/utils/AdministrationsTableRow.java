package com.softserveinc.edu.ita.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Class makes possible to set fields of the row belonging to "Administration" table from application.
 */
public class AdministrationsTableRow {
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String firstName;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String lastName;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String login;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String role;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String region;

    public AdministrationsTableRow(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        } else {
            this.firstName = "";
        }
    }

    @Override
    public String toString() {
        return "AdministrationsTableRow{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    private AdministrationsTableRow() {
    }

    public static FirstNameStep newBuilder() {
        return new Steps();
    }

    public interface FirstNameStep {
        LastNameStep firstName(String firstName);
    }

    public interface LastNameStep {
        LoginStep lastName(String lastName);
    }

    public interface LoginStep {
        RoleStep login(String login);
    }

    public interface RoleStep {
        RegionStep role(String role);
    }

    public interface RegionStep {
        BuildStep region(String region);
    }

    public interface BuildStep {
        AdministrationsTableRow build();
    }

    private static class Steps implements FirstNameStep, LastNameStep, LoginStep, RoleStep, RegionStep, BuildStep {
        private String firstName;
        private String lastName;
        private String login;
        private String role;
        private String region;

        public LastNameStep firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public LoginStep lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public RoleStep login(String login) {
            this.login = login;
            return this;
        }

        public RegionStep role(String role) {
            this.role = role;
            return this;
        }

        public BuildStep region(String region) {
            this.region = region;
            return this;
        }

        public AdministrationsTableRow build() {
            final AdministrationsTableRow administrationsTableRow = new AdministrationsTableRow(firstName.toLowerCase());
            if (lastName != null) {
                administrationsTableRow.setLastName(lastName.toLowerCase());
            } else {
                administrationsTableRow.setLastName("");
            }
            if (login != null) {
                administrationsTableRow.setLogin(login.toLowerCase());
            } else {
                administrationsTableRow.setLogin("");
            }
            if (role != null) {
                administrationsTableRow.setRole(role.toLowerCase());
            } else {
                administrationsTableRow.setRole("");
            }
            if (region != null) {
                administrationsTableRow.setRegion(region.toLowerCase());
            } else {
                administrationsTableRow.setRegion("");
            }
            return administrationsTableRow;
        }
    }
}