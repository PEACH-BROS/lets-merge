package com.peachbros.letsmerge.user.model.domain;

import java.util.List;

public class Group {
    private final List<User> users;

    public Group(List<User> users) {
//        validate(users)
        this.users = users;
    }

    public void add(User user) {
        this.users.add(user);
    }

    public int size() {
        return users.size();
    }
}
