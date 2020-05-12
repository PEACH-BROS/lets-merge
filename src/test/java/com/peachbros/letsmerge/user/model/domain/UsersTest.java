package com.peachbros.letsmerge.user.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {
    @DisplayName("생성자 테스트")
    @Test
    void usersInit() {
        assertThat(new Users(Collections.emptyList())).isInstanceOf(Users.class);
    }
}