package com.peachbros.letsmerge.mission.web;

import com.peachbros.letsmerge.config.auth.dto.SessionUser;
import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.mission.service.MissionService;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MissionController {
    private MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    //미션 신청
    //TODO: 신청 취소는 자원 변경 요청. create같이 자원을 생성하는 요청이 아니라 둘이 충돌. 어떻게 하면 좋을까
    @PostMapping("/api/v1/missions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignMission(@LoginUser SessionUser user, @PathVariable(name = "id") Long missionId) {
        missionService.assignMission(user, missionId);
    }

    //미션 신청 취소
    @DeleteMapping("/api/v1/missions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelMission(@LoginUser SessionUser user, @PathVariable(name = "id") Long missionId) {
        missionService.cancelMission(user, missionId);
    }

    //신청한 미션 조회
    @GetMapping("/api/v1/missions")
    @ResponseStatus(HttpStatus.OK)
    public StandardResponse<MissionsResponse> getAssignedMissions() {
        MissionsResponse missionsResponse = missionService.getAssignedMissions();
        return StandardResponse.of(missionsResponse);
    }

    //신청 가능한 미션 조회
    public StandardResponse<MissionsResponse> getAssignableMissions() {
        MissionsResponse missionsResponse = missionService.getAssignableMissions();
    }
}
