package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.domains.Order;
import com.softserveinc.edu.ita.domains.OrderItem;
import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.dao.interfaces.IFactoryDAO;
import com.softserveinc.edu.ita.dao.interfaces.IGenericDAO;
import com.softserveinc.edu.ita.utils.VirtualBoxUtil;
import com.softserveinc.edu.ita.utils.PropertyLoader;

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
    private static String VM_URL = null;

    private Map<Class, ICreatorDAO> creators;


    /**
     * This method returns a connection to the database.
     *
     * @throws DAOException
     */
    public Connection getConnection() throws DAOException, IOException {
        Connection connection;

        if (PropertyLoader.getProperty("remote.enabled").equals("true")) {
            try {
                USER = getProperty("user", PROPERTY_FILE);
                PASSWORD = getProperty("password", PROPERTY_FILE);
                URL = getProperty("url", PROPERTY_FILE);
                VM_URL = VirtualBoxUtil.getVirtualMachineIP();
                connection = DriverManager.getConnection(URL.replace("localhost", VM_URL), USER, PASSWORD);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } else {
            try {
                USER = getProperty("user", PROPERTY_FILE);
                PASSWORD = getProperty("password", PROPERTY_FILE);
                URL = getProperty("url", PROPERTY_FILE);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
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

    public FactoryDAO() throws IOException, ClassNotFoundException {

        DRIVER = getProperty("driver", PROPERTY_FILE);
        Class.forName(DRIVER);

        creators = new HashMap<>();
        creators.put(User.class, (ICreatorDAO<Connection>) UserDAO::new);
        creators.put(Order.class, (ICreatorDAO<Connection>) OrderDAO::new);
        creators.put(Product.class, (ICreatorDAO<Connection>) ProductDAO::new);
        creators.put(OrderItem.class, (ICreatorDAO<Connection>) OrderItemDAO::new);
    }
}
