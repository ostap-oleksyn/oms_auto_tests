package jdbc.mysql;

import jdbc.dao.DaoFactory;
import jdbc.dao.GenericDao;
import jdbc.dao.PersistException;
import jdbc.domain.Role;
import jdbc.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public class MySqlDaoFactory implements DaoFactory<Connection> {


    private String user = "oms";
    private String password = "1qaz2wsx";
    private String url = "jdbc:mysql://localhost:3306/oms";
    private String driver = "com.mysql.jdbc.Driver";

    private Map<Class, DaoFactory.DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class daoClass) throws PersistException {
        DaoCreator creator = creators.get(daoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + daoClass + " not found.");
        }
        return creator.create(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        creators = new HashMap<>();
        creators.put(Role.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlRoleDao(connection);
            }
        });
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUserDao(connection);
            }
        });

    }
}
