package com.softserveinc.edu.ita.dao_jdbc;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DaoFactory;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.PersistException;
import com.softserveinc.edu.ita.dao_jdbc.interfaces.IGenericDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public class DaoTest {
    public static void main(String[] args) throws PersistException, SQLException {

        DaoFactory factory = new DaoFactory();
        Connection connection = factory.getContext();
        IGenericDao dao = factory.getDao(connection, User.class);
        System.out.println(dao.getById(1));
        System.out.println(dao.getByLogin("iva"));

        List<User> usersList = dao.getAll();
        for (User user : usersList) {
            System.out.println(user);
        }


    }
}

