/*
* Copyright (C) 2015 RegExpTask Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.dao_classes;

import com.softserveinc.edu.ita.dao_jdbc.classes.Role;
import com.softserveinc.edu.ita.dao_jdbc.classes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *  represents a concrete implementation of User model
 */
public class UserDAO extends AbstractDAO<User,Integer> {

    /**
     *  query to database for getting records
     * @return
     */
    @Override
    public String getSelectQuery() {
        return "SELECT Id, FirstName, LastName, Login, Password FROM users";
    }
    @Override
    public String getJoinTablesQuery(){
        return "SELECT FirstName, LastName, Login, Password, RoleName FROM users JOIN roles ON users.RoleRef=roles.ID WHERE";
    }

    /**
     * query to database for creating records
     * @return
     */
    @Override
    public String getCreateQuery() {
        return "INSERT INTO users (FirstName, LastName, Login, Password) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    /**
     * query to database for updating records
     * @return
     */
    @Override
    public String getUpdateQuery() {
        return "UPDATE users \n" +
                "SET FirstName = ?, LastNme  = ?, Login = ?, Password = ? \n" +
                "WHERE id = ?;";
    }

    /**
     * quary to database for deleting records
     * @return
     */
    @Override
    public String getDeleteQuery() {
        return "DELETE FROM users WHERE id= ?;";
    }



    public UserDAO(Connection connection) {
        super(connection);
    }

    /**
     *  sets records to list
     * @param resultSet
     * @return list with records
     * @throws PersistException
     */
    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                Role role = new Role();
                user.setId(resultSet.getInt("Id"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setLogin(resultSet.getString("Login"));
                user.setPassword(resultSet.getString("Password"));
//                role.setRoleName(resultSet.getString("RoleName"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    /**
     * gets records from database for their login
     * @param login
     * @return
     * @throws PersistException
     */
    public User getByLogin(String login) throws PersistException {
        List<User> list;
        String sql = getSelectQuery();
        sql += " WHERE Login= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            throw new PersistException("Record with PK = " + login + " not found.");
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public User create() throws PersistException {
        return null;
    }

    @Override
    public User persist(User object) throws PersistException {
        return null;
    }


//    @Override
//    public void delete(User object) throws PersistException {
//
//    }

    /**
     *  prepares statements for their future using
     * @param statement
     * @param user
     * @throws PersistException
     */
    protected void prepareStatement(PreparedStatement statement, User user) throws PersistException {
        try {
            statement.setInt(1, user.getId());
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
//            statement.setString(5, user.getRoleName());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
