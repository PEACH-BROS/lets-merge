package com.peachbros.letsmerge.mission.model.repository;

import com.peachbros.letsmerge.mission.model.domain.assign.AssignInfo;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.user.model.domain.Role;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AssignInfoRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private AssignInfoRepository assignInfoRepository;

    @DisplayName("User의 id로 AssignInfo를 탐색한다.")
    @Test
    void findByUserId() {
        //given
        User user = new User("엘리", "elly@gmail.com", "profile.png", Role.USER);
        Mission mission1 = new Mission("미션1", LocalDateTime.of(2020, 8, 1, 0, 0, 0), LocalDateTime.of(2020, 9, 1, 0, 0, 0));
        Mission mission2 = new Mission("미션2", LocalDateTime.of(2020, 8, 1, 0, 0, 0), LocalDateTime.of(2020, 9, 1, 0, 0, 0));
        userRepository.save(user);
        missionRepository.saveAll(Arrays.asList(mission1, mission2));

        AssignInfo assignInfo1 = new AssignInfo(user, mission1);
        AssignInfo assignInfo2 = new AssignInfo(user, mission2);

        //when
        user.assignMission(assignInfo1);
        user.assignMission(assignInfo2);
        assignInfoRepository.save(assignInfo1);
        assignInfoRepository.save(assignInfo2);

        //then
        List<AssignInfo> assignedMissions = assignInfoRepository.findByUserId(user.getId());
        assertThat(assignedMissions).hasSize(2);
    }
}