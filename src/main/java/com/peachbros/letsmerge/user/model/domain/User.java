package com.peachbros.letsmerge.user.model.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    public static final String NO_PICTURE = "NO PICTURE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String email;
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    protected User() {
    }

    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public static User ofRoleUser(String name, String email) {
        return new User(name, email, NO_PICTURE, Role.USER);
    }

    public User update(String email, String picture) {
        this.email = email;
        this.picture = picture;

        return this;
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

    public String getRoleKey() {
        return role.getKey();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}