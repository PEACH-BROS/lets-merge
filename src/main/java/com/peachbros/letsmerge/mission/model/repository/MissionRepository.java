package com.peachbros.letsmerge.mission.model.repository;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MissionRepository extends CrudRepository<Mission, Long> {

    @Override
    List<Mission> findAll();
}
