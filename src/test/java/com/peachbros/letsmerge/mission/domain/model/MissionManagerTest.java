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
        Mission mission = new Mission("미션제목", "2021-05-05 15:48", "2021-05-05 15:50");

        assertThat(MissionManager.apply(mission, user, "2021-05-05 15:49")).isTrue();
    }

    @DisplayName("미션 신청 실패 : 중복 신청")
    @Test
    void applyFail() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", "2021-05-05 15:48", "2021-05-05 15:50");

        MissionManager.apply(mission, user, "2021-05-05 15:49");

        assertThat(MissionManager.apply(mission, user, "2021-05-05 15:49")).isFalse();
    }

    @DisplayName("미션 신청 실패 : 신청 기간 마감")
    @Test
    void applyFail2() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", "2021-05-05 15:48", "2021-05-05 15:50");

        assertThat(MissionManager.apply(mission, user, "2021-05-05 15:50")).isFalse();
    }
}