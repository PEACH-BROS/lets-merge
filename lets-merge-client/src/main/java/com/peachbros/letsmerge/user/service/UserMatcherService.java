package com.peachbros.letsmerge.user.service;

import com.peachbros.letsmerge.common.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.model.domain.model.domain.Matcher;
import com.peachbros.letsmerge.model.domain.model.domain.strategy.MatchStrategy;
import com.peachbros.letsmerge.user.model.domain.Group;
import com.peachbros.letsmerge.user.model.domain.Groups;
import com.peachbros.letsmerge.user.model.domain.Users;
import com.peachbros.letsmerge.user.service.dto.GroupResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMatcherService {
    private final MissionRepository missionRepository;
    private final MatchStrategy matchStrategy;

    public UserMatcherService(MissionRepository missionRepository, MatchStrategy matchStrategy) {
        this.missionRepository = missionRepository;
        this.matchStrategy = matchStrategy;
    }

    public GroupResponse showMatchResult(Long userId, Long missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new NoSuchValueException("존재하지 않는 미션입니다."));
        Users users = Users.of(mission.getAssignedUsers());

        Groups matchedGroups = Matcher.match(users, matchStrategy);
        Group group = matchedGroups.findGroupContainingUser(userId);

        return GroupResponse.of(group);
    }
}
