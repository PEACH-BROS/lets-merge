package com.peachbros.letsmerge.user.model.repository;

import com.peachbros.letsmerge.user.model.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
