package com.softserveinc.edu.ita.dao_jdbc;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.AbstractDAO;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DaoFactory;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.PersistException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public class DaoTest {
    public static void main(String[] args) throws PersistException, SQLException {

        DaoFactory factory = new DaoFactory();
        Connection connection = factory.getContext();
        AbstractDAO dao = (AbstractDAO) factory.getDao(connection, User.class);
        System.out.println(dao.getById(5));

        System.out.println(dao.getByLogin("orest"));


//        List<User> usersList = dao.getAll();
//        for (User user : usersList) {
//            System.out.println(user);
//        }

   }
}

