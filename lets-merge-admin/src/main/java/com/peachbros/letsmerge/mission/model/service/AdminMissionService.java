package com.peachbros.letsmerge.mission.model.service;

import com.peachbros.letsmerge.common.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.model.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.model.service.dto.MissionResponse;
import com.peachbros.letsmerge.mission.model.service.dto.MissionUpdateRequest;
import com.peachbros.letsmerge.mission.model.service.dto.MissionsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminMissionService {

    private final MissionRepository missionRepository;

    public AdminMissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Transactional
    public MissionResponse addMission(MissionCreateRequest request) {
        Mission mission = request.toMission();
        Mission persistMission = missionRepository.save(mission);

        return MissionResponse.of(persistMission);
    }

    public MissionsResponse showMissions() {
        List<Mission> missions = missionRepository.findAll();
        return MissionsResponse.of(missions);
    }

    @Transactional
    public void updateMission(Long missionId, MissionUpdateRequest missionUpdateRequest) {
        Mission persistMission = findMissionById(missionId);
        persistMission.update(missionUpdateRequest.getName(), missionUpdateRequest.getStartDateTime(), missionUpdateRequest.getDueDateTime());
    }

    @Transactional
    public void deleteMission(Long missionId) {
        Mission mission = findMissionById(missionId);
        missionRepository.delete(mission);
    }

    private Mission findMissionById(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new NoSuchValueException("해당 미션을 찾을 수 없습니다."));
    }
}
