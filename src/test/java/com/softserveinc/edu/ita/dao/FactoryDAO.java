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
    private Map<Class, ICreatorDAO> creators;

    /**
     * This method returns a connection to the database.
     *
     * @throws DAOException
     */
    public Connection getConnection() throws DAOException, IOException {

        String password;
        String url;
        String user;
        Connection connection;

        if (PropertyLoader.getProperty("remote.enabled").equals("true")) {
            try {
                user = getProperty("user", PROPERTY_FILE);
                password = getProperty("password", PROPERTY_FILE);
                url = getProperty("url", PROPERTY_FILE);
                final String vmUrl = VirtualBoxUtil.getVirtualMachineIP();
                connection = DriverManager.getConnection(url.replace("localhost", vmUrl), user, password);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } else {
            try {
                user = getProperty("user", PROPERTY_FILE);
                password = getProperty("password", PROPERTY_FILE);
                url = getProperty("url", PROPERTY_FILE);
                connection = DriverManager.getConnection(url, user, password);
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

        final String driver = getProperty("driver", PROPERTY_FILE);
        Class.forName(driver);

        creators = new HashMap<>();
        creators.put(User.class, (ICreatorDAO<Connection>) UserDAO::new);
        creators.put(Order.class, (ICreatorDAO<Connection>) OrderDAO::new);
        creators.put(Product.class, (ICreatorDAO<Connection>) ProductDAO::new);
        creators.put(OrderItem.class, (ICreatorDAO<Connection>) OrderItemDAO::new);
    }
}
