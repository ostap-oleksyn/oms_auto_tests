package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.PersistException;

import java.util.List;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public interface IGenericDao<T> {

    public T getById(User id) throws PersistException;

    T getById(Integer key) throws PersistException;

    public T getByLogin(String login) throws PersistException;

    public T getByRoleName(String roleName) throws PersistException;

    public List<T> getAll() throws PersistException;

}
