package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.dao.UserDAO;
import com.softserveinc.edu.ita.domains.User;

/**
 * Created by i07016 on 25.06.2015.
 */
public class DBUtilityTest {
    public static void main(String[] args) {
        System.out.println(DBUtility.getByLogin("iva"));

    }
}
