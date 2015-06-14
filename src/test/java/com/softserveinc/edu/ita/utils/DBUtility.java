package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.AbstractDAO;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.FactoryDAO;
import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.enums.Roles;

import java.sql.Connection;

/**
 * Static methods for work with database
 */
public class DBUtility {

    /**
     * Returns first administrator from database
     */
    public static User getAdmin() {
        FactoryDAO factory = new FactoryDAO();
        Connection connection;
        AbstractDAO userDAO = null;

        try {
            connection = factory.getConnection();
            userDAO = (AbstractDAO) factory.getDAO(connection, User.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        User admin = null;

        try {
            admin = (User) userDAO.getByRoleName(Roles.ADMINISTRATOR);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return admin;
    }

    /**
     * Returns last added user (any role) from database
     */
    public static User getLastUser() {
        FactoryDAO factory = new FactoryDAO();
        Connection connection;
        AbstractDAO userDAO = null;

        try {
            connection = factory.getConnection();
            userDAO = (AbstractDAO) factory.getDAO(connection, User.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        User lastUser = null;

        try {
            lastUser = (User) userDAO.getLastUser();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return lastUser;
    }
}
