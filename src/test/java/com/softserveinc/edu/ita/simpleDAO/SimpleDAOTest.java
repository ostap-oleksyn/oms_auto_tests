package com.softserveinc.edu.ita.simpleDAO;

import com.softserveinc.edu.ita.domains.User;

import java.util.List;

import static com.softserveinc.edu.ita.utils.PropertyLoader.getProperty;

/**
 * Created by i07016 on 17.06.2015.
 */
public class SimpleDAOTest {
    public static void main(String[] args) {

        SimpleDAOUser simpleDAOUser = new SimpleDAOUser();
        List<User> usersList = simpleDAOUser.getAllUsers();
        System.out.println(usersList.get(0));

        User user = simpleDAOUser.getUser("iva");
        System.out.println(user);

        simpleDAOUser.updateUser(user);

        user = simpleDAOUser.getUser("iva");
        System.out.println(user);

    }
}
