/*
* Copyright (C) 2015 RegExpTask Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.PersistException;

import java.io.Serializable;
import java.util.List;

/**
 *  represents a contract for a DAO for their model
 * @param <T>
 */
public interface IGenericDao<T extends IIdentified <PK>, PK extends Serializable> {

    public T getById(Integer id) throws PersistException;

    public T getByLogin(String login) throws PersistException;

    public T getByRoleName(String roleName) throws PersistException;

    public User create() throws PersistException;

    public T persist(T object) throws PersistException;

    public void update(T object) throws PersistException;

    public void delete(T object) throws PersistException;

     List<T> getAll() throws PersistException;

}
