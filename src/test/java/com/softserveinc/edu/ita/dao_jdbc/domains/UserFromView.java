package com.softserveinc.edu.ita.dao_jdbc.domains;

/**
 * Created by student on 6/10/2015.
 */
public class UserFromView {
    private String firstName;
    private String lastName;
    private String login;
    private String role;
    private String region;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public interface FirstNameStep {
        LastNameStep withFirstName(String firstName);
    }

    public interface LastNameStep {
        LoginStep withLastName(String lastName);
    }

    public interface LoginStep {
        RoleStep withLogin(String login);
    }

    public interface RoleStep {
        RegionStep withRole(String role);
    }

    public interface RegionStep {
        BuildStep withRegion(String region);
    }

    public interface BuildStep {
        UserFromView build();
    }

    public static class AdministratorBuilder implements FirstNameStep, LastNameStep, LoginStep, RoleStep, RegionStep, BuildStep {
        private String firstName;
        private String lastName;
        private String login;
        private String role;
        private String region;

        public AdministratorBuilder() {
        }

        public static FirstNameStep newAdministrator() {
            return new AdministratorBuilder();
        }

        @Override
        public LastNameStep withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public LoginStep withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public RoleStep withLogin(String login) {
            this.login = login;
            return this;
        }

        @Override
        public RegionStep withRole(String role) {
            this.role = role;
            return this;
        }

        @Override
        public BuildStep withRegion(String region) {
            this.region = region;
            return this;
        }

        @Override
        public UserFromView build() {
            UserFromView administrator = new UserFromView();
            administrator.setFirstName(this.firstName);
            administrator.setLastName(this.lastName);
            administrator.setLogin(this.login);
            administrator.setRole(this.role);
            administrator.setRegion(this.region);
            return administrator;
        }
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}