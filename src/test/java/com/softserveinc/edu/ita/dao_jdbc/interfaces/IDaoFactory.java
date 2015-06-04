/*
* Copyright (C) 2015 RegExpTask Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.PersistException;

/**
 *  represent a contract for DaoFactory model
 * @param <Context>
 */
public interface IDaoFactory<Context> {

    public interface DaoCreator<Context> {
        public IGenericDao create(Context context);
    }

    public Context getContext() throws PersistException;

    public IGenericDao getDao(Context context, Class Class) throws PersistException;
}

