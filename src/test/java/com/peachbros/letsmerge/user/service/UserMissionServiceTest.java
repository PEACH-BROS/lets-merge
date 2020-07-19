package com.peachbros.letsmerge.user.service;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.repository.MissionRepository;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import com.peachbros.letsmerge.user.model.domain.Role;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserMissionServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserMissionService userMissionService;

    private User user;

    private Mission mission1;

    private Mission mission2;

    private Mission mission3;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        missionRepository.deleteAll();

        this.user = new User("엘리", "elly@gmail.com", "elly.png", Role.USER);
        this.mission1 = new Mission("미션1", LocalDateTime.of(2020, 5, 5, 0, 0), LocalDateTime.of(2020, 5, 12, 0, 0));
        this.mission2 = new Mission("미션2", LocalDateTime.of(2020, 5, 5, 0, 0), LocalDateTime.of(2020, 5, 12, 0, 0));
        this.mission3 = new Mission("미션3", LocalDateTime.of(2020, 5, 5, 0, 0), LocalDateTime.of(2020, 5, 12, 0, 0));

        userRepository.save(user);
        missionRepository.save(mission1);
        missionRepository.save(mission2);
        missionRepository.save(mission3);
    }

    @DisplayName("미션을 신청한다.")
    @Test
    void assignMission() {
        User persistUser = userRepository.findById(user.getId()).get();

        //신청 전 User의 신청한 미션 개수를 확인한다.
        MissionsResponse beforeAssign = userMissionService.getAssignedMissions(persistUser.getId());
        assertThat(beforeAssign.getMissions()).hasSize(0);

        //미션을 신청한다.
        userMissionService.assignMission(user.getId(), mission1.getId());

        //신청 후 User의 신청한 미션 개수를 확인한다.
        MissionsResponse assignedMissions = userMissionService.getAssignedMissions(persistUser.getId());
        assertThat(assignedMissions.getMissions()).hasSize(1);
    }

    @DisplayName("미션 신청을 취소한다.")
    @Test
    void cancelMission() {
        //User가 미션을 신청한다.
        User persistUser = userRepository.findById(user.getId()).get();
        userMissionService.assignMission(user.getId(), mission1.getId());
        userMissionService.assignMission(user.getId(), mission2.getId());

        //User가 미션 신청을 취소한다.
        userMissionService.cancelMission(persistUser.getId(), mission1.getId());

        //신청한 미션 수를 센다.
        MissionsResponse assignedMissions = userMissionService.getAssignedMissions(persistUser.getId());
        assertThat(assignedMissions.getMissions()).hasSize(1);

        //신청 가능한 미션의 수를 센다.
        MissionsResponse assignableMissions = userMissionService.getAssignableMissions(persistUser.getId());
        assertThat(assignableMissions.getMissions()).hasSize(2);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        missionRepository.deleteAll();
    }
}