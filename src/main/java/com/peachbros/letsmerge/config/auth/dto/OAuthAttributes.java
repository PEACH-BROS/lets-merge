package com.peachbros.letsmerge.config.auth.dto;

import com.peachbros.letsmerge.user.model.domain.User;

import java.util.Map;

public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .setName((String) attributes.get("name"))
                .setEmail((String) attributes.get("email"))
                .setPicture((String) attributes.get("picture"))
                .setAttributes(attributes)
                .setNameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuthAttributesBuilder builder() {
        return new OAuthAttributesBuilder();
    }

    public User toEntity() {
        return new User(name, email);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getNameAttributeKey() {
        return nameAttributeKey;
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
