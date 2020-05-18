package com.peachbros.letsmerge.matcher.model.domain;

import com.peachbros.letsmerge.user.model.domain.Groups;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.domain.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MatcherTest {
    @DisplayName("그룹 나누기 - 딱 떨어지는 경우")
    @Test
    void match() {
        Users users = createUsers(6);
        Groups groups = Matcher.match(users);

        assertThat(groups.getGroups().get(0).size()).isEqualTo(3);
        assertThat(groups.getGroups().get(1).size()).isEqualTo(3);
    }

    @DisplayName("그룹 나누기 - 나머지 1")
    @Test
    void match2() {
        Users users = createUsers(7);
        Groups groups = Matcher.match(users);

        assertThat(groups.getGroups().get(0).size()).isEqualTo(4);
        assertThat(groups.getGroups().get(1).size()).isEqualTo(3);
    }

    @DisplayName("그룹 나누기 - 나머지 2")
    @Test
    void match3() {
        Users users = createUsers(8);
        Groups groups = Matcher.match(users);

        assertThat(groups.getGroups().get(0).size()).isEqualTo(3);
        assertThat(groups.getGroups().get(1).size()).isEqualTo(3);
        assertThat(groups.getGroups().get(2).size()).isEqualTo(2);
    }

    private Users createUsers(int size) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            users.add(new User(String.valueOf(i), "dummy@gmail.com"));
        }
        return new Users(users);
    }
}