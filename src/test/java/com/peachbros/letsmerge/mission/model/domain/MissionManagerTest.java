package com.peachbros.letsmerge.mission.model.domain;

import com.peachbros.letsmerge.user.model.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissionManagerTest {

    private static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2020, 5, 5, 15, 48);
    private static final LocalDateTime DUE_DATE_TIME = LocalDateTime.of(2020, 5, 5, 15, 50);
    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(2020, 5, 5, 15, 49);

    @DisplayName("미션 신청 성공")
    @Test
    void applySuccess() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", START_DATE_TIME, DUE_DATE_TIME);

        MissionManager.applyIfApplicable(mission, user, TEST_DATE_TIME);

        assertThat(MissionManager.getApplicants().get(mission).contains(user)).isTrue();
    }

    @DisplayName("미션 신청 실패 : 중복 신청")
    @Test
    void applyFail() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", START_DATE_TIME, DUE_DATE_TIME);

        MissionManager.applyIfApplicable(mission, user, TEST_DATE_TIME);

        assertThatThrownBy(() -> MissionManager.applyIfApplicable(mission, user, TEST_DATE_TIME))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("미션 신청 실패 : 신청 기간 마감")
    @Test
    void applyFail2() {
        User user = new User("이름", "이메일주소");
        Mission mission = new Mission("미션제목", START_DATE_TIME, DUE_DATE_TIME);

        assertThatThrownBy(() -> MissionManager.applyIfApplicable(mission, user, DUE_DATE_TIME))
                .isInstanceOf(IllegalArgumentException.class);
    }
}