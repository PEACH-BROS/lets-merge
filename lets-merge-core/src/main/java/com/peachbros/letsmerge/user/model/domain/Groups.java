package com.peachbros.letsmerge.user.model.domain;

import java.util.Collections;
import java.util.List;

public class Groups {
    private final List<Group> groups;

    public Groups(List<Group> groups) {
        this.groups = groups;
    }

    public void add(Group group) {
        groups.add(group);
    }

    public void addUserInFirstGroup(User user) {
        Group firstGroup = groups.get(0);
        firstGroup.add(user);
    }

    public List<Group> getGroups() {
        return Collections.unmodifiableList(groups);
    }
}
