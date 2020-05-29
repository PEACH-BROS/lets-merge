package com.peachbros.letsmerge.mission.service;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MissionServiceTest {
    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MissionService missionService;

    @DisplayName("미션 추가")
    @Test
    void addMission() {
        MissionCreateRequest request = new MissionCreateRequest("MISSION_NAME", LocalDateTime.of(2020, 5, 24, 0, 0, 0),
                LocalDateTime.of(2020, 5, 25, 0, 0, 0));

        MissionResponse missionResponse = missionService.addMission(request);
        Mission persistMission = missionRepository.findAll().get(0);

        assertThat(missionResponse.getId()).isEqualTo(persistMission.getId());
    }

    @DisplayName("미션 리스트 가져오기")
    @Test
    void showMissions() {
        List<Mission> missions = Arrays.asList(mockMission(), mockMission(), mockMission());
        missionRepository.saveAll(missions);

        MissionsResponse missionResponses = missionService.showMissions();

//        assertThat(missionResponses.size()).isEqualTo(missions.size());
    }

    @Test
    void deleteMission() {
        Mission persistMission = missionRepository.save(mockMission());

        assertThat(missionRepository.findAll()).isNotEmpty();
        missionService.deleteMission(persistMission.getId());
        assertThat(missionRepository.findAll()).isEmpty();
    }

    private Mission mockMission() {
        return new Mission("missionName", LocalDateTime.of(2020, 5, 24, 0, 0, 0),
                LocalDateTime.of(2020, 5, 25, 0, 0, 0));
    }
}