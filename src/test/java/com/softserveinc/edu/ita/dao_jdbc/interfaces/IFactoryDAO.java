
package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;

/**
 * represent a contract for IFactoryDAO model
 *
 * @param <Context>
 */
public interface IFactoryDAO<Context> {

    public interface ICreatorDAO<Context> {
        public IGenericDAO create(Context context);
    }

    public Context getContext() throws DAOException;

    public IGenericDAO getDAO(Context context, Class className) throws DAOException;
}

