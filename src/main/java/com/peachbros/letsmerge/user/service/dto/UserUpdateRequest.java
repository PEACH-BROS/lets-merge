package com.peachbros.letsmerge.user.service.dto;

public class UserUpdateRequest {
    private String email;
    private String picture;

    private UserUpdateRequest() {
    }

    public UserUpdateRequest(String email, String picture) {
        this.email = email;
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }
}
