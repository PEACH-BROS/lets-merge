package com.peachbros.letsmerge.user.model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Users {
    private final List<User> users;

    public Users() {
        this(new ArrayList<>());
    }

    public Users(List<User> users) {
        this.users = users;
    }

    public static Users of(List<User> users) {
        return new Users(users);
    }

    public boolean contains(User target) {
        return users.contains(target);
    }

    public void add(User target) {
        users.add(target);
    }

    public void shuffle() {
        Collections.shuffle(this.users);
    }

    public int size() {
        return users.size();
    }

    public User get(int index) {
        return users.get(index);
    }
}
