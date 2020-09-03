package com.peachbros.letsmerge.mission.model.repository;

import com.peachbros.letsmerge.mission.model.domain.assign.AssignInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignInfoRepository extends JpaRepository<AssignInfo, Long> {

    List<AssignInfo> findByUserId(Long userId);
}
