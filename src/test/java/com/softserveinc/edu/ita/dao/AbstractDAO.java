package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.dao.interfaces.IGenericDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class AbstractDAO<T> implements IGenericDAO<T> {

    protected Connection connection;

    protected abstract String getSelectQuery();
    protected abstract String getSelectAllQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getInsertQuery();
    protected abstract String getDeleteQuery();
    protected abstract void setUpdateParameters(PreparedStatement statement, T object);
    protected abstract void setInsertParameters(PreparedStatement statement, T object);
    protected abstract List<T> parseResultSet(ResultSet resultSet) throws DAOException;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<T> getAll() throws DAOException {
        List<T> list;
        String selectQuery = getSelectAllQuery();

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public T getObject(int id) throws DAOException {
        List<T> list;
        String sqlQuery = getSelectQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

    @Override
    public int insert(T object) throws DAOException {
        String sqlQuery = getInsertQuery();

        int newId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            setInsertParameters(statement, object);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                newId = resultSet.getInt(1);
            }

        } catch (Exception e) {
            throw new DAOException(e);
        }

        return newId;
    }

    @Override
    public void update(T object) throws DAOException {
        String sqlQuery = getUpdateQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setUpdateParameters(statement, object);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sqlQuery = getDeleteQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

}
