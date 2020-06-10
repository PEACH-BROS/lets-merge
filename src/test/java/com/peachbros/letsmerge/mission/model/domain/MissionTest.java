package com.peachbros.letsmerge.mission.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissionTest {

    private static final String MISSION_NAME = "미션";
    private static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2020, 5, 1, 21, 0);
    private static final LocalDateTime DUE_DATE_TIME = LocalDateTime.of(2020, 5, 12, 21, 0);

    private static Stream<Arguments> createMission() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2020, 5, 12, 20, 59), true),
                Arguments.of(LocalDateTime.of(2020, 5, 12, 21, 59), false));
    }

    @DisplayName("생성자 테스트")
    @Test
    void MissionInit() {
        assertThat(new Mission(MISSION_NAME, START_DATE_TIME, DUE_DATE_TIME)).isInstanceOf(Mission.class);
    }

    @DisplayName("미션 날짜 활성화 참/거짓")
    @ParameterizedTest
    @MethodSource("createMission")
    void isActive(LocalDateTime dateTime, boolean result) {
        Mission mission = new Mission(MISSION_NAME, START_DATE_TIME, DUE_DATE_TIME);
        assertThat(mission.isActive(dateTime)).isEqualTo(result);
    }

    @DisplayName("시작 시간이 종료 시간보다 뒤에 있는 경우 exception 발생")
    @Test
    void validateDateTime() {
        assertThatThrownBy(() -> new Mission(MISSION_NAME, DUE_DATE_TIME, START_DATE_TIME))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작 시간(%s)이 종료 시간(%s)보다 뒤에 있습니다.", DUE_DATE_TIME, START_DATE_TIME);
    }
}