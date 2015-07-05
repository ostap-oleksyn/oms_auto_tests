
package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.enums.administration_page.UsersTableColumns;
import com.softserveinc.edu.ita.enums.ordering_page.OrdersTableColumns;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static com.softserveinc.edu.ita.utils.DBUtility.getByLogin;
import static com.softserveinc.edu.ita.utils.RandomUtil.getRandomString;
import static com.softserveinc.edu.ita.utils.XlsFileReader.getAllRowsFromXlsSheet;


public final class DataProviders {

    /**
     * returns searchterms from xls file
     *
     * @return
     */
    @DataProvider(name = "getSearchTerms")
    public static Object[][] getSearchTerms() {
        Object[][] searchTerms = null;
        try {
            searchTerms = getAllRowsFromXlsSheet("searchTerms");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchTerms;
    }

    /**
     * returns searchterms from xls file
     *
     * @return
     */
    @DataProvider(name = "getOrderSearchTerms")
    public static Object[][] getOrderSearchTerms() {
        Object[][] searchTerms = null;
        try {
            searchTerms = getAllRowsFromXlsSheet("OrderSearchTerms");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchTerms;
    }

    /**
     * Returns names of 'Users' table columns.
     */
    @DataProvider(name = "getUsersTableColumns")
    public static Iterator<Object[]> getUsersDataIterator() {
        List<Object[]> testDataList = new ArrayList<>();
        Stream.of(UsersTableColumns.values()).forEach(column -> testDataList.add(new Object[]{column}));
        return testDataList.iterator();
    }

    /**
     * Returns names of 'Users' table columns.
     */
    @DataProvider(name = "getOrdersTableColumns")
    public static Iterator<Object[]> getOrdersDataIterator() {
        List<Object[]> testDataList = new ArrayList<>();
        Stream.of(OrdersTableColumns.values()).forEach(column -> testDataList.add(new Object[]{column}));
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
            invalidUsers = getAllRowsFromXlsSheet("InvalidCredentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invalidUsers;
    }

    private static Object[][] getUsersFromList(Roles role) throws DAOException, IOException {
        final List<String> usersLoginFromXls = XlsFileReader.getColumnFromXlsSheet("Users", role.getRoleName());
        final List<User> users = new ArrayList<>();

        for (String usersLogin : usersLoginFromXls) {
            users.add(getByLogin(usersLogin));
        }

        final Object[][] usersList = new Object[users.size()][1];

        for (int i = 0; i < users.size(); i++) {
            usersList[i][0] = users.get(i);
        }

        return usersList;
    }

    @DataProvider(name = "generatedValidUserData")
    public static Object[][] generateValidUserData() {

        final int GENERATED_USERS_COUNT = 5;
        Random randomGenerator = new Random();

        Object[][] usersList = new Object[GENERATED_USERS_COUNT][1];

        for (int i = 0; i < GENERATED_USERS_COUNT; i++) {
            int roleReference = randomGenerator.nextInt(3) + 1;
            int regionReference = randomGenerator.nextInt(3) + 1;

            User user = User.newBuilder()
                    .withoutId()
                    .withStatus(1)
                    .withFirstName(getRandomString("NameSymbols", 1, 13).toLowerCase())
                    .withLastName(getRandomString("NameSymbols", 1, 13).toLowerCase())
                    .withLogin(getRandomString("NameSymbols", 1, 13).toLowerCase())
                    .withPassword(getRandomString("PasswordSymbols", 4, 10))
                    .withEmail(getRandomString("EmailSymbols", 4, 8) + "@"
                            + getRandomString("DomainNamesSymbols", 4, 8) + "."
                            + getRandomString("DomainNamesSymbols", 3, 4))
                    .withRoleReference(roleReference)
                    .withoutCustomerTypeReference()
                    .withRegionReference(regionReference)
                    .build();

            usersList[i][0] = user;
        }

        return usersList;
    }

    @DataProvider(name = "generatedNotValidUserData")
    public static Object[][] generateNotValidUserData() {

        final int GENERATED_USERS_COUNT = 5;
        String login;
        String firstName;
        String lastName;
        String password;
        String email;

        Object[][] usersList = new Object[GENERATED_USERS_COUNT][1];
        Random randomGenerator = new Random();

        for (int i = 0; i < GENERATED_USERS_COUNT; i++) {

            // generate string with digits
            if (randomGenerator.nextBoolean()) {
                login = getRandomString("Digits", 1, 13);
                firstName = getRandomString("Digits", 1, 13);
                lastName = getRandomString("Digits", 1, 13);
                // or with length > 13 symbols
            } else {
                login = getRandomString("NameSymbols", 14, 20);
                firstName = getRandomString("NameSymbols", 14, 20);
                lastName = getRandomString("NameSymbols", 14, 20);
            }

            // generate string with length < 4
            if (randomGenerator.nextBoolean()) {
                password = getRandomString("PasswordSymbols", 1, 3);
                // or > 14
            } else {
                password = getRandomString("PasswordSymbols", 14, 20);
            }

            email = getRandomString("Digits", 2, 20);

            User user = User.newBuilder()
                    .withoutId()
                    .withoutStatus()
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withLogin(login)
                    .withPassword(password)
                    .withEmail(email)
                    .withoutRoleReference()
                    .withoutCustomerTypeReference()
                    .withoutRegionReference()
                    .build();

            usersList[i][0] = user;
        }

        return usersList;
    }

    @DataProvider(name = "getUserEditData")
    public static Object[][] getUserEditData() {
        Object[][] userEditData = null;
        try {
            userEditData = getAllRowsFromXlsSheet("userEditData");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userEditData;
    }

    @DataProvider(name = "products")
    public static Object[][] getProducts() throws IOException {
        return getAllRowsFromXlsSheet("Products");
    }
}