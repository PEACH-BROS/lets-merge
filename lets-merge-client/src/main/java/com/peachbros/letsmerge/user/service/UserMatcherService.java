package com.peachbros.letsmerge.user.service;

import com.peachbros.letsmerge.common.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.user.model.domain.Group;
import com.peachbros.letsmerge.user.model.domain.Groups;
import com.peachbros.letsmerge.user.service.dto.GroupResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMatcherService {
    private final MissionRepository missionRepository;

    public UserMatcherService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    //특정 미션 match 결과에서 사용자가 속한 group을 찾고, group의 users를 보여준다.
    public GroupResponse showMatchResult(Long userId, Long missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new NoSuchValueException("존재하지 않는 미션입니다."));
        List<Group> matchedGroups = mission.getMatchedGroups();
        Groups groups = new Groups(matchedGroups);
        Group groupContainingUser = groups.findGroupContainingUser(userId);

        return GroupResponse.of(groupContainingUser);
    }
}
