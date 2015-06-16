package com.softserveinc.edu.ita.dataproviders;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.AbstractDAO;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.FactoryDAO;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.utils.XlsFileReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class DataProviders {

    /**
     * Returns all users with Administrator role from database;
     */
    @DataProvider(name = "getAdministrators")
    public static Object[][] getAdministratorCredentials() {
        Object[][] credentials = null;
        try {
            credentials = getUsersFromList(Roles.ADMINISTRATOR);
        } catch (IOException | DAOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    /**
     * Returns all users with Customer role from database;
     */
    @DataProvider(name = "getCustomers")
    public static Object[][] getCustomerCredentials() {
        Object[][] credentials = null;
        try {
            credentials = getUsersFromList(Roles.CUSTOMER);
        } catch (IOException | DAOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    /**
     * Returns all users with Supervisor role from database;
     */
    @DataProvider(name = "getSupervisors")
    public static Object[][] getSupervisorCredentials() {
        Object[][] credentials = null;
        try {
            credentials = getUsersFromList(Roles.SUPERVISOR);
        } catch (IOException | DAOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    /**
     * Returns all users with Merchandiser role from database;
     */
    @DataProvider(name = "getMerchandisers")
    public static Object[][] getMerchandiserCredentials() {
        Object[][] credentials = null;
        try {
            credentials = getUsersFromList(Roles.MERCHANDISER);
        } catch (IOException | DAOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    /**
     * Returns all roles users;
     */
    @DataProvider(name = "getAllRoles")
    public static Object[][] getAllRolesUsersFromXLS() {
        Object[][] allRolesUsers = null;
        try {
            allRolesUsers = getUsersFromList(Roles.ALL);
        } catch (DAOException | IOException e) {
            e.printStackTrace();
        }
        return allRolesUsers;
    }

    /**
     * Returns invalid users credentials;
     */
    @DataProvider(name = "getInvalidUsers")
    public static Object[][] getInvalidCredentials() {
        Object[][] invalidUsers = null;
        try {
            invalidUsers = XlsFileReader.readFromXlsByRows("InvalidCredentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invalidUsers;
    }

    private static Object[][] getUsersFromList(Roles role) throws DAOException, IOException {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection = factory.getConnection();
        final AbstractDAO userDAO = (AbstractDAO) factory.getDAO(connection, User.class);

        final List<String> usersLoginFromXls = XlsFileReader.readFromXlsByColumns("Users", role.toString());
        final List<User> users = new ArrayList<>();
        for (String usersLogin : usersLoginFromXls) {
            users.add((User) userDAO.getByLogin(usersLogin));
        }

        final Object[][] usersList = new Object[users.size()][1];

        for (int i = 0; i < users.size(); i++) {
            usersList[i][0] = users.get(i);
        }

        return usersList;
    }
}