package com.softserveinc.edu.ita.dataprovider;


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


public class Utils {

    @DataProvider(name = "getCredibleUsers")
    public static Object[][] getCredibleUsers() throws IOException {

        DaoFactory daoFactory = new DaoFactory();
        Connection connection = daoFactory.getConnection();
        UserDao userDao = daoFactory.getUserDao(connection);

        List<String> usersLoginsFromXls = getCredibleUsersFromXls(Roles.ADMINISTRATOR, "Login1.xls", "Users");


        List<User> users = new ArrayList<>();
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


    public static List<String> getCredibleUsersFromXls(Roles roles, String xlsFile, String sheetName) throws IOException {

        File excelFile = new File("d:\\" + xlsFile);
        FileInputStream fileInputStream = new FileInputStream(excelFile);

        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet = workbook.getSheet(sheetName);

        List<String> listOfUsers = new ArrayList<>();

        Integer columnNumber = null;
        String columnName = roles.toString();

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

        return listOfUsers.stream().skip(1).collect(Collectors.toList());
    }
}