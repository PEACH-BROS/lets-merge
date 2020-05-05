package com.peachbros.letsmerge.mission.domain.model;

import com.peachbros.letsmerge.user.model.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissionManagerTest {

    @DisplayName("미션 신청 성공")
    @Test
    void applySuccess() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", "2020-05-05 15:49");

        assertThat(MissionManager.apply(mission, user)).isTrue();
    }

    @DisplayName("미션 신청 실패")
    @Test
    void applyFail() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", "2020-05-05 15:49");

        MissionManager.apply(mission, user);

        assertThat(MissionManager.apply(mission, user)).isFalse();
    }
}