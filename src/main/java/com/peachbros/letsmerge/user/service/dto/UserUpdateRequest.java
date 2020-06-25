package com.peachbros.letsmerge.user.service.dto;

public class UserUpdateRequest {
    private final String name;
    private final String email;
    private final String picture;

    public UserUpdateRequest(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
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
