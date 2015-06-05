
package com.softserveinc.edu.ita.dao_jdbc;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.AbstractDAO;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.FactoryDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DAOTest {
    public static void main(String[] args) throws DAOException, SQLException {

        FactoryDAO factory = new FactoryDAO();
        Connection connection = factory.getConnection();
        AbstractDAO dao = (AbstractDAO) factory.getDAO(connection, User.class);

        System.out.println(dao.getById(5));

        System.out.println(dao.getByLogin("login4"));

        System.out.println(dao.getByRoleName("Merchandiser"));

        System.out.println(dao.getByRoleName("Supervisor"));

        List<User> userList = (List) dao.getByRoleName("Administrator");
        for (User user : userList) {
            System.out.println(user);
        }

        List<User> usersList = dao.getAll();
        for (User user : usersList) {
            System.out.println(user);
        }


    }
}

