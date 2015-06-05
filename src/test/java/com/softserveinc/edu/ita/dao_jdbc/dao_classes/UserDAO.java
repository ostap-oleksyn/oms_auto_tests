/*
* Copyright (C) 2015 dao_jdbc Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.dao_classes;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * represents a concrete implementation of User model
 */
public class UserDAO extends AbstractDAO<User, Integer> {

    /**
     * query to database for getting records
     *
     * @return
     */
//    @Override
//    public String getSelectQuery() {
//        return "SELECT Id, FirstName, LastName, Login, Password FROM users";
//    }
    @Override
    public String getSelectQuery() {
        /*return "SELECT * \n" +
                "FROM users JOIN roles ON users.RoleRef=roles.ID";*/

        return "select  users.Id, FirstName, LastName, Login, Password, RoleName, TypeName \n" +
                "from users inner join roles on users.RoleRef = roles.ID \n" +
                "inner join customertypes on \n" +
                "users.CustomerTypeRef = customertypes.ID";
    }

    public UserDAO(Connection connection) {
        super(connection);
    }

    /**
     * sets records to list
     *
     * @param resultSet
     * @return list with records
     * @throws DAOException
     */
    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws DAOException {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setLogin(resultSet.getString("Login"));
                user.setPassword(resultSet.getString("Password"));
                user.setRoleName(resultSet.getString("RoleName"));
                user.setCustomerType(resultSet.getString("TypeName"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return result;
    }

    /**
     * gets records from database for their login
     *
     * @param login
     * @return
     * @throws DAOException
     */
    public User getByLogin(String login) throws DAOException {
        List<User> list;
        String sql = getSelectQuery();
        sql += " WHERE Login= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        if (list == null || list.size() == 0) {
            throw new DAOException("Record with PK = " + login + " not found.");
        }
        if (list.size() > 1) {
            throw new DAOException("Received more than one record.");
        }
        return list.iterator().next();
    }

    /**
     * prepares statements for their future using
     *
     * @param statement
     * @param user
     * @throws DAOException
     */
    protected void prepareStatement(PreparedStatement statement, User user) throws DAOException {
        try {
            statement.setInt(1, user.getId());
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRoleName());
            statement.setString(6, user.getCustomerType());
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
