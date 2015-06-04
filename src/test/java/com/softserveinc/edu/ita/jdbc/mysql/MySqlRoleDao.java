package jdbc.mysql;

import jdbc.dao.AbstractJDBCDao;
import jdbc.dao.PersistException;
import jdbc.domain.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public class MySqlRoleDao extends AbstractJDBCDao<Role, Integer>{
    @Override
    public Role getByLogin(Integer login) throws PersistException {
        return null;
    }

    private class PersistGroup extends Role {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT Id, RoleName FROM roles";
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    protected List<Role> parseResultSet(ResultSet rs) throws PersistException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Role object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Role object) throws PersistException {

    }

    public MySqlRoleDao(Connection connection) {
        super(connection);
    }
}
