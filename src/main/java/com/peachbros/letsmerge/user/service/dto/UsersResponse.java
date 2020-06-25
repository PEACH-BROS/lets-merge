package com.peachbros.letsmerge.user.service.dto;

import com.peachbros.letsmerge.user.model.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UsersResponse {
    private List<UserResponse> usersResponse;

    private UsersResponse() {
    }

    private UsersResponse(List<UserResponse> usersResponse) {
        this.usersResponse = usersResponse;
    }

    public List<UserResponse> getUsersResponse() {
        return usersResponse;
    }

    public static UsersResponse of(List<User> users) {
        List<UserResponse> userResponses = users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());

        return new UsersResponse(userResponses);
    }
}
