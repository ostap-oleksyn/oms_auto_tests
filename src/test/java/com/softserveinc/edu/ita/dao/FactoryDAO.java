
package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.domains.Order;
import com.softserveinc.edu.ita.domains.OrderItem;
import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.dao.interfaces.IFactoryDAO;
import com.softserveinc.edu.ita.dao.interfaces.IGenericDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.softserveinc.edu.ita.utils.PropertyLoader.getProperty;

/**
 * Class that represents factory of DAO model.
 */
public class FactoryDAO implements IFactoryDAO<Connection> {

    private static final String PROPERTY_FILE = "jdbc.properties";

    private static String USER = null;
    private static String PASSWORD = null;
    private static String URL = null;
    private static String DRIVER = null;

    private Map<Class, ICreatorDAO> creators;


    /**
     * This method returns a connection to the database.
     * @throws DAOException
     */
    public Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            USER = getProperty("user", PROPERTY_FILE);
            PASSWORD = getProperty("password", PROPERTY_FILE);
            URL = getProperty("url", PROPERTY_FILE);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * This method returns an instance of specified DAO domain.
     *
     * @param connection
     * @param classDAO
     * @throws DAOException
     */
    @Override
    public IGenericDAO getDAO(final Connection connection, final Class classDAO) throws DAOException {
        final ICreatorDAO creator = creators.get(classDAO);
        if (creator == null) {
            throw new DAOException("DAO object for " + classDAO + " not found.");
        }
        return creator.create(connection);
    }

    public FactoryDAO() {

        try {
            DRIVER = getProperty("driver", PROPERTY_FILE);
            Class.forName(DRIVER);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        creators = new HashMap<>();

        creators.put(User.class, new ICreatorDAO<Connection>() {
            @Override
            public AbstractDAO create(final Connection connection) {
                return new UserDAO(connection);
            }
        });

        creators.put(User.class, new ICreatorDAO<Connection>() {
            @Override
            public IGenericDAO create(final Connection connection) {
                return new UserDAO(connection);
            }
        });

        creators.put(Order.class, new ICreatorDAO<Connection>() {
            @Override
            public AbstractDAO create(final Connection connection) {
                return new OrderDAO(connection);
            }
        });

        creators.put(Order.class, new ICreatorDAO<Connection>() {
            @Override
            public IGenericDAO create(final Connection connection) {
                return new OrderDAO(connection);
            }
        });
        creators.put(Product.class, new ICreatorDAO<Connection>() {

            @Override
            public AbstractDAO create(final Connection connection) {
                return new ProductDAO(connection);
            }
        });
        creators.put(Product.class, new ICreatorDAO<Connection>() {

            @Override
            public IGenericDAO create(final Connection connection) {
                return new ProductDAO(connection);
            }
        });
        creators.put(OrderItem.class, new ICreatorDAO<Connection>() {
            @Override
            public AbstractDAO create(Connection connection) {
                return new OrderItemDAO(connection);
            }
        });

        creators.put(OrderItem.class, new ICreatorDAO<Connection>() {
            @Override
            public IGenericDAO create(Connection connection) {
                return new OrderItemDAO(connection);
            }
        });



    }
}
