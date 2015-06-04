package jdbc.mysql;

import jdbc.dao.AbstractJDBCDao;
import jdbc.dao.PersistException;
import jdbc.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {


    @Override
    public String getSelectQuery() {
        return "SELECT Id, FirstName, LastName, Login, Password FROM users";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO users (FirstName, LastName, Login, Password) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE users \n" +
                "SET FirstName = ?, LastNme  = ?, Login = ?, Password = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM users WHERE id= ?;";
    }



    public MySqlUserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setLogin(rs.getString("Login"));
                user.setPassword(rs.getString("Password"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {

    }


    @Override
    public User getByLogin(Integer login) throws PersistException {
        return null;
    }
}
