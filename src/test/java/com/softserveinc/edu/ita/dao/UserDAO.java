package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.administration_page.SearchConditions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO class for user domain.
 */
public class UserDAO<T> extends AbstractDAO<T> {

    protected UserDAO(final Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT ID, IsUserActive AS Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "FROM Users";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT ID, IsUserActive AS Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "FROM Users \n" +
                "WHERE ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Users SET IsUserActive=?, Email=?, FirstName=?, LastName=?, \n" +
                "Login=?, Password=?, CustomerTypeRef=?, RegionRef=?, RoleRef=? \n" +
                "WHERE ID = ?";
    }

    @Override
    protected String getInsertQuery() {
        return "insert into Users (IsUserActive, Email, FirstName, LastName, " +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Users WHERE ID = ?";
    }

    protected String getByLoginQuery() {
        return "SELECT ID, IsUserActive AS Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "FROM Users \n" +
                "WHERE Login=?";
    }

    private String getByRoleQuery() {
        return "SELECT ID, IsUserActive AS Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "FROM Users \n" +
                "WHERE RoleRef=? \n" +
                "LIMIT 1";
    }

    private String getLastFromDBQuery() {
        return "SELECT ID, IsUserActive AS Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "FROM Users \n" +
                "ORDER BY ID DESC \n" +
                "LIMIT 1";
    }

    private String setUserStatus() {
        return "UPDATE Users SET IsUserActive = ? \n" +
                "WHERE ID = ?";

    }

    @Override
    protected void setUpdateParameters(final PreparedStatement statement, final T object) throws DAOException {
        final User user = (User) object;
        try {
            int i = 1;
            statement.setInt(i++, user.getStatus());
            statement.setString(i++, user.getEmail());
            statement.setString(i++, user.getFirstName());
            statement.setString(i++, user.getLastName());
            statement.setString(i++, user.getLogin());
            statement.setString(i++, user.getPassword());
            statement.setInt(i++,  user.getCustomerTypeReference());
            statement.setInt(i++, user.getRegionReference());
            statement.setInt(i++, user.getRoleReference());
            statement.setInt(i++, user.getId());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    protected void setInsertParameters(final PreparedStatement statement, final T object) throws DAOException {
        final User user = (User) object;
        try {
            int i = 1;
            statement.setInt(i++, user.getStatus());
            statement.setString(i++, user.getEmail());
            statement.setString(i++, user.getFirstName());
            statement.setString(i++, user.getLastName());
            statement.setString(i++, user.getLogin());
            statement.setString(i++, user.getPassword());
            statement.setInt(i++,  user.getCustomerTypeReference());
            statement.setInt(i++, user.getRegionReference());
            statement.setInt(i++, user.getRoleReference());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    protected List<T> parseResultSet(final ResultSet resultSet) throws DAOException {
        final List<T> resultList = new LinkedList<>();
        try {
            while (resultSet.next()) {

                final User user = User.newBuilder()
                        .withId(resultSet.getInt("ID"))
                        .withStatus(resultSet.getInt("Status"))
                        .withFirstName(resultSet.getString("FirstName"))
                        .withLastName(resultSet.getString("LastName"))
                        .withLogin(resultSet.getString("Login"))
                        .withPassword(resultSet.getString("Password"))
                        .withEmail(resultSet.getString("Email"))
                        .withRoleReference(resultSet.getInt("RoleRef"))
                        .withCustomerTypeReference(resultSet.getInt("CustomerTypeRef"))
                        .withRegionReference(resultSet.getInt("RegionRef"))
                        .build();

                resultList.add((T) (user));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return resultList;
    }

    public User getByLogin(final String login) throws DAOException {
        List<User> list;
        final String sqlQuery = getByLoginQuery();

        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, login);
            final ResultSet resultSet = statement.executeQuery();
            list = (List<User>) parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

    public User getByRole(final Roles role) throws DAOException {
        List<User> list;
        final String sqlQuery = getByRoleQuery();

        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, role.ordinal() + 1);
            final ResultSet resultSet = statement.executeQuery();
            list = (List<User>) parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

    public User getLastFromDB() throws DAOException {
        List<User> list;
        final String sqlQuery = getLastFromDBQuery();

        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            final ResultSet resultSet = statement.executeQuery();
            list = (List<User>) parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

    public List<User> getFilteredUsers(final SearchConditions condition, final String searchTerm) throws DAOException {
        List<User> usersList;
        String getAllUsersQuery = null;

        switch (condition) {
            case EQUALS:
                default:
                getAllUsersQuery = getSelectAllQuery() + " WHERE FirstName = ? OR LastName = ? OR Login = ? OR RoleRef = ? " +
                        "OR RegionRef = ?";
                break;
            case NOT_EQUALS_TO:
                getAllUsersQuery = getSelectAllQuery() + " WHERE FirstName != ? OR LastName != ? OR Login != ? OR RoleRef != ? " +
                        "OR RegionRef != ?";
                break;
            case CONTAINS:
                getAllUsersQuery = getSelectAllQuery() + " WHERE FirstName like ? OR LastName like ? OR Login like ? OR RoleRef like ?" +
                        " OR RegionRef like ?";
                break;
            case DOES_NOT_CONTAINS:
                getAllUsersQuery = getSelectAllQuery() + " WHERE FirstName not like? OR LastName not like ? OR Login not like ? " +
                        "OR RoleRef not like ? OR RegionRef not like ?";
                break;
            case STARTS_WITH:
                getAllUsersQuery = getSelectAllQuery() + " WHERE FirstName like ? OR LastName like ? OR Login like ? OR " +
                        "RoleRef like ? OR RegionRef like ?";
                break;
        }
        try (PreparedStatement statement = connection.prepareStatement(getAllUsersQuery)) {
            statement.setString(1, searchTerm);
            statement.setString(2, searchTerm);
            statement.setString(3, searchTerm);
            statement.setString(4, searchTerm);
            statement.setString(5, searchTerm);

            final ResultSet resultSet = statement.executeQuery();
            usersList = (List<User>) parseResultSet(resultSet);

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return usersList;
    }

    public void setUserStatus(final User user, final int status) throws DAOException {
        final String sqlQuery = setUserStatus();
        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, status);
            statement.setInt(2, user.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
