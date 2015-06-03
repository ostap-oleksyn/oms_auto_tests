package com.softserveinc.edu.ita.dao_jdbc.DaoClasses;

import com.softserveinc.edu.ita.dao_jdbc.interfaces.IDaoFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by student on 6/2/2015.
 */
public class DaoFactory implements IDaoFactory {

    static final String FILE_PATH = "src/resources/jdbc.properties";

    private static String URL = null;
    private static String USER = null;
    private static String PASSWORD = null;

    Properties properties = new Properties();
    FileInputStream fileInputStream = null;

    @Override
    public Connection getConnection() throws SQLException {
        try (FileInputStream fileInputStream = new FileInputStream(FILE_PATH)) {
            properties.load(fileInputStream);

            URL = properties.getProperty("host");
            USER = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return new UserDao(connection);
    }

    @Override
    public RoleDao getRoleDao(Connection connection) {
        return new RoleDao(connection);
    }

}
