package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.dao.*;
import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.enums.administration_page.SearchConditions;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Static methods for work with database
 */
public class DBUtility {

    /**
     * Returns all users
     */
    public static List<User> getAllUsers() {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        List<User> usersList = null;
        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            usersList = userDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    /**
     * Returns filtered by conditions users
     */
    public static List<User> getFilteredUsers(SearchConditions condition, String searchTerm) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        List<User> usersList = null;
        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            usersList = userDAO.getFilteredUsers(condition, searchTerm);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    /**
     * Returns first administrator from database
     */
    public static User getAdmin() {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        User admin = null;
        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            admin = userDAO.getByRole(Roles.ADMINISTRATOR);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return admin;
    }

    /**
     * Returns last added user (any role) from database
     */
    public static User getLastUser() {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        User lastUser = null;
        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            lastUser = userDAO.getLastFromDB();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return lastUser;
    }

    /**
     * Returns User by login
     */
    public static User getByLogin(String login) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        User user = null;
        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            user = userDAO.getByLogin(login);
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
                .filter(user -> user.getStatus() == 1)
                .collect(Collectors.toList()).size();
    }

    /**
     * Delete user from database
     */
    public static void deleteUser(User user) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            userDAO.delete(user.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * set user status
     */
    public static void setUserStatus(User user, int status) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            userDAO.setUserStatus(user, status);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns last added product to the database
     */
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
            product = productDAO.getLastAddedProduct();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * Removes the product from database
     */
    public static void removeProductFromDatabase(Product product) {
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
