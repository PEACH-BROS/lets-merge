package com.peachbros.letsmerge.mission.model.repository;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("select distinct m from Mission m left join fetch m.assignedUsers")
    List<Mission> findAllWithAssignInfo();
}
