package com.peachbros.letsmerge.mission.service;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MissionServiceTest {
    @Mock
    private MissionRepository missionRepository;

    private MissionService missionService;

    @BeforeEach
    void setUp() {
        missionService = new MissionService(missionRepository);
    }

    @DisplayName("미션 리스트 가져오기")
    @Test
    void showMissions() {
        List<Mission> missions = Arrays.asList(mockMission(), mockMission(), mockMission());
        when(missionRepository.findAll()).thenReturn(missions);

        List<MissionResponse> missionResponses = missionService.showMissions();

        assertThat(missionResponses.size()).isEqualTo(missions.size());
    }

    private Mission mockMission() {
        return new Mission("missionName", LocalDateTime.of(2020, 5, 24, 0, 0, 0),
                LocalDateTime.of(2020, 5, 25, 0, 0, 0));
    }
}