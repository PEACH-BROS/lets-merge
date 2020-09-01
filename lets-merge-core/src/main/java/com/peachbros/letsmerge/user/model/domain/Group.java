package com.peachbros.letsmerge.user.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<User> users;

    public Group(List<User> users) {
        this.users = users;
    }

    public void add(User user) {
        this.users = new ArrayList<>(users);
        this.users.add(user);
    }

    public int size() {
        return users.size();
    }
}