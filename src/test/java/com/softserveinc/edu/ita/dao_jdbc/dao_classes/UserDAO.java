
package com.softserveinc.edu.ita.dao_jdbc.dao_classes;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * represents a concrete implementation of User model
 */
public class UserDAO extends AbstractDAO<User> {

    /**
     * query to database for getting records
     *
     * @return
     */
    @Override
    public String getSelectQuery() {
        return "select  users.Id, FirstName, LastName, Login, Password, Email, RoleName, TypeName \n" +
                "from users inner join roles on users.RoleRef = roles.ID \n" +
                "inner join customertypes on \n" +
                "users.CustomerTypeRef = customertypes.ID";
    }

    public UserDAO(Connection connection) {
        super(connection);
    }

    /**
     * sets records to list
     *
     * @param resultSet
     * @return list with records
     * @throws DAOException
     */
    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws DAOException {
        LinkedList<User> resultList = new LinkedList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setLogin(resultSet.getString("Login"));
                user.setPassword(resultSet.getString("Password"));
                user.setEmail(resultSet.getString("Email"));
                user.setRoleName(resultSet.getString("RoleName"));
                user.setCustomerType(resultSet.getString("TypeName"));
                resultList.add(user);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return resultList;
    }

    /**
     * gets records from database for their login
     *
     * @param login
     * @return
     * @throws DAOException
     */
    public User getByLogin(String login) throws DAOException {
        List<User> list;
        String sqlQuery = getSelectQuery();
        sqlQuery += " WHERE Login= ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        if (list == null || list.size() == 0) {
            throw new DAOException("Record with PK = " + login + " not found.");
        }
        if (list.size() > 1) {
            throw new DAOException("Received more than one record.");
        }
        return list.iterator().next();
    }
}
