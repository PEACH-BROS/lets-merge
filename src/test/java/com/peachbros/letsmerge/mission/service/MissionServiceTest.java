package com.peachbros.letsmerge.mission.service;

import com.peachbros.letsmerge.core.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import com.peachbros.letsmerge.mission.service.dto.MissionUpdateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class MissionServiceTest {
    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MissionService missionService;

    @AfterEach
    void afterEach() {
        missionRepository.deleteAll();
    }

    @DisplayName("미션 추가")
    @Test
    void addMission() {
        MissionCreateRequest request = new MissionCreateRequest("MISSION_NAME", "2010-11-25 12:30:00",
                "2010-11-26 12:30:00");

        MissionResponse missionResponse = missionService.addMission(request);
        Mission persistMission = missionRepository.findAll().get(0);

        assertThat(missionResponse.getId()).isEqualTo(persistMission.getId());
    }

    @DisplayName("미션 리스트 조회")
    @Test
    void showMissions() {
        List<Mission> missions = Arrays.asList(mockMission(), mockMission(), mockMission());
        missionRepository.saveAll(missions);

        MissionsResponse missionResponses = missionService.showMissions();

        assertThat(missionResponses.getMissions()).hasSize(missions.size());
    }

    @DisplayName("미션 업데이트")
    @Test
    void updateMission() {
        Mission mission = mockMission();
        Mission persistMission = missionRepository.save(mission);

        MissionUpdateRequest missionUpdateRequest = new MissionUpdateRequest("updatedMissionName", null, null);
        missionService.updateMission(persistMission.getId(), missionUpdateRequest);

        Mission updatedMission = missionRepository.findById(persistMission.getId()).get();
        assertAll(
                () -> assertThat(updatedMission).isNotNull(),
                () -> assertThat(updatedMission.getName()).isEqualTo(missionUpdateRequest.getName())
        );

    }

    @DisplayName("미션 삭제")
    @Test
    void deleteMission() {
        Mission persistMission = missionRepository.save(mockMission());

        assertThat(missionRepository.findAll()).isNotEmpty();

        missionService.deleteMission(persistMission.getId());
        assertThat(missionRepository.findAll()).isEmpty();
    }

    @DisplayName("미션 삭제 예외상황 : 삭제할 미션이 없는 경우")
    @Test
    void deleteMissionException1() {
        assertThatThrownBy(() -> missionService.deleteMission(1L))
                .isInstanceOf(NoSuchValueException.class);
    }

    private Mission mockMission() {
        return new Mission("missionName", LocalDateTime.of(2020, 5, 24, 0, 0, 0),
                LocalDateTime.of(2020, 5, 25, 0, 0, 0));
    }
}