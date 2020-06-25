package com.peachbros.letsmerge.user.web;

import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.user.service.UserService;
import com.peachbros.letsmerge.user.service.dto.UserUpdateRequest;
import com.peachbros.letsmerge.user.service.dto.UsersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public StandardResponse<UsersResponse> showUsers() {
        return StandardResponse.of(userService.showUsers());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public StandardResponse<Void> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(id, userUpdateRequest);
        return StandardResponse.empty();
    }
}
