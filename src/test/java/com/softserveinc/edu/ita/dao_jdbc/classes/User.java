/*
* Copyright (C) 2015 RegExpTask Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.classes;

import com.softserveinc.edu.ita.dao_jdbc.interfaces.IIdentified;

/**
 *  This class represents the User model
 */
public class User implements IIdentified<Integer> {
    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *    helps to output users more correctly
     * @return
     */

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
