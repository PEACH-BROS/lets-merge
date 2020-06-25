package com.peachbros.letsmerge.user.service;

import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import com.peachbros.letsmerge.user.service.dto.UsersResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersResponse showUsers() {
        List<User> users = userRepository.findAll();
        return UsersResponse.of(users);
    }
}
