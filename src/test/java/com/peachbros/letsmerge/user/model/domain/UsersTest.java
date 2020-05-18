package com.peachbros.letsmerge.user.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {
    @DisplayName("생성자 테스트")
    @Test
    void usersInit1() {
        assertThat(new Users()).isInstanceOf(Users.class);
    }

    @DisplayName("생성자 테스트")
    @Test
    void usersInit2() {
        assertThat(new Users(Collections.emptyList())).isInstanceOf(Users.class);
    }

    @DisplayName("Users에 아이템 존재하는지 확인")
    @Test
    void contains() {
        User user1 = new User("엘리", "elly@gmail.com");
        User user2 = new User("코일", "coyle@gmail.com");
        User user3 = new User("비밥", "bibab@gmail.com");

        Users users = new Users(Arrays.asList(user1, user2, user3));

        assertThat(users.contains(user3)).isTrue();
    }

    @DisplayName("Users에 아이템 존재하는지 확인")
    @Test
    void notContains() {
        User user1 = new User("엘리", "elly@gmail.com");
        User user2 = new User("코일", "coyle@gmail.com");
        User user3 = new User("비밥", "bibab@gmail.com");
        User newUser = new User("?", "?@gmail.com");

        Users users = new Users(Arrays.asList(user1, user2, user3));

        assertThat(users.contains(newUser)).isFalse();
    }

}