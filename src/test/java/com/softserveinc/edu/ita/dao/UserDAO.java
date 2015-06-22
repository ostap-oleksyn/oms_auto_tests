
package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.enums.SearchConditions;

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
    protected String getSelectQuery() {
        return "select  users.Id, FirstName, LastName, Login, Password, Email, RoleName, TypeName, RegionName, " +
                "IsUserActive as Status  \n" +
                "from users \n" +
                "left outer join customertypes on users.CustomerTypeRef = customertypes.ID \n" +
                "inner join regions on users.RegionRef = regions.ID \n" +
                "inner join roles on users.RoleRef = roles.ID";
    }
    String getAllUsersQuery = "select  users.Id, FirstName, LastName, Login, Password, Email, RoleName, TypeName, RegionName, \n" +
            "IsUserActive as Status \n" +
            "from users inner join roles on users.RoleRef = roles.ID \n" +
            "inner join customertypes on\n" +
            "users.CustomerTypeRef = customertypes.ID\n" +
            "inner join regions on \n" +
            "users.RegionRef = regions.ID";

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
                user.setRegionName(resultSet.getString("RegionName"));
                user.setStatus(resultSet.getString("Status"));
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

    public User getLastUser() throws DAOException {
        List<User> usersList;
        String sqlQuery = getSelectQuery();
        sqlQuery += " WHERE IsUserActive = 1 ORDER BY ID DESC ";
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            usersList = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        if (usersList == null || usersList.size() == 0) {
            throw new DAOException("Users not found.");
        }

        return usersList.get(0);
    }

    public User getByRoleName(Roles role) throws DAOException {
        List<User> list;
        String sqlQuery = getSelectQuery();
        //todo add limit 1
        sqlQuery += " WHERE RoleName= ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, String.valueOf(role));
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        if (list == null || list.size() == 0) {
            throw new DAOException("Record with RoleName = " + role + " not found.");
        }

        return list.get(0);
    }
    /**
     * get all users from database
     *
     * @return
     * @throws DAOException
     */
    public List<User> getAllUsers() throws DAOException {
        List<User> usersList;
        try (PreparedStatement statement = connection.prepareStatement(getAllUsersQuery)) {
            ResultSet resultSet = statement.executeQuery();
            usersList = parseResultSet(resultSet);
            for (User user : usersList) {
                user.setId(0);
                user.setEmail(null);
                user.setCustomerType(null);
                user.setStatus(null);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return usersList;
    }

    public List<User> getFilteredUsers(SearchConditions conditions, String searchTerm) throws DAOException {
        List<User> usersList;
        switch (conditions) {
            case EQUALS:
                getAllUsersQuery += " where FirstName = ? or LastName = ? or Login = ? or RoleName = ? " +
                        "or RegionName = ?";
                break;
            case NOT_EQUALS_TO:
                getAllUsersQuery += " where FirstName != ? or LastName != ? or Login != ? or RoleName != ? " +
                        "or RegionName != ?";
                break;
            case CONTAINS:
                getAllUsersQuery += " where FirstName like ? or LastName like ? or Login like ? or RoleName like ?" +
                        " or RegionName like ?";
                break;
            case DOES_NOT_CONTAINS:
                getAllUsersQuery += " where FirstName not like? or LastName not like ? or Login not like ? " +
                        "or RoleName not like ? or RegionName not like ?";
                break;
            case STARTS_WITH:
                getAllUsersQuery += " where FirstName like ? or LastName like ? or Login like ? or " +
                        "RoleName like ? or RegionName like ?";
                break;
        }
        try (PreparedStatement statement = connection.prepareStatement(getAllUsersQuery)) {
            statement.setString(1, searchTerm);
            statement.setString(2, searchTerm);
            statement.setString(3, searchTerm);
            statement.setString(4, searchTerm);
            statement.setString(5, searchTerm);

            ResultSet resultSet = statement.executeQuery();
            usersList = parseResultSet(resultSet);
            for (User user : usersList) {
                user.setId(0);
                user.setEmail(null);
                user.setCustomerType(null);
                user.setStatus(null);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return usersList;
    }
}
