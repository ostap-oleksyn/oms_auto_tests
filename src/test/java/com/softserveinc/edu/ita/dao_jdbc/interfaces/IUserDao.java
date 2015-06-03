package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.Classes.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by student on 6/2/2015.
 */
public interface IUserDao {

    public User createNewUser();

    public User getUserById(int id);

    public void updateUser (User user);

    public void deleteUser (User user);

    public List<User> getAllUsers() throws SQLException;

}
