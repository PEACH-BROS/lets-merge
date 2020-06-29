package com.peachbros.letsmerge.user.model.repository;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.user.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    @Query("SELECT m.id, m.name, m.startDateTime, m.dueDateTime\n" +
            "FROM USER u\n" +
            "JOIN MISSION_USER mu\n" +
            "ON u.id = mu.id\n" +
            "JOIN MISSION m\n" +
            "ON mu.id = m.id")
    public List<Mission> findAssignedMissions();
}


/*
//TODO: JPQL 써야 할 것 같은데 어떻게 쓰는지 모르겠다
SELECT m.id, m.name, m.startDateTime, m.dueDateTime
FROM USER u
JOIN MISSION_USER mu
ON u.id = mu.id
JOIN MISSION m
ON mu.id = m.id
 */

/*
//신청 가능한 미션 가져오기
SELECT * FROM MISSION
JOIN MISSION_USER mu
ON
 */