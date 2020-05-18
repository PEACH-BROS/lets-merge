package com.peachbros.letsmerge.user.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("동일한 유저인지 확인")
    @Test
    void testEquals() {
        User user1 = new User("코일", "coyle@gmail.com");
        User user2 = new User("코일", "coyle@gmail.com");

        assertThat(user1.equals(user2)).isTrue();
    }
}