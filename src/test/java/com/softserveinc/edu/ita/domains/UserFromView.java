package com.softserveinc.edu.ita.domains;

/**
 * Class to initiate user using "Step Builder" Pattern.
 */
public class UserFromView {
    private String firstName;
    private String lastName;
    private String login;
    private String role;
    private String region;

    public UserFromView(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.toLowerCase();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role.toLowerCase();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region.toLowerCase();
    }

    public String getFirstName() {
        return firstName;
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

    private UserFromView(){

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
        UserFromView build();
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


        public UserFromView build(){
            final UserFromView userFromView = new UserFromView(firstName);

            userFromView.setLastName(lastName);

            if (login != null) {
                userFromView.setLogin(login);
            }
            if (role != null){
                userFromView.setRole(role);
            }
            if (region != null) {
                userFromView.setRegion(region);
            }

            return userFromView;
        }
    }
}