
package com.softserveinc.edu.ita.dao.interfaces;

import com.softserveinc.edu.ita.dao.DAOException;

import java.util.List;

/**
 * represents a contract for a DAO for their model
 *
 * @param <T>
 */
public interface IGenericDAO<T> {

    List<T> getAll() throws DAOException;

    T getObject(int id) throws DAOException;

    int insert(T object) throws DAOException;

    void update(T object) throws DAOException;

    void delete(int id) throws DAOException;

}
