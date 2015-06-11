package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.AbstractDAO;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.FactoryDAO;
import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.enums.Roles;

import java.sql.Connection;
import java.util.List;

/**
 * Created on 10.06.2015.
 */
public class DBUtility {

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

        List<User> lastUserList = null;

        try {
            lastUserList = userDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return lastUserList.get(0);
    }


}
