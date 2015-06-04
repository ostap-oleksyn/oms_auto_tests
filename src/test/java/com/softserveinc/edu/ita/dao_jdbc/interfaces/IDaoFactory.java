package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.PersistException;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
    public interface IDaoFactory<Context> {

        public interface DaoCreator<Context> {
            public IGenericDao create(Context context);
        }


        public Context getContext() throws PersistException;

        public IGenericDao getDao(Context context, Class Class) throws PersistException;
}

