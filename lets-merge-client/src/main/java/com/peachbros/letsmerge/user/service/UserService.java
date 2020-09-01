package com.peachbros.letsmerge.user.service;

import com.peachbros.letsmerge.common.exception.NoSuchValueException;
import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.repository.UserRepository;
import com.peachbros.letsmerge.user.service.dto.UserUpdateRequest;
import com.peachbros.letsmerge.user.service.dto.UsersResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User persistUser = findUserById(userId);
        persistUser.update(userUpdateRequest.getEmail(), userUpdateRequest.getPicture());
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchValueException("해당 User를 찾을 수 없습니다. user.id = " + userId));
    }
}
