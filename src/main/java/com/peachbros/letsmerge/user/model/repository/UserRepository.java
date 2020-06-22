package com.peachbros.letsmerge.user.model.repository;

import com.peachbros.letsmerge.user.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
