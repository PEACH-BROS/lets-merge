package com.peachbros.letsmerge.user.model.repository;

import com.peachbros.letsmerge.user.model.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
