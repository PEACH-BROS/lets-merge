package com.peachbros.letsmerge.user.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private final List<User> users;

    public Users() {
        this(new ArrayList<>());
    }

    public Users(List<User> users) {
        this.users = users;
    }


    public boolean contains(User target) {
        return users.contains(target);
    }

    public void add(User target) {
        users.add(target);
    }
}
