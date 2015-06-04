package com.softserveinc.edu.ita.dataproviders;

import com.softserveinc.edu.ita.dao_jdbc.classes.User;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.DaoFactory;
import com.softserveinc.edu.ita.dao_jdbc.dao_classes.PersistException;
import com.softserveinc.edu.ita.dao_jdbc.interfaces.IGenericDao;
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


public class DataProviders {

    private static HSSFSheet sheet;
    private static HSSFWorkbook workbook;


    @DataProvider(name = "getAdministrators")
    public static Object[][] getAdministratorCredentials() {
        Object[][] credentials = null;
        try {
            credentials = getUsersByRoleFromXls(Roles.ADMINISTRATOR);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    @DataProvider(name = "getCustomers")
    public static Object[][] getCutomerCredentials() {
        Object[][] credentials = null;
        try {
            credentials = getUsersByRoleFromXls(Roles.CUSTOMER);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    @DataProvider(name = "getSupervisors")
    public static Object[][] getSupervisorCredentials() {
        Object[][] credentials = null;
        try {
            credentials = getUsersByRoleFromXls(Roles.SUPERVISOR);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    @DataProvider(name = "getMerchandisers")
    public static Object[][] getMerchandiserCredentials() {
        Object[][] credentials = null;
        try {
            credentials = getUsersByRoleFromXls(Roles.MERCHANDISER);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    @DataProvider(name = "getInvalidUsers")
    public static Object[][] getInvalidCredentials() {
        return getUsersFromXls("InvalidCredentials");
    }

    @DataProvider(name = "getAllRoles")
    public static Object[][] getAllUserRolesFromXLS() {
        return getUsersFromXls("AllRolesCredentials");
    }

    private static Object[][] getUsersByRoleFromXls(Roles roles) throws PersistException {

        final File excelFile = new File("src//resources//TestData.xls");
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

        final DaoFactory factory = new DaoFactory();
        final Connection connection = factory.getContext();
        final IGenericDao userDao = factory.getDao(connection, User.class);

        final List<String> usersLoginsFromXls = listOfUsers.stream().skip(1).collect(Collectors.toList());

        final List<User> users = new ArrayList<>();
        for (String usersLogins : usersLoginsFromXls) {
            users.add((User) userDao.getByLogin(usersLogins));
        }

        Object[][] credibleUserCredentials = new Object[users.size()][2];
        for (int i = 0; i < users.size(); i++) {
            credibleUserCredentials[i][0] = users.get(i).getLogin();
        }
        for (int i = 0; i < users.size(); i++) {
            credibleUserCredentials[i][1] = users.get(i).getPassword();
        }

        return credibleUserCredentials;
    }

    private static Object[][] getUsersFromXls(String sheetName) {
        final File excelFile = new File("src//resources//TestData.xls");
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream(excelFile);
            workbook = new HSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheet(sheetName);

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