package com.peachbros.letsmerge.user.service;

import com.peachbros.letsmerge.common.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.domain.assign.AssignInfo;
import com.peachbros.letsmerge.mission.model.repository.AssignInfoRepository;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.model.service.dto.MissionsResponse;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMissionService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final AssignInfoRepository assignInfoRepository;

    public UserMissionService(MissionRepository missionRepository, UserRepository userRepository, AssignInfoRepository assignInfoRepository) {
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
        this.assignInfoRepository = assignInfoRepository;
    }

    @Transactional
    public void assignMission(Long userId, Long missionId) {
        User persistUser = findUserById(userId);
        Mission persistMission = findMission(missionId);
        AssignInfo assignInfo = new AssignInfo(persistUser, persistMission);

        persistUser.assignMission(assignInfo);
    }

    @Transactional
    public void cancelMission(Long userId, Long missionId) {
        User persistUser = findUserById(userId);
        Mission mission = findMission(missionId);
        persistUser.cancelMission(mission);
    }

    public MissionsResponse getAssignedMissions(Long userId) {
        List<AssignInfo> assignedInfos = assignInfoRepository.findByUserId(userId);
        List<Mission> missions = assignedInfos.stream().map(AssignInfo::getMission).collect(Collectors.toList());
        return MissionsResponse.of(missions);
    }

    @Transactional
    public MissionsResponse getAssignableMissions(Long userId) {
        User persistUser = findUserById(userId);

        List<Mission> assignedMissions = persistUser.getAssignedMissions();
        List<Mission> everyMission = missionRepository.findAll();

        everyMission.removeAll(assignedMissions);
        return MissionsResponse.of(everyMission);
    }

    public MissionsResponse getMissions() {
        List<Mission> missions = missionRepository.findAll();
        return MissionsResponse.of(missions);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchValueException("해당 User를 찾을 수 없습니다. user.id = " + userId));
    }

    private Mission findMission(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new NoSuchValueException("존재하지 않는 미션입니다."));
    }
}
