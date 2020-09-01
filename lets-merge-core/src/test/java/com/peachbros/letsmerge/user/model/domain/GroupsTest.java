package com.peachbros.letsmerge.user.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class GroupsTest {
    @DisplayName("Groups 생성자")
    @Test
    void constructor() {
        Groups groups = new Groups(new ArrayList<>());

        assertThat(groups).isInstanceOf(Groups.class);
    }

    @DisplayName("Groups에 group 추가")
    @Test
    void add() {
        Groups groups = new Groups(new ArrayList<>());
        groups.add(new Group(new ArrayList<>()));
        assertThat(groups.getGroups().size()).isEqualTo(1);
    }
}