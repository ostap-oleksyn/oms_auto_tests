
package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;

/**
 * represent a contract for IFactoryDAO model
 *
 * @param <Connection>
 */
public interface IFactoryDAO<Connection> {

    public interface ICreatorDAO<Connection> {
        public IGenericDAO create(Connection connection);
    }

    public Connection getConnection() throws DAOException;

    public IGenericDAO getDAO(Connection connection, Class className) throws DAOException;
}

