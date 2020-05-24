package com.peachbros.letsmerge.mission.service;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionService {
    private MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Transactional(readOnly = true)
    public List<MissionResponse> showMissions() {
        List<Mission> missions = missionRepository.findAll();
        return missions.stream()
                .map(MissionResponse::of)
                .collect(Collectors.toList());
    }
}
