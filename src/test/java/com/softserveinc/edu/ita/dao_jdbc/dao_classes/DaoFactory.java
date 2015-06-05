/*
* Copyright (C) 2015 dao_jdbc Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.dao_classes;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dao_jdbc.interfaces.IDaoFactory;
import com.softserveinc.edu.ita.dao_jdbc.interfaces.IGenericDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * represents factory of DAO model
 */
public class DaoFactory implements IDaoFactory<Connection> {

    private String user = "oms";
    private String password = "1qaz2wsx";
    private String url = "jdbc:mysql://localhost:3306/oms";
    private String driver = "com.mysql.jdbc.Driver";

    private Map<Class, DaoFactory.DaoCreator> creators;

    public Connection getContext() throws DAOException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return connection;
    }

    @Override
    public IGenericDao getDao(Connection connection, Class daoClass) throws DAOException {
        DaoCreator creator = creators.get(daoClass);
        if (creator == null) {
            throw new DAOException("Dao object for " + daoClass + " not found.");
        }
        return creator.create(connection);
    }

    public DaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        creators = new HashMap<>();
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public AbstractDAO create(Connection connection) {
                return new UserDAO(connection);
            }
        });
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public IGenericDao create(Connection connection) {
                return new UserDAO(connection);
            }
        });

    }
}