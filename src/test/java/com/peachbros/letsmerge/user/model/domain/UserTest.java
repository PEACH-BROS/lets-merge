package com.peachbros.letsmerge.user.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @DisplayName("동일한 유저인지 확인")
    @Test
    void testEquals() {
        User user1 = User.ofRoleUser("코일", "coyle@gmail.com");
        User user2 = User.ofRoleUser("코일", "coyle@gmail.com");

        assertThat(user1.equals(user2)).isTrue();
    }

    @DisplayName("Getter 테스트")
    @Test
    void getterTest() {
        User user = new User("코일", "slowcoyole@gmail.com", User.NO_PICTURE, Role.USER);
        assertAll(
                () -> assertEquals(user.getName(), "코일"),
                () -> assertEquals(user.getEmail(), "slowcoyole@gmail.com"),
                () -> assertEquals(user.getPicture(), User.NO_PICTURE),
                () -> assertEquals(user.getRole(), Role.USER),
                () -> assertEquals(user.getRoleKey(), "ROLE_USER")
        );
    }

    @DisplayName("user update 테스트")
    @Test
    void updateTest() {
        User user = new User("코일", "slowcoyole@gmail.com", User.NO_PICTURE, Role.USER);

        user.update("anotherEmail@gmail.com", "anotherPicture");

        assertAll(
                () -> assertEquals(user.getEmail(), "anotherEmail@gmail.com"),
                () -> assertEquals(user.getPicture(), "anotherPicture")
        );
    }
}