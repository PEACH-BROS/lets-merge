package com.peachbros.letsmerge.mission.service;

import com.peachbros.letsmerge.config.auth.dto.SessionUser;
import com.peachbros.letsmerge.core.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public MissionService(MissionRepository missionRepository, UserRepository userRepository) {
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
    }

    public void assignMission(SessionUser user, Long missionId) {
        User persistUser = findUser(user);
        Mission mission = findMission(missionId);
        mission.addUser(persistUser);
    }

    public void cancelMission(SessionUser user, Long missionId) {
        User persistUser = findUser(user);
        Mission mission = findMission(missionId);
        mission.removeUser(persistUser);
    }

    public MissionsResponse getAssignedMissions() {
        List<Mission> assignedMissions = userRepository.findAssignedMissions();
        return MissionsResponse.of(assignedMissions);
    }

    public MissionsResponse getAssignableMissions() {
        List<Mission> assignableMissions = userRepository.findAssignableMissions();
        return MissionsResponse.of(assignableMissions);
    }

    private User findUser(SessionUser user) {
        return userRepository.findByName(user.getName())
                .orElseThrow(() -> new NoSuchValueException("존재하지 않는 사용자입니다."));
    }

    private Mission findMission(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new NoSuchValueException("존재하지 않는 미션입니다."));
    }
}
