
package com.softserveinc.edu.ita.dao.interfaces;

import com.softserveinc.edu.ita.dao.DAOException;

import java.util.List;

/**
 * represents a contract for a DAO for their model
 *
 * @param <T>
 */
public interface IGenericDAO<T> {

    public List<T> getAll() throws DAOException;

    public T getObject(int id) throws DAOException;

    public int insert(T object) throws DAOException;

    public void update(T object) throws DAOException;

    public void delete(int id) throws DAOException;

}
