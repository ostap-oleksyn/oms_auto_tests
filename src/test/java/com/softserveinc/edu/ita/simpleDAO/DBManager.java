package com.softserveinc.edu.ita.simpleDAO;

import java.io.IOException;
import java.sql.*;

import static com.softserveinc.edu.ita.utils.PropertyLoader.getProperty;

public class DBManager {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public void connect() {

        final String PROPERTY_FILE = "jdbc.properties";

        try {
            final String USER = getProperty("user", PROPERTY_FILE);
            final String PASSWORD = getProperty("password", PROPERTY_FILE);
            final String URL = getProperty("url", PROPERTY_FILE);

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query, String... parameters) {

        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                statement.setString(i+1, parameters[i]);
            }
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void execute(String query, String... parameters) {

        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                statement.setString(i+1, parameters[i]);
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resultSet = null;
    }


}
