package com.peachbros.letsmerge.user.web;

import com.peachbros.letsmerge.common.dto.StandardResponse;
import com.peachbros.letsmerge.user.service.UserMatcherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserMatcherController {
    private final UserMatcherService userMatcherService;

    public UserMatcherController(UserMatcherService userMatcherService) {
        this.userMatcherService = userMatcherService;
    }

    //미션 match 결과 요청
    @GetMapping("/api/v1/users/{userId}/missions/{missionId}/result")
    public StandardResponse<Void> getMatchResult(@PathVariable("userId") Long userId, @PathVariable("missionId") Long missionId) {
        return null;
    }
}