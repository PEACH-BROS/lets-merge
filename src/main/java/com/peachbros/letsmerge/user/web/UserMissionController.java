package com.peachbros.letsmerge.user.web;

import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import com.peachbros.letsmerge.user.service.UserMissionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/{userId}/missions")
public class UserMissionController {
    private final UserMissionService userMissionService;

    public UserMissionController(UserMissionService userMissionService) {
        this.userMissionService = userMissionService;
    }

    //미션 신청
    //TODO: 신청 취소는 자원 변경 요청. create같이 자원을 생성하는 요청이 아니라 둘이 충돌. 어떻게 하면 좋을까
    @PostMapping("/{missionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignMission(@PathVariable("userId") Long userId, @PathVariable("missionId") Long missionId) {
        userMissionService.assignMission(userId, missionId);
    }

    //미션 신청 취소
    @DeleteMapping("/{missionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelMission(@PathVariable("userId") Long userId, @PathVariable("missionId") Long missionId) {
        userMissionService.cancelMission(userId, missionId);
    }

    //신청한 미션 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StandardResponse<MissionsResponse> getAssignedMissions(@PathVariable("userId") Long userId) {
        MissionsResponse missionsResponse = userMissionService.getAssignedMissions(userId);
        return StandardResponse.of(missionsResponse);
    }
//
//    //신청 가능한 미션 조회
//    public StandardResponse<MissionsResponse> getAssignableMissions() {
//        MissionsResponse missionsResponse = missionService.getAssignableMissions();
//    }

}
