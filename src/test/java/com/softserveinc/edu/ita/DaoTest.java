package com.softserveinc.edu.ita;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by student on 6/2/2015.
 */
public class DaoTest {
    public static void main(String[] args) throws SQLException {
        IDaoFactory daoFactory = new DaoFactory();
        Connection connection = daoFactory.getConnection();
        UserDao userDao = daoFactory.getUserDao(connection);
        List<User> userList = userDao.getAllUsers();
        for(User user:userList){
            System.out.println(user);
        }
        User user = userDao.getUserById(7);
        System.out.println(user);

        RoleDao roleDao = daoFactory.getRoleDao(connection);
        List<Role> roleList = roleDao.getUsersByRole();
        for (Role role:roleList){
            System.out.println(role);
        }

    }
}
