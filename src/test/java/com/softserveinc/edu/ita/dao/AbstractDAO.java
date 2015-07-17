package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.dao.interfaces.IGenericDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract class that provides basic CRUD operations.
 */
public abstract class AbstractDAO<T> implements IGenericDAO<T> {

    protected Connection connection;

    protected abstract String getSelectQuery();

    protected abstract String getSelectAllQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getInsertQuery();

    protected abstract String getDeleteQuery();

    /**
     * Method for updating data in the database.
     * @param statement - statement with update query
     * @param object - object of domain class which fields will be updated
     */
    protected abstract void setUpdateParameters(PreparedStatement statement, T object) throws DAOException;

    /**
     * Method for inserting data into to the database.
     * @param statement - statement with insert query
     * @param object - object of domain class to be inserted
     */
    protected abstract void setInsertParameters(PreparedStatement statement, T object) throws DAOException;

    /**
     * Parses ResultSet and returns a list of object corresponding to the content of ResultSet.
     */
    protected abstract List<T> parseResultSet(ResultSet resultSet) throws DAOException;

    public AbstractDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<T> getAll() throws DAOException {
        List<T> list;
        final String selectQuery = getSelectAllQuery();

        try (final PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            final ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public T getObject(final int id) throws DAOException {
        List<T> list;
        final String sqlQuery = getSelectQuery();

        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

    @Override
    public int insert(final T object) throws DAOException {
        final String sqlQuery = getInsertQuery();

        int newId = 0;
        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            setInsertParameters(statement, object);
            statement.executeUpdate();
            final ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                newId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return newId;
    }

    @Override
    public void update(final T object) throws DAOException {
        final String sqlQuery = getUpdateQuery();

        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setUpdateParameters(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(final int id) throws DAOException {
        final String sqlQuery = getDeleteQuery();

        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
