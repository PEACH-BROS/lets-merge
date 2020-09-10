package com.peachbros.letsmerge.matcher.model.service.dto;

import com.peachbros.letsmerge.user.model.domain.Groups;

import java.util.List;
import java.util.stream.Collectors;

public class GroupsResponse {
    private List<GroupResponse> groupsResponse;

    protected GroupsResponse() {
    }

    public GroupsResponse(Groups groups) {
        this.groupsResponse = groups.getGroups().stream()
                .map(GroupResponse::new)
                .collect(Collectors.toList());
    }

    public List<GroupResponse> getGroupsResponse() {
        return groupsResponse;
    }
}
