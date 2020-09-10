package com.peachbros.letsmerge.user.model.domain;

import com.peachbros.letsmerge.common.exception.NoSuchValueException;

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
        firstGroup.addUser(user);
    }

    public Group findGroupContainingUser(Long userId) {
        return groups.stream().filter(group -> group.hasUser(userId)).findFirst()
                .orElseThrow(() -> new NoSuchValueException("해당 사용자가 속한 그룹이 존재하지 않습니다."));
    }

    public List<Group> getGroups() {
        return Collections.unmodifiableList(groups);
    }
}
