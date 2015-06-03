package com.softserveinc.edu.ita.dataproviders;


import com.softserveinc.edu.ita.dao_jdbc.Classes.User;
import com.softserveinc.edu.ita.dao_jdbc.DaoClasses.DaoFactory;
import com.softserveinc.edu.ita.dao_jdbc.DaoClasses.UserDao;
import com.softserveinc.edu.ita.enums.Roles;
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

    @DataProvider(name = "getAdministratorCredentials")
    public static Object[][] getAdministratorCredentials() {
        return getCredibleUsersFromXls(Roles.ADMINISTRATOR);
    }

    @DataProvider(name = "getCustomerCredentials")
    public static Object[][] getCutomerCredentials() {
        return getCredibleUsersFromXls(Roles.CUSTOMER);
    }

    @DataProvider(name = "getSupervisorCredentials")
    public static Object[][] getSupervisorCredentials() {
        return getCredibleUsersFromXls(Roles.SUPERVISOR);
    }

    @DataProvider(name = "getMerchandiserCredentials")
    public static Object[][] getMerchandiserCredentials() {
        return getCredibleUsersFromXls(Roles.MERCHANDISER);
    }


    public static Object[][] getCredibleUsersFromXls(Roles roles) {

        final File excelFile = new File("src\\resources\\TestData.xls");
        FileInputStream fileInputStream;
        HSSFWorkbook workbook = null;

        try {
            fileInputStream = new FileInputStream(excelFile);
            workbook = new HSSFWorkbook(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        final HSSFSheet sheet = workbook.getSheet("Users");

        List<String> listOfUsers = new ArrayList<>();

        Integer columnNumber = null;
        final String columnName = roles.toString();

        Row firstRow = sheet.getRow(0);

        for (Cell cell : firstRow) {
            if (cell.getStringCellValue().equals(columnName)) {
                columnNumber = cell.getColumnIndex();
            }
        }

        if (columnNumber != null) {
            for (Row row : sheet) {
                Cell cell = row.getCell(columnNumber);
                if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    break;
                } else {
                    listOfUsers.add(cell.toString());
                }
            }
        } else {
            System.out.println("Could not find column " + columnName + " in first row of " + excelFile.toString());
        }


        final DaoFactory daoFactory = new DaoFactory();
        final Connection connection = daoFactory.getConnection();
        UserDao userDao = daoFactory.getUserDao(connection);

        final List<String> usersLoginsFromXls = listOfUsers.stream().skip(1).collect(Collectors.toList());

        final List<User> users = new ArrayList<>();
        for (String usersLogins : usersLoginsFromXls) {
            users.add(userDao.getUsersByLogin(usersLogins));
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
}