package com.peachbros.letsmerge.mission.model.domain;

import com.peachbros.letsmerge.user.model.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissionManagerTest {

    @DisplayName("미션 신청 성공")
    @Test
    void applySuccess() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", "2021-05-05 15:48", "2021-05-05 15:50");

        MissionManager.applyIfApplicable(mission, user, "2021-05-05 15:49");

        assertThat(MissionManager.getApplicants().get(mission).contains(user)).isTrue();
    }

    @DisplayName("미션 신청 실패 : 중복 신청")
    @Test
    void applyFail() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", "2021-05-05 15:48", "2021-05-05 15:50");

        MissionManager.applyIfApplicable(mission, user, "2021-05-05 15:49");

        assertThatThrownBy(() -> MissionManager.applyIfApplicable(mission, user, "2021-05-05 15:49"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("미션 신청 실패 : 신청 기간 마감")
    @Test
    void applyFail2() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", "2021-05-05 15:48", "2021-05-05 15:50");

        assertThatThrownBy(() -> MissionManager.applyIfApplicable(mission, user, "2021-05-05 15:50"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}