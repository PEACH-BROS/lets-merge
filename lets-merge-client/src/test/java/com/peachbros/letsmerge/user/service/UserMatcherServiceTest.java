package com.peachbros.letsmerge.user.service;

import com.peachbros.letsmerge.matcher.model.domain.Matcher;
import com.peachbros.letsmerge.matcher.model.domain.strategy.MatchStrategy;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.domain.assign.AssignInfo;
import com.peachbros.letsmerge.mission.model.repository.AssignInfoRepository;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.user.model.domain.Groups;
import com.peachbros.letsmerge.user.model.domain.Role;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.domain.Users;
import com.peachbros.letsmerge.user.model.repository.GroupRepository;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import com.peachbros.letsmerge.user.service.dto.GroupResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserMatcherServiceTest {

    @Autowired
    private UserMatcherService userMatcherService;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignInfoRepository assignInfoRepository;
    @Autowired
    private MatchStrategy matchStrategy;
    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    @DisplayName("특정 사용자가 속한 group을 반환한다.")
    @Test
    void showMatchResult() {
        //given
        Mission mission = new Mission("체스 미션", LocalDateTime.of(2020, 5, 24, 0, 0, 0),
                LocalDateTime.of(2020, 5, 25, 0, 0, 0));
        missionRepository.save(mission);
        User user1 = new User("사용자1", "dummy@gmail.com", "picture.png", Role.USER);
        User user2 = new User("사용자2", "dummy@gmail.com", "picture.png", Role.USER);
        User user3 = new User("사용자3", "dummy@gmail.com", "picture.png", Role.USER);
        User user4 = new User("사용자4", "dummy@gmail.com", "picture.png", Role.USER);
        User user5 = new User("사용자5", "dummy@gmail.com", "picture.png", Role.USER);
        User user6 = new User("사용자6", "dummy@gmail.com", "picture.png", Role.USER);
        User user7 = new User("사용자7", "dummy@gmail.com", "picture.png", Role.USER);
        User user8 = new User("사용자8", "dummy@gmail.com", "picture.png", Role.USER);
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);
        userRepository.saveAll(users);
        AssignInfo assignInfo1 = new AssignInfo(user1, mission);
        AssignInfo assignInfo2 = new AssignInfo(user2, mission);
        AssignInfo assignInfo3 = new AssignInfo(user3, mission);
        AssignInfo assignInfo4 = new AssignInfo(user4, mission);
        AssignInfo assignInfo5 = new AssignInfo(user5, mission);
        AssignInfo assignInfo6 = new AssignInfo(user6, mission);
        AssignInfo assignInfo7 = new AssignInfo(user7, mission);
        AssignInfo assignInfo8 = new AssignInfo(user8, mission);
        List<AssignInfo> assignInfos = Arrays.asList(assignInfo1, assignInfo2, assignInfo3, assignInfo4, assignInfo5, assignInfo6, assignInfo7, assignInfo8);
        assignInfoRepository.saveAll(assignInfos);

        user1.assignMission(assignInfo1);
        user2.assignMission(assignInfo2);
        user3.assignMission(assignInfo3);
        user4.assignMission(assignInfo4);
        user5.assignMission(assignInfo5);
        user6.assignMission(assignInfo6);
        user7.assignMission(assignInfo7);
        user8.assignMission(assignInfo8);
        List<User> assignedUsers = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);
        userRepository.saveAll(assignedUsers);

        Groups matchedGroups = Matcher.match(new Users(assignedUsers), matchStrategy);
        mission.addMatchedGroups(matchedGroups);
        groupRepository.saveAll(matchedGroups.getGroups());

        //when
        GroupResponse groupResponse = userMatcherService.showMatchResult(user1.getId(), mission.getId());

        //then
        assertThat(groupResponse.getGroup().hasUser(user1.getId()));
    }

    @AfterEach
    void tearDown() {
        assignInfoRepository.deleteAll();
        missionRepository.deleteAll();
        userRepository.deleteAll();
    }
}