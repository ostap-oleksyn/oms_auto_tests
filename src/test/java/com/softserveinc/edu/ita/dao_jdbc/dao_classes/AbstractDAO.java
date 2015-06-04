/*
* Copyright (C) 2015 RegExpTask Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.dao_classes;

import com.softserveinc.edu.ita.dao_jdbc.interfaces.IGenericDao;
import com.softserveinc.edu.ita.dao_jdbc.interfaces.IIdentified;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *    represents some general methods
 * @param <T>  type of using classes
 */
public abstract class AbstractDAO<T extends IIdentified <PK>, PK extends Integer> implements IGenericDao<T,PK> {

    protected Connection connection;

    public abstract String getSelectQuery();

    public abstract String getJoinTablesQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    protected abstract void prepareStatement(PreparedStatement statement, T object) throws PersistException;

    /**
     *  updates records on database
     * @param object
     * @throws PersistException
     */
    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatement(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    /**
     * gets records from databes by their ID
     * @param id
     * @return
     * @throws PersistException
     */
   @Override
   public T getById(Integer id) throws PersistException {
       List<T> list;
       String sql = getSelectQuery();
       sql += " WHERE id = ?";
       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           statement.setInt(1, id);
           ResultSet rs = statement.executeQuery();
           list = parseResultSet(rs);
       } catch (Exception e) {
           throw new PersistException(e);
       }
       if (list == null || list.size() == 0) {
           throw new PersistException("Record with PK = " + id + " not found.");
       }
       if (list.size() > 1) {
           throw new PersistException("Received more than one record.");
       }
       return list.iterator().next();
   }

    public T  getByRoleName(String roleName) throws PersistException {
        List<T> list;
        String sql = getJoinTablesQuery();
        sql += " WHERE RoleName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        }return  list.iterator().next();
    }

    /**
     *  gets all records from database
     * @return
     * @throws PersistException
     */
    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }
    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
}

