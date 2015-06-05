/*
* Copyright (C) 2015 dao_jdbc Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.dao_classes;

import com.softserveinc.edu.ita.dao_jdbc.interfaces.IGenericDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * represents some general methods
 *
 * @param <T> type of using classes
 */
public abstract class AbstractDAO<T, PK extends Integer> implements IGenericDao<T> {

    protected Connection connection;

    public abstract String getSelectQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws DAOException;

    protected abstract void prepareStatement(PreparedStatement statement, T object) throws DAOException;

    /**
     * gets records from databes by their ID
     *
     * @param id
     * @return
     * @throws DAOException
     */

    public T getById(int id) throws DAOException {
        List<T> list;
        String sqlQuery = getSelectQuery();
        sqlQuery += " WHERE users.Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return (T) list;
    }


    public T getByRoleName(String roleName) throws DAOException {
        List<T> list;
        String selectQuery = getSelectQuery();
        selectQuery += " WHERE RoleName = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, roleName);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return (T) list;
    }

    /**
     * gets all records from database
     *
     * @return
     * @throws DAOException
     */
    @Override
    public List<T> getAll() throws DAOException {
        List<T> list;
        String selectQuery = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
}

