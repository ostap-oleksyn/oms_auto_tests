
package com.softserveinc.edu.ita.dao_jdbc.dao_classes;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dao_jdbc.interfaces.IFactoryDAO;
import com.softserveinc.edu.ita.dao_jdbc.interfaces.IGenericDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * represents factory of DAO model
 */
public class FactoryDAO implements IFactoryDAO<Connection> {

    private String user = "oms";
    private String password = "1qaz2wsx";
    private String url = "jdbc:mysql://localhost:3306/oms";
    private String driver = "com.mysql.jdbc.Driver";

    private Map<Class, ICreatorDAO> creators;

    public Connection getConnection() throws DAOException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return connection;
    }

    @Override
    public IGenericDAO getDAO(Connection connection, Class classDAO) throws DAOException {
        ICreatorDAO creator = creators.get(classDAO);
        if (creator == null) {
            throw new DAOException("DAO object for " + classDAO + " not found.");
        }
        return creator.create(connection);
    }

    public FactoryDAO() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        creators = new HashMap<>();
        creators.put(User.class, new ICreatorDAO<Connection>() {

            @Override
            public AbstractDAO create(Connection connection) {
                return new UserDAO(connection);
            }
        });
        creators.put(User.class, new ICreatorDAO<Connection>() {

            @Override
            public IGenericDAO create(Connection connection) {
                return new UserDAO(connection);
            }
        });

    }
}
