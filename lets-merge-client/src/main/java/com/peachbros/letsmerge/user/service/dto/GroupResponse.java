package com.peachbros.letsmerge.user.service.dto;

import com.peachbros.letsmerge.user.model.domain.Group;

public class GroupResponse {
    private Group group;

    protected GroupResponse() {
    }

    private GroupResponse(Group group) {
        this.group = group;
    }

    public static GroupResponse of(Group group) {
        return new GroupResponse(group);
    }

    public Group getGroup() {
        return group;
    }
}
