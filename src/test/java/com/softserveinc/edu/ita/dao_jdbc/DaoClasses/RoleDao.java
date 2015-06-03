package com.softserveinc.edu.ita.dao_jdbc.DaoClasses;

import com.softserveinc.edu.ita.dao_jdbc.Classes.Role;
import com.softserveinc.edu.ita.dao_jdbc.interfaces.IRoleDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 6/3/2015.
 */
public class RoleDao implements IRoleDao {
    private Connection connection;
    Role role;
    public RoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Role> getUsersByRole() {
//        String sqlQuery = "SELECT * FROM Roles WHERE RoleName = ?;";
//
//        PreparedStatement preparedStatement;
//        List<Role> roleList = null;
//        try {
//            preparedStatement = connection.prepareStatement(sqlQuery);
//            preparedStatement.setInt(1, Integer.parseInt(roleName));
//
//            roleList = new ArrayList<Role>();
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            role = new Role(resultSet.getInt("Id"), resultSet.getString("RoleName"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return roleList;

        String sqlQuery = "SELECT * FROM Roles;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Role> roleList = new ArrayList<Role>();
        try {
            while (resultSet.next()) {
                try {
                    role = new Role(resultSet.getInt("Id"), resultSet.getString("RoleName"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                roleList.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }
}
