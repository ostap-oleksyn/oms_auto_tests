package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.PersistException;

import java.util.List;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public interface IGenericDao<T> {

    public T getById(Integer key) throws PersistException;

//    public T getByLogin(String login) throws PersistException;

    public T create () throws PersistException;

    public T persist(T object) throws PersistException;

    T getByLogin(String key) throws PersistException;

    public void update(T object) throws PersistException;

    public void delete(T object) throws PersistException;

     List<T> getAll() throws PersistException;

}
