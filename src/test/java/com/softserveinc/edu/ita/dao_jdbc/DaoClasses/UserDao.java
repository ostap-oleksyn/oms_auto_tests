package com.softserveinc.edu.ita.dao_jdbc.DaoClasses;

import com.softserveinc.edu.ita.dao_jdbc.Classes.Role;
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
            user = new User(resultSet.getInt("Id"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Login"),
                    resultSet.getString("Password"));
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
     public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM Users;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getInt("Id"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Login"),
                        resultSet.getString("Password"));
                userList.add(user);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }return userList;

    }


    public List<User> getUsersByLogin(String password) {
        String sqlQuery = "SELECT * FROM users WHERE Password = ?;";

        PreparedStatement preparedStatement;
        List<User> userList = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, password);

            userList = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getInt("Id"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Login"),
                    resultSet.getString("Password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
