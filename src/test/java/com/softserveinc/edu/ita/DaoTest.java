import jdbc.dao.DaoFactory;
import jdbc.dao.GenericDao;
import jdbc.dao.Identified;
import jdbc.dao.PersistException;
import jdbc.domain.User;
import jdbc.mysql.MySqlDaoFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public class DaoTest {
    protected Class daoClass;
    protected  Identified identified;


    public static void main(String[] args) throws PersistException, SQLException {

        DaoFactory factory = new MySqlDaoFactory();
        Connection connection = (Connection) factory.getContext();
        GenericDao dao = factory.getDao(connection,User.class);
        System.out.println(dao.getById(1));
        System.out.println(dao.getByLogin("iva"));

        List<User>usersList = dao.getAll();
        for (User user:usersList){
            System.out.println(user);
        }

        }



    }

