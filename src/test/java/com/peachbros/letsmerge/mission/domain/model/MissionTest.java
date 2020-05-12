package com.peachbros.letsmerge.mission.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class MissionTest {
    @DisplayName("미션 날짜 활성화 : 참")
    @Test
    void isActive() {
        Mission mission = new Mission("미션", "2020-05-01 21:00", "2020-05-12 21:00");
        LocalDateTime now = LocalDateTime.of(2020, 05, 12, 20, 59);
        assertThat(mission.isActive(now)).isTrue();
    }

    @DisplayName("미션 날짜 활성화 : 거짓")
    @Test
    void isNotActive() {
        Mission mission = new Mission("미션", "2020-05-01 21:00", "2020-05-12 21:00");
        LocalDateTime now = LocalDateTime.of(2020, 05, 12, 21, 59);
        assertThat(mission.isActive(now)).isFalse();
    }
}