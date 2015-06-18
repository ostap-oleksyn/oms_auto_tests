
package com.softserveinc.edu.ita.dataproviders;

import com.softserveinc.edu.ita.dao_jdbc.dao_classes.AbstractDAO;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.FactoryDAO;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.*;


import com.softserveinc.edu.ita.utils.XlsFileReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import java.util.Random;
import java.util.stream.Stream;

import static com.softserveinc.edu.ita.enums.AdministrationTabConditions.*;
import static com.softserveinc.edu.ita.enums.AdministrationTabFilters.*;
import static com.softserveinc.edu.ita.utils.StringsGenerator.generateString;


public class DataProviders {
    /**
     * returns all  searching filters from administration tab
     */
    @DataProvider(name = "getSearchFilters")
    public static Object[][] getFilters() {
        return new Object[][]{
                new AdministrationTabFilters[]{ALL_COLUMNS},
                new AdministrationTabFilters[]{FIRST_NAME},
                new AdministrationTabFilters[]{LAST_NAME},
                new AdministrationTabFilters[]{LOGIN_NAME},
                new AdministrationTabFilters[]{ROLE}
        };
    }

    /**
     *  returns all searching conditions from administration tab
     * @return
     */
    @DataProvider(name = "getSearchCondition")
    public static Object[][] getCondition() {
        return new Object[][]{
                new AdministrationTabConditions[]{EQUALS},
                new AdministrationTabConditions[]{NOT_EQUALS_TO},
                new AdministrationTabConditions[]{CONTAINS},
                new AdministrationTabConditions[]{DOES_NOT_CONTAINS},
                new AdministrationTabConditions[]{STARTS_WITH}
        };
    }

    /**
     * Returns names of 'Users' table columns.
     */
    @DataProvider(name = "getUsersTableColumns")
    public static Iterator<Object[]> getTestDataIterator() {
        List<Object[]> testDataList = new ArrayList<>();
        Stream.of(UsersTableColumns.values()).forEach(column -> testDataList.add(new Object[]{column}));
        return testDataList.iterator();
    }

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
            invalidUsers = XlsFileReader.getAllRowsFromXlsSheet("InvalidCredentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invalidUsers;
    }

    private static Object[][] getUsersFromList(Roles role) throws DAOException, IOException {
        final FactoryDAO factory = new FactoryDAO();
        final Connection connection = factory.getConnection();
        final AbstractDAO userDAO = (AbstractDAO) factory.getDAO(connection, User.class);

        final List<String> usersLoginFromXls = XlsFileReader.getColumnFromXlsSheet("Users", role.toString());
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

    @DataProvider(name="generatedValidUserData")
    public static Object[][] generateValidUserData() {

        final int GENERATED_USERS_COUNT = 5;

        Object[][] usersList = new Object[GENERATED_USERS_COUNT][1];

        for (int i=0; i < GENERATED_USERS_COUNT; i++) {
            User user = new User();

            user.setLogin(generateString("NameSymbols", 1, 13).toLowerCase());
            user.setLastName(generateString("NameSymbols", 1, 13).toLowerCase());
            user.setFirstName(generateString("NameSymbols", 1, 13).toLowerCase());
            user.setPassword(generateString("PasswordSymbols", 4, 10));
            user.setEmail(generateString("EmailSymbols", 4, 8) + "@"
                    + generateString("DomainNamesSymbols", 4, 8) + "."
                    + generateString("DomainNamesSymbols", 3, 4));
            user.setRegionName(String.valueOf(Regions.getRandomRegion()));
            user.setRoleName(String.valueOf(Roles.getRandomRole()));

            usersList[i][0] = user;
        }

        return usersList;
    }

    @DataProvider(name="generatedNotValidUserData")
    public static Object[][] generateNotValidUserData() {

        final int GENERATED_USERS_COUNT = 5;

        Object[][] usersList = new Object[GENERATED_USERS_COUNT][1];
        Random randomGenerator = new Random();

        for (int i=0; i < GENERATED_USERS_COUNT; i++) {
            User user = new User();

            // generate string with digits
            if (randomGenerator.nextBoolean()) {
                user.setLogin(generateString("Digits", 1, 13));
                user.setFirstName(generateString("Digits", 1, 13));
                user.setLastName(generateString("Digits", 1, 13));
                // or with length > 13 symbols
            } else {
                user.setLogin(generateString("NameSymbols", 14, 20));
                user.setFirstName(generateString("NameSymbols", 14, 20));
                user.setLastName(generateString("NameSymbols", 14, 20));
            }

            // generate string with length < 4
            if (randomGenerator.nextBoolean()) {
                user.setPassword(generateString("PasswordSymbols", 1, 3));
                // or > 14
            } else {
                user.setPassword(generateString("PasswordSymbols", 14, 20));
            }

            user.setEmail(generateString("Digits", 2, 20));

            usersList[i][0] = user;
        }

        return usersList;
    }
}