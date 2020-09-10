package com.peachbros.letsmerge.matcher.model.service.dto;

import com.peachbros.letsmerge.user.model.domain.Group;

import java.util.List;
import java.util.stream.Collectors;

public class GroupResponse {
    private List<UserResponse> userResponses;

    protected GroupResponse() {
    }

    public GroupResponse(Group group) {
        this.userResponses = group.getUsers()
                .stream().map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getUserResponses() {
        return userResponses;
    }
}
