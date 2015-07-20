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

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class with static methods for work with database.
 */
public final class DBUtility {

    private DBUtility() {
    }

    /**
     * This method returns a list of all users from database.
     */
    public static List<User> getAllUsers() {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;

        List<User> usersList = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            usersList = userDAO.getAll();
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    /**
     * This method returns a random user by his role from database.
     */
    public static User getRandomUserByRole(final Roles role) {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;
        List<User> usersList = new ArrayList<>();

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            usersList = userDAO.getAll();
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        final List<User> separatedUserList = usersList.stream()
                .filter(user -> user.getRoleName().equals(role.getRoleName()))
                .collect(Collectors.toList());
        return separatedUserList.get(RandomUtil.getRandomInteger(0, separatedUserList.size() - 1));
    }

    /**
     * This method returns filtered users by conditions from database.
     */
    public static List<User> getFilteredUsers(final SearchConditions condition, final String searchTerm) {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;

        List<User> usersList = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            usersList = userDAO.getFilteredUsers(condition, searchTerm);
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    /**
     * This method returns first merchandiser from the database.
     */
    public static User getMerchandiser() {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;

        User admin = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            admin = userDAO.getByRole(Roles.MERCHANDISER);
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return admin;
    }

    /**
     * Returns first customer from database
     */
    public static User getCustomer() throws IOException, ClassNotFoundException {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection;
        final UserDAO userDAO;

        User customer = null;
        try {
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            customer = userDAO.getByRole(Roles.CUSTOMER);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return customer;
    }

    /**
     * This method returns first administrator from the database.
     */
    public static User getAdmin() {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;

        User admin = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            admin = userDAO.getByRole(Roles.ADMINISTRATOR);
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return admin;
    }

    /**
     * This method returns last added user (any role) from the database.
     */
    public static User getLastUser() {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;

        User lastUser = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            lastUser = userDAO.getLastFromDB();
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lastUser;
    }

    /**
     * This method returns a user by login from the database.
     */
    public static User getByLogin(final String login) {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;

        User user = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            user = userDAO.getByLogin(login);
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * This method returns the number of active users in the database.
     */
    public static int getActiveUsersNumber() {
        final FactoryDAO factory;
        Connection connection;
        AbstractDAO userDAO = null;

        List<User> activeUsersList = new ArrayList<>();

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (AbstractDAO) factory.getDAO(connection, User.class);
        } catch (DAOException | IOException | ClassNotFoundException e) {
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
     * This method removes a user from the database.
     */
    public static void deleteUser(final User user) {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            userDAO.delete(user.getId());
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets a user status in the database.
     */
    public static void setUserStatus(final User user, final int status) {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            userDAO.setUserStatus(user, status);
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the last added product to the database.
     */
    public static Product getLastAddedProduct() {
        final FactoryDAO factory;
        final Connection connection;
        ProductDAO productDAO = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        } catch (DAOException | IOException | ClassNotFoundException e) {
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
     * This method removes the product from the database.
     */
    public static void removeProductFromDatabase(final Product product) {
        final FactoryDAO factory;
        final Connection connection;
        ProductDAO productDAO = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        } catch (DAOException | IOException | ClassNotFoundException e) {
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
     * This method returns a status of a product from the database.
     *
     * @param name        - product name
     * @param description - product description
     */
    public static int getProductStatus(final String name, final String description) {
        final FactoryDAO factory;
        final Connection connection;
        ProductDAO productDAO = null;
        int status = 0;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        } catch (DAOException | IOException | ClassNotFoundException e) {
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
     * This method sets status to a product in the database.
     *
     * @param name        - product name
     * @param description - product description
     * @param status      - product status
     */
    public static void setProductStatus(final String name, final String description, final ProductStatus status) {
        final FactoryDAO factory;
        final Connection connection;
        final ProductDAO productDAO;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
            productDAO.setProductStatus(name, description, status);
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns a product from the database by name and description.
     *
     * @param name        - product name
     * @param description - product description
     */
    public static Product getProduct(final String name, final String description) {
        final FactoryDAO factory;
        final Connection connection;
        ProductDAO productDAO = null;
        Product product = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
        } catch (DAOException | IOException | ClassNotFoundException e) {
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
     * This method returns the first user by role from the database.
     */
    public static User getByRole(final Roles role) {
        final FactoryDAO factory;
        final Connection connection;
        final UserDAO userDAO;

        User user = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            userDAO = (UserDAO) factory.getDAO(connection, User.class);
            user = userDAO.getByRole(role);
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * This method returns a order from the database by orderNumber.
     */
    public static Order getByOrderNumber(final int orderNumber) {
        final FactoryDAO factory;
        final Connection connection;
        final OrderDAO orderDAO;

        Order order = null;
        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            orderDAO = (OrderDAO) factory.getDAO(connection, Order.class);
            order = orderDAO.getByOrderNumber(orderNumber);
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return order;
    }

    /**
     * This method returns the number of order items (Products).
     */
    public static int getOrderItemsCount(final Order order) {
        final FactoryDAO factory;
        final Connection connection;
        final OrderItemDAO orderItemDAO;

        List<OrderItem> orderItemsList = new ArrayList<>();

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            orderItemDAO = (OrderItemDAO) factory.getDAO(connection, OrderItem.class);
            orderItemsList = orderItemDAO.getAll();
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return orderItemsList.stream().filter(orderItem -> orderItem.getOrderReference() == order.getId())
                .collect(Collectors.toList()).size();
    }


    /**
     * This method deletes order items (Products).
     */
    public static void deleteOrderItems(final Order order) {
        final FactoryDAO factory;
        final Connection connection;
        final OrderItemDAO orderItemDAO;

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            orderItemDAO = (OrderItemDAO) factory.getDAO(connection, OrderItem.class);
            orderItemDAO.deleteByOrderReference(order.getId());
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method removes an order from the database.
     */
    public static void deleteOrder(final Order order) {
        final FactoryDAO factory;
        final Connection connection;
        final OrderDAO orderDAO;

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            orderDAO = (OrderDAO) factory.getDAO(connection, Order.class);
            orderDAO.delete(order.getId());
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns a list of active products in the database.
     */
    public static List<Product> getActiveProducts() {
        final FactoryDAO factory;
        final Connection connection;
        final ProductDAO productDAO;

        List<Product> productsList = new ArrayList<>();

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
            productsList = productDAO.getActiveProducts();
        } catch (DAOException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return productsList;
    }

    /**
     * This method returns the number of active products in the database.
     */
    public static int getActiveProductsNumber() {
        final FactoryDAO factory;
        final Connection connection;
        final ProductDAO productDAO;
        List<Product> allProductsList = new ArrayList<>();

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            productDAO = (ProductDAO) factory.getDAO(connection, Product.class);
            allProductsList = productDAO.getAll();
        } catch (IOException | DAOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return allProductsList.stream()
                .filter(product -> product.getStatus() == 1)
                .collect(Collectors.toList()).size();
    }

    public static String getOrderNameByCustomer(final User user){
        final FactoryDAO factory;
        final Connection connection;
        final OrderDAO orderDAO;
        List<String> orderNames = new ArrayList<>();

        try {
            factory = new FactoryDAO();
            connection = factory.getConnection();
            orderDAO = (OrderDAO) factory.getDAO(connection, Order.class);
            orderNames = orderDAO.getOrderNamesByCustomer(user);
        } catch (IOException | DAOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orderNames.get( 0 );
    }
}
