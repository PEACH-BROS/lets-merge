package com.peachbros.letsmerge.user.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Group {
    private List<User> users;

    public Group(List<User> users) {
        this.users = users;
    }

    public void add(User user) {
        this.users = new ArrayList<>(users);
        this.users.add(user);
    }

    public boolean hasUser(Long userId) {
        //TODO: User.isSameUserWith로 변경하기
        Optional<User> first = users.stream().filter(user -> user.getId().equals(userId)).findFirst();
        return first.isPresent();
    }

    public int size() {
        return users.size();
    }
}