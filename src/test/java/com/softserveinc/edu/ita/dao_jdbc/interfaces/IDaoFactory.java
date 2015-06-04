package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.DaoClasses.RoleDao;
import com.softserveinc.edu.ita.dao_jdbc.DaoClasses.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by student on 6/2/2015.
 */

public interface IDaoFactory {

   public Connection getConnection()throws SQLException;

   public UserDao getUserDao(Connection connection);

   public RoleDao getRoleDao(Connection connection);
}