package com.peachbros.letsmerge.matcher.model.service;

import com.peachbros.letsmerge.common.exception.NoSuchValueException;
import com.peachbros.letsmerge.matcher.model.domain.Matcher;
import com.peachbros.letsmerge.matcher.model.domain.strategy.MatchStrategy;
import com.peachbros.letsmerge.matcher.model.service.dto.GroupsResponse;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.user.model.domain.Groups;
import com.peachbros.letsmerge.user.model.domain.Users;
import com.peachbros.letsmerge.user.model.repository.GroupRepository;
import com.peachbros.letsmerge.user.model.repository.UserGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminMatcherService {

    private MatchStrategy matchStrategy;

    private MissionRepository missionRepository;

    private GroupRepository groupRepository;

    private UserGroupRepository userGroupRepository;

    public AdminMatcherService(MatchStrategy matchStrategy, MissionRepository missionRepository, GroupRepository groupRepository, UserGroupRepository userGroupRepository) {
        this.matchStrategy = matchStrategy;
        this.missionRepository = missionRepository;
        this.groupRepository = groupRepository;
        this.userGroupRepository = userGroupRepository;
    }

    @Transactional
    public GroupsResponse match(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new NoSuchValueException("해당 미션이 존재하지 않습니다."));
        Users users = new Users(mission.getAssignedUsers());

        Groups matchedGroups = Matcher.match(users, matchStrategy);
        matchedGroups.getGroups().forEach(group -> groupRepository.save(group));
        //TODO: 여기서 자동으로 userGroupRepository.save가 되나?

        return new GroupsResponse(matchedGroups);
    }
}
