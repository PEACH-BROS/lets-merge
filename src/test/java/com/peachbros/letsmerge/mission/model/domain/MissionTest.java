package com.peachbros.letsmerge.mission.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MissionTest {

    private static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2020, 5, 1, 21, 0);
    private static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2020, 5, 12, 21, 0);

    private static Stream<Arguments> createMission() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2020, 5, 12, 20, 59), true),
                Arguments.of(LocalDateTime.of(2020, 5, 12, 21, 59), false));
    }

    @DisplayName("생성자 테스트")
    @Test
    void MissionInit() {
        assertThat(new Mission("미션", END_DATE_TIME)).isInstanceOf(Mission.class);
    }

    @DisplayName("미션 날짜 활성화 참/거짓")
    @ParameterizedTest
    @MethodSource("createMission")
    void isActive(LocalDateTime dateTime, boolean result) {
        Mission mission = new Mission("미션", START_DATE_TIME, END_DATE_TIME);
        LocalDateTime now = LocalDateTime.of(2020, 5, 12, 20, 59);
        assertThat(mission.isActive(dateTime)).isEqualTo(result);
    }
}