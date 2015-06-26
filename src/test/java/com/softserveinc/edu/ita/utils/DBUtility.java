package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.dao.*;
import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Roles;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            lastUser = (User) userDAO.getLast();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return lastUser;
    }

    /**
     * Returns User by login
     */
    public static User getByLogin(String login) {
        FactoryDAO factory = new FactoryDAO();
        Connection connection;
        AbstractDAO userDAO = null;

        try {
            connection = factory.getConnection();
            userDAO = (AbstractDAO) factory.getDAO(connection, User.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        User user = null;

        try {
            user = (User) userDAO.getByLogin(login);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Returns the number of active users in the database
     */
    public static int getActiveUsersNumber() {
        FactoryDAO factory = new FactoryDAO();
        Connection connection;
        AbstractDAO userDAO = null;

        List<User> activeUsersList = new ArrayList<>();

        try {
            connection = factory.getConnection();
            userDAO = (AbstractDAO) factory.getDAO(connection, User.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        try {
            activeUsersList = userDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return activeUsersList.stream()
                .filter(user -> user.getStatus().equals("1"))
                .collect(Collectors.toList()).size();
    }

    /**
     * connects to database and returns userDAO object
     *
     * @return
     * @throws DAOException
     */
    public static UserDAO getUserDao() throws DAOException {
        FactoryDAO factory = new FactoryDAO();
        Connection connection = factory.getConnection();
        UserDAO userDAO = (UserDAO) factory.getDAO(connection, User.class);
        return userDAO;

    }

    public static Product getLastAddedProduct() {
        FactoryDAO factory = new FactoryDAO();
        Connection connection;
        ProductDAO productDAO = null;
        try {
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        Product product = null;
        try {
            product = productDAO.getLast();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static void deleteLastAddedProduct(Product product) {
        FactoryDAO factory = new FactoryDAO();
        Connection connection;
        ProductDAO productDAO = null;
        try {
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        try {
            productDAO.deleteById(product);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
