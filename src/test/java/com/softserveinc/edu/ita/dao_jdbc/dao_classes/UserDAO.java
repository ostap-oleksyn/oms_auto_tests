package com.softserveinc.edu.ita.dao_jdbc.dao_classes;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public class UserDAO extends AbstractDAO<User, Integer> {


    @Override
    public String getSelectQuery() {
        return "SELECT Id, FirstName, LastName, Login, Password FROM users";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO users (FirstName, LastName, Login, Password) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE users \n" +
                "SET FirstName = ?, LastNme  = ?, Login = ?, Password = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM users WHERE id= ?;";
    }



    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setLogin(rs.getString("Login"));
                user.setPassword(rs.getString("Password"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }
@Override
    public User getByLogin(String login) throws PersistException {
        List<User> list;
        String sql = getSelectQuery();
        sql += " WHERE Login= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
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

    @Override
    public void delete(User object) throws PersistException {

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {

    }



}
