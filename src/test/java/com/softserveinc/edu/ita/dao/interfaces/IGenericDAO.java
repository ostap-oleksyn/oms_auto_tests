
package com.softserveinc.edu.ita.dao.interfaces;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.enums.Roles;

import java.util.List;

/**
 * represents a contract for a DAO for their model
 *
 * @param <T>
 */
public interface IGenericDAO<T> {

    public T getById(int id) throws DAOException;

    public T getByLogin(String login) throws DAOException;

    public T getByRoleName(Roles roleName) throws DAOException;

    public T getLast() throws DAOException;

    List<T> getAll() throws DAOException;

}
