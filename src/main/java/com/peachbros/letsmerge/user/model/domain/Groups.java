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

    public List<Group> getGroups() {
        return Collections.unmodifiableList(groups);
    }
}
