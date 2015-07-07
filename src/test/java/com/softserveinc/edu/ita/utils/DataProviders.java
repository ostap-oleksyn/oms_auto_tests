package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.enums.administration_page.UsersTableColumns;
import com.softserveinc.edu.ita.enums.item_management_page.ItemFilter;
import com.softserveinc.edu.ita.enums.item_management_page.ProductsTableColumns;
import com.softserveinc.edu.ita.enums.ordering_page.OrdersTableColumns;
import org.testng.annotations.DataProvider;
import org.testng.collections.Lists;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static com.softserveinc.edu.ita.utils.DBUtility.getByLogin;
import static com.softserveinc.edu.ita.utils.RandomUtil.getRandomString;
import static com.softserveinc.edu.ita.utils.XlsFileReader.getAllRowsFromXlsSheet;

/**
 * Class with data provider methods.
 */
public final class DataProviders {

    /**
     * returns searchterms and users from xls file
     *
     * @return
     */
    @DataProvider(name = "getOrderSearchTestData")
    public static Object[][] getTestData() throws IOException, ClassNotFoundException {
        Object[][] searchTerms = XlsFileReader.getAllRowsFromXlsSheet("OrderSearchTerms");

        return new Object[][]{new Object[]{DBUtility.getMerchandiser(), searchTerms[0][0]},
                new Object[]{DBUtility.getMerchandiser(), searchTerms[1][0]},
                new Object[]{DBUtility.getMerchandiser(), searchTerms[2][0]},
                new Object[]{DBUtility.getCustomer(), searchTerms[0][0]},
                new Object[]{DBUtility.getCustomer(), searchTerms[1][0]},
                new Object[]{DBUtility.getCustomer(), searchTerms[2][0]}};
    }

    /**
     * Returns all users with Administrator and Customer role from database
     *
     * @return
     */
    @DataProvider(name = "getMerchandiserAndCustomer")
    public static Object[][] getMerchandiserAndCustomerCredentials() throws IOException, DAOException {
        List<Object[]> usersList = Lists.newArrayList();
        usersList.addAll(Arrays.asList(getCustomerCredentials()));
        usersList.addAll(Arrays.asList(getMerchandiserCredentials()));
        return usersList.toArray(new Object[usersList.size()][]);
    }

    /**
     * returns searchterms from xls file
     */
    @DataProvider(name = "getSearchTerms")

    public static Object[][] getSearchTerms() throws IOException {
        return XlsFileReader.getAllRowsFromXlsSheet("searchTerms");
    }

    /**
     * returns searchterms from xls file
     */
    @DataProvider(name = "getOrderSearchTerms")
    public static Object[][] getOrderSearchTerms() throws IOException {
        return XlsFileReader.getAllRowsFromXlsSheet("OrderSearchTerms");
    }

    /**
     * Returns names of 'Users' table columns.
     */
    @DataProvider(name = "getUsersTableColumns")
    public static Iterator<Object[]> getUsersDataIterator() {
        final List<Object[]> testDataList = new ArrayList<>();
        Stream.of(UsersTableColumns.values()).forEach(column -> testDataList.add(new Object[]{column}));
        return testDataList.iterator();
    }

    /**
     * Returns names of 'Orders' table columns.
     */
    @DataProvider(name = "getOrdersTableColumns")
    public static Iterator<Object[]> getOrdersDataIterator() {
        final List<Object[]> testDataList = new ArrayList<>();
        Stream.of(OrdersTableColumns.values()).forEach(column -> testDataList.add(new Object[]{column}));
        return testDataList.iterator();
    }

    /**
     * Returns names of 'Products' table columns.
     */
    @DataProvider(name = "getProductsTableColumns")
    public static Iterator<Object[]> getProductsDataIterator() {
        final List<Object[]> testDataList = new ArrayList<>();
        Stream.of(ProductsTableColumns.values()).forEach(column -> testDataList.add(new Object[]{column}));
        return testDataList.iterator();
    }

    /**
     * Returns 'Products' table filters.
     */
    @DataProvider(name = "getProductTablesFilters")
    public static Iterator<Object[]> getProductsTableFilters() {
        final List<Object[]> testDataList = new ArrayList<>();
        Stream.of(ItemFilter.values()).forEach(filter -> testDataList.add(new Object[]{filter}));
        return testDataList.iterator();
    }

    /**
     * Returns all users with Administrator role from database;
     */
    @DataProvider(name = "getAdministrators")
    public static Object[][] getAdministratorCredentials() throws IOException, DAOException {
        return getUsersFromList(Roles.ADMINISTRATOR);
    }

    /**
     * Returns all users with Customer role from database;
     */
    @DataProvider(name = "getCustomers")
    public static Object[][] getCustomerCredentials() throws IOException, DAOException {
        return getUsersFromList(Roles.CUSTOMER);
    }

    /**
     * Returns all users with Supervisor role from database;
     */
    @DataProvider(name = "getSupervisors")
    public static Object[][] getSupervisorCredentials() throws IOException, DAOException {
        return getUsersFromList(Roles.SUPERVISOR);
    }

    /**
     * Returns all users with Merchandiser role from database;
     */
    @DataProvider(name = "getMerchandisers")
    public static Object[][] getMerchandiserCredentials() throws IOException, DAOException {
        return getUsersFromList(Roles.MERCHANDISER);
    }

    /**
     * Returns all roles users;
     */
    @DataProvider(name = "getAllRoles")
    public static Object[][] getAllRolesUsersFromXLS() throws IOException, DAOException {
        return getUsersFromList(Roles.ALL);
    }

    /**
     * Returns invalid users credentials;
     */
    @DataProvider(name = "getInvalidUsers")
    public static Object[][] getInvalidCredentials() throws IOException {
        return XlsFileReader.getAllRowsFromXlsSheet("InvalidCredentials");
    }

    /**
     * Returns list of users from database extracted by login from xls test data file
     */
    private static Object[][] getUsersFromList(final Roles role) throws DAOException, IOException {
        final List<String> usersLoginFromXls = XlsFileReader.getColumnFromXlsSheet("Users", role.getRoleName());
        final List<User> users = new ArrayList<>();

        for (final String usersLogin : usersLoginFromXls) {
            users.add(getByLogin(usersLogin));
        }

        final Object[][] usersList = new Object[users.size()][1];

        for (int i = 0; i < users.size(); i++) {
            usersList[i][0] = users.get(i);
        }

        return usersList;
    }

    /**
     * Returns list of users with randomly generated valid user data
     */
    @DataProvider(name = "generatedValidUserData")
    public static Object[][] generateValidUserData() {

        final int GENERATED_USERS_COUNT = 5;
        final Random randomGenerator = new Random();

        Object[][] usersList = new Object[GENERATED_USERS_COUNT][1];

        for (int i = 0; i < GENERATED_USERS_COUNT; i++) {
            final int roleReference = randomGenerator.nextInt(3) + 1;
            final int regionReference = randomGenerator.nextInt(3) + 1;

            final User user = User.newBuilder()
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

    /**
     * Returns list of users with randomly generated invalid user data
     */
    @DataProvider(name = "generatedNotValidUserData")
    public static Object[][] generateNotValidUserData() {

        final int GENERATED_USERS_COUNT = 5;
        String login;
        String firstName;
        String lastName;
        String password;
        String email;

        Object[][] usersList = new Object[GENERATED_USERS_COUNT][1];
        final Random randomGenerator = new Random();

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

            final User user = User.newBuilder()
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

    /**
     * Returns randomly generated data for user editing
     */
    @DataProvider(name = "getUserEditData")
    public static Object[][] getUserEditData() throws IOException {
        return XlsFileReader.getAllRowsFromXlsSheet("userEditData");
    }

    @DataProvider(name = "products")
    public static Object[][] getProducts() throws IOException {
        return getAllRowsFromXlsSheet("Products");
    }
}


