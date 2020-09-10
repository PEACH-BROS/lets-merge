package com.peachbros.letsmerge.matcher.model.service.dto;

import com.peachbros.letsmerge.user.model.domain.User;

public class UserResponse {

    private String name;
    private String email;
    private String picture;

    public UserResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
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
}
