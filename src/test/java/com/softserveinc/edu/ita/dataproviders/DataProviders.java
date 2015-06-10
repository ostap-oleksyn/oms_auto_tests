package com.softserveinc.edu.ita.dataproviders;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.AbstractDAO;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DAOException;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.FactoryDAO;
import com.softserveinc.edu.ita.enums.Roles;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.softserveinc.edu.ita.utils.PropertyLoaderUtil.getProperty;


public class DataProviders {

    private static HSSFSheet sheet;
    private static HSSFWorkbook workbook;
    private static final String CONFIG_PROPERTIES_FILE = "config.properties";

    /**
     * Returns all users with Administrator role from database;
     */
    @DataProvider(name = "getAdministrators")
    public static Object[][] getAdministratorCredentials() {
        Object[][] credentials = null;
        try {
            credentials = getUsersByRoleFromXls(Roles.ADMINISTRATOR);
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
            credentials = getUsersByRoleFromXls(Roles.CUSTOMER);
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
            credentials = getUsersByRoleFromXls(Roles.SUPERVISOR);
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
            credentials = getUsersByRoleFromXls(Roles.MERCHANDISER);
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
            allRolesUsers = getUsersByRoleFromXls(Roles.ALL);
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
            invalidUsers = getInvalidUsersFromXls();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invalidUsers;
    }

    private static Object[][] getUsersByRoleFromXls(Roles roles) throws IOException, DAOException {

        final File excelFile = new File(getProperty("testDataFile", CONFIG_PROPERTIES_FILE));
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream(excelFile);
            workbook = new HSSFWorkbook(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheet("Users");

        final List<String> listOfUsers = new ArrayList<>();

        Integer columnNumber = null;
        final String columnName = roles.toString();

        final Row firstRow = sheet.getRow(0);

        for (Cell cell : firstRow) {
            if (cell.getStringCellValue().equals(columnName)) {
                columnNumber = cell.getColumnIndex();
            }
        }

        if (columnNumber != null) {
            for (Row row : sheet) {
                final Cell cell = row.getCell(columnNumber);
                if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    break;
                } else {
                    listOfUsers.add(cell.toString());
                }
            }
        } else {
            System.out.println("Could not find column " + columnName + " in first row of " + excelFile.toString());
        }

        FactoryDAO factory = new FactoryDAO();
        Connection connection = factory.getConnection();
        AbstractDAO userDAO = (AbstractDAO) factory.getDAO(connection, User.class);

        final List<String> usersLoginFromXls = listOfUsers.stream().skip(1).collect(Collectors.toList());

        final List<User> users = new ArrayList<>();
        for (String usersLogin : usersLoginFromXls) {
            users.add((User) userDAO.getByLogin(usersLogin));
        }

        Object[][] credibleUserCredentials = new Object[users.size()][1];

        for (int i = 0; i < users.size(); i++) {
            credibleUserCredentials[i][0] = users.get(i);
        }

        return credibleUserCredentials;
    }

    private static Object[][] getInvalidUsersFromXls() throws IOException {
        final File excelFile = new File(getProperty("testDataFile", CONFIG_PROPERTIES_FILE));
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream(excelFile);
            workbook = new HSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheet("InvalidCredentials");

        final int numberOfRows = sheet.getLastRowNum();
        final int numberOfColumns = sheet.getRow(0).getLastCellNum();

        final String[][] xlsData = new String[numberOfRows][numberOfColumns];
        String cellValue;

        for (int i = 1; i <= numberOfRows; i++) {
            final HSSFRow row = sheet.getRow(i);

            for (int j = 0; j < numberOfColumns; j++) {
                final HSSFCell cell = row.getCell(j);
                final int cellType = cell.getCellType();

                if (cellType == HSSFCell.CELL_TYPE_FORMULA) {
                    throw new RuntimeException("Cannot process a formula. Please change field to result of formula.");
                } else {
                    cellValue = String.valueOf(cell);
                    xlsData[i - 1][j] = cellValue;
                }
            }
        }
        return xlsData;
    }
}