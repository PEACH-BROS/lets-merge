package com.peachbros.letsmerge.user.service.dto;

import java.util.List;

public class UsersResponse {
    private final List<UserResponse> usersResponse;

    public UsersResponse(List<UserResponse> usersResponse) {
        this.usersResponse = usersResponse;
    }

    public List<UserResponse> getUsersResponse() {
        return usersResponse;
    }
}
