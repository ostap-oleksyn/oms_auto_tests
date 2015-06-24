package com.softserveinc.edu.ita.domains;

/**
 * This class represents the User model
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String roleReference;
    private String customerTypeReference;
    private String status;
    private String regionReference;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleReference() {
        return roleReference;
    }

    public void setRoleReference(String roleReference) {
        this.roleReference = roleReference;
    }

    public String getCustomerTypeReference() {
        return customerTypeReference;
    }

    public void setCustomerTypeReference(String customerTypeReference) {
        this.customerTypeReference = customerTypeReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegionReference() {
        return regionReference;
    }

    public void setRegionReference(String regionReference) {
        this.regionReference = regionReference;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", roleReference='" + getRoleReference() + '\'' +
                ", customerTypeReference='" + getCustomerTypeReference() + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }

    public static UserIdStep newBuilder() {
        return new Steps();
    }

    public interface UserIdStep {
        FirstNameStep withId(int id);
        FirstNameStep withoutId();
    }

    public interface FirstNameStep {
        LastNameStep withFirstName(String firstName);
    }

    public interface LastNameStep {
        LoginStep withLastName(String lastName);
    }

    public interface LoginStep {
        EmailStep withLogin(String login);
    }

    public interface EmailStep {
        PasswordStep withEmail(String email);
    }

    public interface PasswordStep {
        RoleReferenceStep withPassword(String password);
    }

    public interface RoleReferenceStep {
        CustomerTypeReferenceStep withRoleRef(String roleName);
        CustomerTypeReferenceStep withoutRoleRef();
    }

    public interface CustomerTypeReferenceStep {
        StatusStep withCustomerTypeRef(String customerType);
        StatusStep withoutCustomerTypeRef();
    }

    public interface StatusStep {
        RegionReferenceStep withStatus(String status);
        RegionReferenceStep withoutStatus();
    }

    public interface RegionReferenceStep {
        BuildStep withRegionRef(String regionName);
        BuildStep withoutRegionRef();
    }

    public interface BuildStep {
        User build();
    }

    public static class Steps implements UserIdStep, FirstNameStep, LastNameStep, LoginStep, EmailStep, PasswordStep, RoleReferenceStep, CustomerTypeReferenceStep, StatusStep, RegionReferenceStep, BuildStep {
        private int id;
        private String firstName;
        private String lastName;
        private String login;
        private String email;
        private String password;
        private String roleName;
        private String customerType;
        private String status;
        private String regionName;

        private Steps() {
        }

        public static UserIdStep user() {
            return new Steps();
        }

        @Override
        public FirstNameStep withId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public FirstNameStep withoutId() {
            return this;
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
        public EmailStep withLogin(String login) {
            this.login = login;
            return this;
        }

        @Override
        public PasswordStep withEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public RoleReferenceStep withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public CustomerTypeReferenceStep withRoleRef(String roleName) {
            this.roleName = roleName;
            return this;
        }

        @Override
        public CustomerTypeReferenceStep withoutRoleRef() {
            return this;
        }

        @Override
        public StatusStep withCustomerTypeRef(String customerType) {
            this.customerType = customerType;
            return this;
        }

        @Override
        public StatusStep withoutCustomerTypeRef() {
            return this;
        }

        @Override
        public RegionReferenceStep withStatus(String status) {
            this.status = status;
            return this;
        }

        @Override
        public RegionReferenceStep withoutStatus() {
            return this;
        }

        @Override
        public BuildStep withRegionRef(String regionName) {
            this.regionName = regionName;
            return this;
        }

        @Override
        public BuildStep withoutRegionRef() {
            return this;
        }

        @Override
        public User build() {
            User user = new User();
            user.setId(this.id);
            user.setFirstName(this.firstName);
            user.setLastName(this.lastName);
            user.setLogin(this.login);
            user.setEmail(this.email);
            user.setPassword(this.password);
            user.setRoleReference(this.roleName);
            user.setCustomerTypeReference(this.customerType);
            user.setStatus(this.status);
            user.setRegionReference(this.regionName);
            return user;
        }
    }
}
