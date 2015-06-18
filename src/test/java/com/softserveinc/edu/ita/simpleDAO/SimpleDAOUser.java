package com.softserveinc.edu.ita.simpleDAO;

import com.softserveinc.edu.ita.domains.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleDAOUser {

    public List<User> getAllUsers() {

        DBManager dbManager = new DBManager();
        dbManager.connect();
        ResultSet resultSet = dbManager.executeQuery("select * from users where isUserActive=?", "1");

        List<User> usersList = new ArrayList<>();

        try {
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setLastName(resultSet.getString("LastName"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLogin(resultSet.getString("Login"));
                user.setPassword(resultSet.getString("Password"));
                user.setEmail(resultSet.getString("Email"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbManager.close();

        return usersList;
    }

    public User getUser(String login) {

        DBManager dbManager = new DBManager();
        dbManager.connect();
        ResultSet resultSet = dbManager.executeQuery("select * from users where login=?", login);

        List<User> usersList = new ArrayList<>();

        try {
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setLastName(resultSet.getString("LastName"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLogin(resultSet.getString("Login"));
                user.setPassword(resultSet.getString("Password"));
                user.setEmail(resultSet.getString("Email"));
                user.setStatus(resultSet.getString("IsUserActive"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbManager.close();

        return usersList.get(0);
    }

    public void updateUser(User user) {
        DBManager dbManager = new DBManager();
        dbManager.connect();
        dbManager.execute("update users set IsUserActive=0 where id=?", String.valueOf(user.getId()));

        dbManager.close();

    }

    public void deleteUser(User student) {

    }
}
