package com.softserveinc.edu.ita.dao_jdbc.DaoClasses;

import com.softserveinc.edu.ita.dao_jdbc.Classes.User;
import com.softserveinc.edu.ita.dao_jdbc.interfaces.IUserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ihor-Dynka on 02.06.2015.
 */
public class UserDao implements IUserDao {
    private Connection connection;
    User user;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User createNewUser() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        String sqlQuery = "SELECT * FROM Users WHERE id = ?;";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getInt("Id"), resultSet.getString("FirstName"), resultSet.getString("LastName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List <User> getAllUsers() throws SQLException {
        String sqlQuery = "SELECT * FROM Users;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> userList = new ArrayList<User>();
        while (resultSet.next()) {
            user = new User(resultSet.getInt("Id"), resultSet.getString("FirstName"), resultSet.getString("LastName"));
            userList.add(user);
        }
        return userList;
    }
}
