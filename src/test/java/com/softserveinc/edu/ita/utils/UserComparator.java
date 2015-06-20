package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.domains.User;
import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        return user1.getLogin().compareToIgnoreCase(user2.getLogin());
    }
}
