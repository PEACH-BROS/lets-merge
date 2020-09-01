package com.peachbros.letsmerge.config.auth.dto;

import java.util.Map;

public class OAuthAttributesBuilder {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public OAuthAttributesBuilder setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public OAuthAttributesBuilder setNameAttributeKey(String nameAttributeKey) {
        this.nameAttributeKey = nameAttributeKey;
        return this;
    }

    public OAuthAttributesBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public OAuthAttributesBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public OAuthAttributesBuilder setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public OAuthAttributes build() {
        return new OAuthAttributes(attributes, nameAttributeKey, name, email, picture);
    }
}
