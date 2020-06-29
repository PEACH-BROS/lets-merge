package com.peachbros.letsmerge.mission.model.repository;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
