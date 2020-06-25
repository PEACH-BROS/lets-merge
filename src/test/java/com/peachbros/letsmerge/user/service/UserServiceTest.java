package com.peachbros.letsmerge.user.service;

import com.peachbros.letsmerge.user.model.domain.Role;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import com.peachbros.letsmerge.user.service.dto.UsersResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @DisplayName("Users 조회")
    @Test
    void findAllUsers() {
        List<User> users = Arrays.asList(mockUser(), mockUser(), mockUser());
        userRepository.saveAll(users);

        UsersResponse usersResponse = userService.showUsers();

        assertThat(usersResponse.getUsersResponse()).hasSize(users.size());
    }

    private User mockUser() {
        return new User("name", "email@gmail.com", User.NO_PICTURE, Role.USER);
    }
}