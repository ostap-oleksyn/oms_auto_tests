package com.softserveinc.edu.ita.utils;

import com.softserveinc.edu.ita.domains.UserFromView;

import java.util.Comparator;

public class UserFromViewComparator implements Comparator<UserFromView> {

    @Override
    public int compare(UserFromView user1, UserFromView user2) {
        return user1.getLogin().compareToIgnoreCase(user2.getLogin());
    }
}
