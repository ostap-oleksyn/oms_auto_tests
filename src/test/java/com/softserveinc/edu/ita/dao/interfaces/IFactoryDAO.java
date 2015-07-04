
package com.softserveinc.edu.ita.dao.interfaces;

import com.softserveinc.edu.ita.dao.DAOException;

/**
 * represent a contract for IFactoryDAO model
 *
 * @param <Connection>
 */
public interface IFactoryDAO<Connection> {

    interface ICreatorDAO<Connection> {
        IGenericDAO create(Connection connection);
    }

    Connection getConnection() throws DAOException;

    IGenericDAO getDAO(Connection connection, Class className) throws DAOException;
}

