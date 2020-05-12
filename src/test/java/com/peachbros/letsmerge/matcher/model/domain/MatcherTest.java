package com.peachbros.letsmerge.matcher.model.domain;

import com.peachbros.letsmerge.user.model.domain.Group;
import com.peachbros.letsmerge.user.model.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MatcherTest {
    private final List<User> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        users.clear();
        users.add(new User("코일", "slowcoyle@gmail.com"));
        users.add(new User("엘리", "yebink@gmail.com"));
        users.add(new User("비밥", "pci@gmail.com"));
        users.add(new User("타미", "tami@gmail.com"));
        users.add(new User("터틀", "turtle@gmail.com"));
        users.add(new User("카일", "kyle@gmail.com"));
    }

    @DisplayName("그룹 나누기 - 딱 떨어지는 경우")
    @Test
    void match() {
        List<Group> groups = Matcher.match(users);

        assertThat(groups.get(0).size()).isEqualTo(3);
        assertThat(groups.get(1).size()).isEqualTo(3);
    }

    @DisplayName("그룹 나누기 - 나머지 1")
    @Test
    void match2() {
        users.add(new User("카일", "kyle@gmail.com"));

        List<Group> groups = Matcher.match(users);

        assertThat(groups.get(0).size()).isEqualTo(4);
        assertThat(groups.get(1).size()).isEqualTo(3);
    }

    @DisplayName("그룹 나누기 - 나머지 2")
    @Test
    void match3() {
        users.add(new User("카일", "kyle@gmail.com"));
        users.add(new User("카일", "kyle@gmail.com"));

        List<Group> groups = Matcher.match(users);

        assertThat(groups.get(0).size()).isEqualTo(3);
        assertThat(groups.get(1).size()).isEqualTo(3);
        assertThat(groups.get(2).size()).isEqualTo(2);
    }
}