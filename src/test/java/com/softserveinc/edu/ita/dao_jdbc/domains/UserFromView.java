package com.softserveinc.edu.ita.dao_jdbc.domains;

import java.lang.reflect.Method;

/**
 * Class to initiate user using "Step Builder" Pattern.
 */
public class UserFromView {
    private String firstName;
    private String lastName;
    private String login;
    private String role;
    private String region;
    private String defaultHeader;

    public String getDefaultHeader(){
        return this.defaultHeader;
    }

    public void setDefaultHeader(String defaultHeader){
        this.defaultHeader = defaultHeader;
    }

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

    public static class UserFromViewBuilder implements FirstNameStep, LastNameStep, LoginStep, RoleStep, RegionStep, BuildStep {
        private String firstName;
        private String lastName;
        private String login;
        private String role;
        private String region;

        public UserFromViewBuilder() {
        }

        public static FirstNameStep newUserFromView() {
            return new UserFromViewBuilder();
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
            UserFromView userFromView = new UserFromView();
            userFromView.setFirstName(this.firstName);
            userFromView.setLastName(this.lastName);
            userFromView.setLogin(this.login);
            userFromView.setRole(this.role);
            userFromView.setRegion(this.region);
            return userFromView;
        }
    }

    @Override
    public String toString() {
        return "UserFromView{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}