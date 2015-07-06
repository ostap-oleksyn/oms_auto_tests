package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.dao.*;
import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.ProductStatus;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.enums.administration_page.SearchConditions;
import org.testng.Reporter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class with static methods for work with database.
 */
public final class DBUtility {

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
     * Returns random user by his role.
     */
    public static User getRandomUserByRole(final Roles role) throws DAOException {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;
        connection = factory.getConnection();
        userDAO = (UserDAO) factory.getDAO(connection, User.class);
        final List<User> usersList = userDAO.getAll();
        final List<User> separatedUserList = usersList.stream()
                .filter(user -> user.getRoleName().equals(role.getRoleName()))
                .collect(Collectors.toList());
        return separatedUserList.get(RandomUtil.getRandomInteger(0, separatedUserList.size()-1));
    }

    /**
     * Returns filtered by conditions users
     */
    public static List<User> getFilteredUsers(final SearchConditions condition, final String searchTerm) {
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
     * Returns first merchandiser from database
     */
    public static User getMerchandiser() {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        User admin = null;
        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            admin = userDAO.getByRole(Roles.MERCHANDISER);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return admin;
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
    public static User getByLogin(final String login) {
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
        final FactoryDAO factory = new FactoryDAO();
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
    public static void deleteUser(final User user) {
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
    public static void setUserStatus(final User user, final int status) {
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
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
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
     * Removes the product from the database
     */
    public static void removeProductFromDatabase(final Product product) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        ProductDAO productDAO = null;
        try {
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        try {
            productDAO.delete(product.getId());
            Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Product <b>%s</b> removed from the database", product.getProductName()));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns status of a product from database
     *
     * @param name        - product name
     * @param description - product description
     */
    public static int getProductStatus(final String name, final String description) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        ProductDAO productDAO = null;
        int status = 0;
        try {
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        try {
            status = productDAO.getProductStatus(name, description);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * Sets status to a product in database
     *
     * @param name        - product name
     * @param description - product description
     * @param status      - product status
     */
    public static void setProductStatus(final String name, final String description, final ProductStatus status) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final ProductDAO productDAO;
        try {
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
            productDAO.setProductStatus(name, description, status);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns product from database by name and description
     *
     * @param name        - product name
     * @param description - product description
     */
    public static Product getProduct(final String name, final String description) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        ProductDAO productDAO = null;
        Product product = null;
        try {
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        try {
            product = productDAO.getProduct(name, description);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static int getActiveProductsNumber() throws DAOException {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection = factory.getConnection();
        final ProductDAO productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        final List<Product> allProductsList = productDAO.getAll();
        return allProductsList.stream()
                .filter(product -> product.getStatus() == 1)
                .collect(Collectors.toList()).size();
    }
}
