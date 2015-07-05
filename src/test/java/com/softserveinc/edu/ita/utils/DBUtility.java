package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.dao.*;
import com.softserveinc.edu.ita.domains.Order;
import com.softserveinc.edu.ita.domains.OrderItem;
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
 * Static methods for work with database
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
    public static void removeProductFromDatabase(Product product) {
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

    public static int getProductStatus(String name, String description) {
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

    public static void setProductStatus(String name, String description, ProductStatus status) {
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

    public static Product getProduct(String name, String description) {
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

    /**
     * Returns first User by role
     */
    public static User getByRole(Roles role) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        User user = null;
        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            user = userDAO.getByRole(role);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Returns Order by orderNumber
     */
    public static Order getByOrderNumber(int orderNumber) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final OrderDAO orderDAO;

        Order order = null;
        try {
            connection = factory.getConnection();
            orderDAO = (OrderDAO) factory.getDAO(connection, Order.class);
            order = orderDAO.getByOrderNumber(orderNumber);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return order;
    }

    /**
     * Return Order items (Products)
     */
    public static int getOrderItemsCount(Order order) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final OrderItemDAO orderItemDAO;

        List<OrderItem> orderItemsList = new ArrayList<>();

        try {
            connection = factory.getConnection();
            orderItemDAO = (OrderItemDAO) factory.getDAO(connection, OrderItem.class);
            orderItemsList = orderItemDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return orderItemsList.stream().filter(orderItem -> orderItem.getOrderReference() == order.getId())
                .collect(Collectors.toList()).size();
    }

    public static void deleteOrderItems(Order order) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final OrderItemDAO orderItemDAO;

        try {
            connection = factory.getConnection();
            orderItemDAO = (OrderItemDAO) factory.getDAO(connection, OrderItem.class);
            orderItemDAO.deleteByOrderReference(order.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete Order from database
     */
    public static void deleteOrder(Order order) {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final OrderDAO orderDAO;

        try {
            connection = factory.getConnection();
            orderDAO = (OrderDAO) factory.getDAO(connection, Order.class);
            orderDAO.delete(order.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }


    public static int getActiveProductsCount() {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final ProductDAO productDAO;

        List<Product> productsList = new ArrayList<>();

        try {
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
            productsList = productDAO.getActiveProducts();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return productsList.size();
    }

    public static List<Product> getActiveProducts() {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final ProductDAO productDAO;

        List<Product> productsList = new ArrayList<>();

        try {
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
            productsList = productDAO.getActiveProducts();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return productsList;
    }

}
