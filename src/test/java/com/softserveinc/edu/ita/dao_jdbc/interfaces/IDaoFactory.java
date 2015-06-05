/*
* Copyright (C) 2015 dao_jdbc Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;

/**
 * represent a contract for DaoFactory model
 *
 * @param <Context>
 */
public interface IDaoFactory<Context> {

    public interface DaoCreator<Context> {
        public IGenericDao create(Context context);
    }

    public Context getContext() throws DAOException;

    public IGenericDao getDao(Context context, Class Class) throws DAOException;
}

