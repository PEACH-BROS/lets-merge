package com.peachbros.letsmerge.user.service;

import com.peachbros.letsmerge.core.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.domain.assign.AssignInfo;
import com.peachbros.letsmerge.mission.model.domain.assign.AssignStatus;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserMissionService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public UserMissionService(MissionRepository missionRepository, UserRepository userRepository) {
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void assignMission(Long userId, Long missionId) {
        User persistUser = findUserById(userId);
        Mission persistMission = findMission(missionId);
        AssignInfo assignInfo = new AssignInfo(persistUser, persistMission, AssignStatus.ASSIGN);

        persistUser.addAssignInfo(assignInfo);
        persistMission.addAssignInfo(assignInfo);
    }

    @Transactional
    public void cancelMission(Long userId, Long missionId) {
        User persistUser = findUserById(userId);
        Mission mission = findMission(missionId);
        persistUser.cancelMission(mission);
    }

    @Transactional
    public MissionsResponse getAssignedMissions(Long userId) {
        //TODO: User와 Mission을 조인해서 가져오는 조인 쿼리 작성
        User persistUser = findUserById(userId);
        List<Mission> assignedMissions = persistUser.getAssignedMissions();
        return MissionsResponse.of(assignedMissions);
    }

    @Transactional
    public MissionsResponse getAssignableMissions(Long userId) {
        User persistUser = findUserById(userId);

        List<Mission> assignedMissions = persistUser.getAssignedMissions();
        List<Mission> everyMission = missionRepository.findAll();

        everyMission.removeAll(assignedMissions);
        return MissionsResponse.of(everyMission);
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
