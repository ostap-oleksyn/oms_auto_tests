
package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;

import java.util.List;

/**
 * represents a contract for a DAO for their model
 *
 * @param <T>
 */
public interface IGenericDAO<T> {

    public T getById(int id) throws DAOException;

    public T getByLogin(String login) throws DAOException;

    public T getByRoleName(String roleName) throws DAOException;

    List<T> getAll() throws DAOException;

}
