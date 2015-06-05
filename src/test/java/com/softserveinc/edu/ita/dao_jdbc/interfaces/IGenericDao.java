/*
* Copyright (C) 2015 dao_jdbc Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;

import java.util.List;

/**
 * represents a contract for a DAO for their model
 *
 * @param <T>
 */
public interface IGenericDao<T> {

    public T getById(int id) throws DAOException;

    public T getByLogin(String login) throws DAOException;

    public T getByRoleName(String roleName) throws DAOException;

    List<T> getAll() throws DAOException;

}
