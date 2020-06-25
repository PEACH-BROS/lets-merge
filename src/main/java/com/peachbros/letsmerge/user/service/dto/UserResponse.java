package com.peachbros.letsmerge.user.service.dto;

import com.peachbros.letsmerge.user.model.domain.Role;
import com.peachbros.letsmerge.user.model.domain.User;

public class UserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String picture;
    private final Role role;

    private UserResponse(Long id, String name, String email, String picture, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPicture(), user.getRole());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public Role getRole() {
        return role;
    }
}
