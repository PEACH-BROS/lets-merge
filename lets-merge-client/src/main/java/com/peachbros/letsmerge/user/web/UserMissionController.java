package com.peachbros.letsmerge.user.web;

import com.peachbros.letsmerge.common.dto.StandardResponse;
import com.peachbros.letsmerge.mission.model.service.dto.MissionWithStatusResponse;
import com.peachbros.letsmerge.mission.model.service.dto.MissionsResponse;
import com.peachbros.letsmerge.user.service.UserMissionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users/{userId}/missions")
public class UserMissionController {
    private final UserMissionService userMissionService;

    public UserMissionController(UserMissionService userMissionService) {
        this.userMissionService = userMissionService;
    }

    //미션 신청
    @PostMapping("/{missionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StandardResponse<Void> assignMission(@PathVariable("userId") Long userId, @PathVariable("missionId") Long missionId) {
        userMissionService.assignMission(userId, missionId);
        return StandardResponse.empty();
    }

    //미션 신청 취소
    @DeleteMapping("/{missionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StandardResponse<Void> cancelMission(@PathVariable("userId") Long userId, @PathVariable("missionId") Long missionId) {
        userMissionService.cancelMission(userId, missionId);
        return StandardResponse.empty();
    }

    //신청한 미션 조회
    @GetMapping("/assigned")
    @ResponseStatus(HttpStatus.OK)
    public StandardResponse<MissionsResponse> getAssignedMissions(@PathVariable("userId") Long userId) {
        MissionsResponse missionsResponse = userMissionService.getAssignedMissions(userId);
        return StandardResponse.of(missionsResponse);
    }

    //신청 가능한 미션 조회
    @GetMapping("/assignable")
    public StandardResponse<MissionsResponse> getAssignableMissions(@PathVariable("userId") Long userId) {
        MissionsResponse missionsResponse = userMissionService.getAssignableMissions(userId);
        return StandardResponse.of(missionsResponse);
    }

    //전체 미션과 user에 따른 전체 미션 상태 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StandardResponse<MissionWithStatusResponse> getMissions(@PathVariable("userId") Long userId) {
        MissionWithStatusResponse missionsResponse = userMissionService.getMissions(userId);
        return StandardResponse.of(missionsResponse);
    }
}
