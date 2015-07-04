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


public class UserDAO<T> extends AbstractDAO<T> {

    protected UserDAO(final Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "select Id, IsUserActive as Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "from users";
    }

    @Override
    protected String getSelectQuery() {
        return "select Id, IsUserActive as Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "from users \n" +
                "where id=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "update users set IsUserActive=?, Email=?, FirstName=?, LastName=?, \n" +
                "Login=?, Password=?, CustomerTypeRef=?, RegionRef=?, RoleRef=? \n" +
                "where id = ?";
    }

    @Override
    protected String getInsertQuery() {
        return "insert into users (IsUserActive, Email, FirstName, LastName, " +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from users where id = ?";
    }

    protected String getByLoginQuery() {
        return "select Id, IsUserActive as Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "from users \n" +
                "where login=?";
    }

    private String getByRoleQuery() {
        return "select Id, IsUserActive as Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "from users \n" +
                "where RoleRef=? \n" +
                "limit 1";
    }

    private String getLastFromDBQuery() {
        return "select Id, IsUserActive as Status, Email, FirstName, LastName, \n" +
                "Login, Password, CustomerTypeRef, RegionRef, RoleRef \n" +
                "from users \n" +
                "order by id desc \n" +
                "limit 1";
    }

    private String setUserStatus() {
        return "UPDATE USERS SET IsUserActive = ? \n" +
                "WHERE Id = ?";

    }

    @Override
    protected void setUpdateParameters(final PreparedStatement statement, final T object) {
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
            e.printStackTrace();
        }
    }

    @Override
    protected void setInsertParameters(final PreparedStatement statement, final T object) {
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
            e.printStackTrace();
        }
    }

    @Override
    protected List<T> parseResultSet(final ResultSet resultSet) throws DAOException {
        final List<T> resultList = new LinkedList<>();
        try {
            while (resultSet.next()) {

                final User user = User.newBuilder()
                        .withId(resultSet.getInt("Id"))
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
        } catch (Exception e) {
            throw new DAOException(e);
        }

        return resultList;
    }

    public User getByLogin(final String login) throws DAOException {
        List<User> list;
        final String sqlQuery = getByLoginQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, login);
            final ResultSet resultSet = statement.executeQuery();
            list = (List<User>) parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

    public User getByRole(final Roles role) throws DAOException {
        List<User> list;
        final String sqlQuery = getByRoleQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, role.ordinal() + 1);
            final ResultSet resultSet = statement.executeQuery();
            list = (List<User>) parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

    public User getLastFromDB() throws DAOException {
        List<User> list;
        final String sqlQuery = getLastFromDBQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            final ResultSet resultSet = statement.executeQuery();
            list = (List<User>) parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

    public List<User> getFilteredUsers(final SearchConditions condition, final String searchTerm) throws DAOException {
        List<User> usersList;
        String getAllUsersQuery = null;

        switch (condition) {
            case EQUALS:
                getAllUsersQuery = getSelectAllQuery() + " where FirstName = ? or LastName = ? or Login = ? or RoleRef = ? " +
                        "or RegionRef = ?";
                break;
            case NOT_EQUALS_TO:
                getAllUsersQuery = getSelectAllQuery() + " where FirstName != ? or LastName != ? or Login != ? or RoleRef != ? " +
                        "or RegionRef != ?";
                break;
            case CONTAINS:
                getAllUsersQuery = getSelectAllQuery() + " where FirstName like ? or LastName like ? or Login like ? or RoleRef like ?" +
                        " or RegionRef like ?";
                break;
            case DOES_NOT_CONTAINS:
                getAllUsersQuery = getSelectAllQuery() + " where FirstName not like? or LastName not like ? or Login not like ? " +
                        "or RoleRef not like ? or RegionRef not like ?";
                break;
            case STARTS_WITH:
                getAllUsersQuery = getSelectAllQuery() + " where FirstName like ? or LastName like ? or Login like ? or " +
                        "RoleRef like ? or RegionRef like ?";
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

        } catch (Exception e) {
            throw new DAOException(e);
        }
        return usersList;
    }

    public void setUserStatus(final User user, final int status) throws DAOException {
        final String sqlQuery = setUserStatus();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, status);
            statement.setInt(2, user.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

}
