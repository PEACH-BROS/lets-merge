package com.peachbros.letsmerge.mission.service;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MissionService {
    private final MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Transactional
    public MissionResponse addMission(MissionCreateRequest request) {
        Mission mission = request.toMission();
        Mission persistMission = missionRepository.save(mission);

        return MissionResponse.of(persistMission);
    }

    @Transactional(readOnly = true)
    public MissionsResponse showMissions() {
        List<Mission> missions = missionRepository.findAll();
        return MissionsResponse.of(missions);
    }

    public void deleteMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(IllegalArgumentException::new);
        missionRepository.delete(mission);
    }
}
