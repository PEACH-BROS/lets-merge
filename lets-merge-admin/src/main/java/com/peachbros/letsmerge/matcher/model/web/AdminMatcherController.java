package com.peachbros.letsmerge.matcher.model.web;

import com.peachbros.letsmerge.common.dto.StandardResponse;
import com.peachbros.letsmerge.matcher.model.service.AdminMatcherService;
import com.peachbros.letsmerge.matcher.model.service.dto.GroupsResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminMatcherController {

    private AdminMatcherService adminMatchService;

    public AdminMatcherController(AdminMatcherService adminMatchService) {
        this.adminMatchService = adminMatchService;
    }

    @PostMapping("/admin/missions/{missionId}/match")
    public StandardResponse<GroupsResponse> matchUsers(@PathVariable Long missionId) {
        GroupsResponse matchedGroups = adminMatchService.match(missionId);
        return StandardResponse.of(matchedGroups);
    }
}
