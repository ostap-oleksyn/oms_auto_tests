package com.softserveinc.edu.ita.domains;

import com.softserveinc.edu.ita.enums.CustomerTypes;
import com.softserveinc.edu.ita.enums.Roles;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the User model
 */
public class User {

    @Getter @Setter(AccessLevel.PRIVATE) private int id;
    @Getter @Setter(AccessLevel.PRIVATE) private int status;
    @Getter @Setter(AccessLevel.PRIVATE) private String firstName;
    @Getter @Setter(AccessLevel.PRIVATE) private String lastName;
    @Getter @Setter(AccessLevel.PRIVATE) private String login;
    @Getter @Setter(AccessLevel.PRIVATE) private String password;
    @Getter @Setter(AccessLevel.PRIVATE) private String email;
    @Getter @Setter(AccessLevel.PRIVATE) private int roleReference;
    @Getter @Setter(AccessLevel.PRIVATE) private int customerTypeReference;
    @Getter @Setter(AccessLevel.PRIVATE) private int regionReference;

    private User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", status=" + status +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roleReference=" + roleReference +
                ", customerTypeReference=" + customerTypeReference +
                ", regionReference=" + regionReference +
                '}';
    }

    public String getRoleName() {
        return Roles.getRoleName(roleReference);
    }

    public String getCustomerTypeName() {
        return CustomerTypes.getCustomerTypeName(customerTypeReference);
    }

    public static IdStep newBuilder() {
        return new Builder();
    }

    public interface IdStep {
        StatusStep withId(int id);
        StatusStep withoutId();
    }

    public interface StatusStep {
        FirstNameStep withStatus(int status);
        FirstNameStep withoutStatus();
    }

    public interface FirstNameStep {
        LastNameStep withFirstName(String firstName);
        LastNameStep withoutFirstName();
    }

    public interface LastNameStep {
        LoginStep withLastName(String lastName);
        LoginStep withoutLastName();
    }

    public interface LoginStep {
        PasswordStep withLogin(String login);
        PasswordStep withoutLogin();
    }

    public interface PasswordStep {
        EmailStep withPassword(String password);
        EmailStep withoutPassword();
    }

    public interface EmailStep {
        RoleReferenceStep withEmail(String email);
        RoleReferenceStep withoutEmail();
    }

    public interface RoleReferenceStep {
        CustomerTypeReferenceStep withRoleReference(int roleReference);
        CustomerTypeReferenceStep withoutRoleReference();
    }

    public interface CustomerTypeReferenceStep {
        RegionReferenceStep withCustomerTypeReference(int customerTypeReference);
        RegionReferenceStep withoutCustomerTypeReference();
    }

    public interface RegionReferenceStep {
        BuildStep withRegionReference(int regionReference);
        BuildStep withoutRegionReference();
    }

    public interface BuildStep {
        User build();
    }

    public static class Builder implements IdStep, StatusStep, FirstNameStep, LastNameStep, LoginStep, PasswordStep,
            EmailStep, RoleReferenceStep, CustomerTypeReferenceStep, RegionReferenceStep, BuildStep {
        private int id;
        private int status;
        private String firstName;
        private String lastName;
        private String login;
        private String password;
        private String email;
        private int roleReference;
        private int customerTypeReference;
        private int regionReference;

        private Builder() {
        }

        public static IdStep user() {
            return new Builder();
        }

        @Override
        public StatusStep withId(final int id) {
            this.id = id;
            return this;
        }

        @Override
        public StatusStep withoutId() {
            return this;
        }

        @Override
        public FirstNameStep withStatus(final int status) {
            this.status = status;
            return this;
        }

        @Override
        public FirstNameStep withoutStatus() {
            return this;
        }

        @Override
        public LastNameStep withFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public LastNameStep withoutFirstName() {
            return this;
        }

        @Override
        public LoginStep withLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public LoginStep withoutLastName() {
            return this;
        }

        @Override
        public PasswordStep withLogin(final String login) {
            this.login = login;
            return this;
        }

        @Override
        public PasswordStep withoutLogin() {
            return this;
        }

        @Override
        public EmailStep withPassword(final String password) {
            this.password = password;
            return this;
        }

        @Override
        public EmailStep withoutPassword() {
            return this;
        }

        @Override
        public RoleReferenceStep withEmail(final String email) {
            this.email = email;
            return this;
        }

        @Override
        public RoleReferenceStep withoutEmail() {
            return this;
        }

        @Override
        public CustomerTypeReferenceStep withRoleReference(final int roleReference) {
            this.roleReference = roleReference;
            return this;
        }

        @Override
        public CustomerTypeReferenceStep withoutRoleReference() {
            return this;
        }

        @Override
        public RegionReferenceStep withCustomerTypeReference(final int customerTypeReference) {
            this.customerTypeReference = customerTypeReference;
            return this;
        }

        @Override
        public RegionReferenceStep withoutCustomerTypeReference() {
            return this;
        }

        @Override
        public BuildStep withRegionReference(final int regionReference) {
            this.regionReference = regionReference;
            return this;
        }

        @Override
        public BuildStep withoutRegionReference() {
            return this;
        }

        @Override
        public User build() {
            final User user = new User();
            user.setId(this.id);
            user.setStatus(this.status);
            user.setFirstName(this.firstName);
            user.setLastName(this.lastName);
            user.setLogin(this.login);
            user.setPassword(this.password);
            user.setEmail(this.email);
            user.setRoleReference(this.roleReference);
            user.setCustomerTypeReference(this.customerTypeReference);
            user.setRegionReference(this.regionReference);
            return user;
        }
    }
}
