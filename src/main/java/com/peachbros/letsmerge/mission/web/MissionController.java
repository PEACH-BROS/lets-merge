package com.peachbros.letsmerge.mission.web;

import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.mission.service.MissionService;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class MissionController {
    private MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping("/admin/missions")
    public ResponseEntity<Void> addMission(@RequestBody @Valid MissionCreateRequest request) {
        MissionResponse response = missionService.addMission(request);
        return ResponseEntity.created(URI.create("/admin/missions/" + response.getId())).build();
    }

    @GetMapping("/admin/missions")
    public StandardResponse<MissionsResponse> showMissions() {
        return StandardResponse.of(HttpStatus.OK.value(), missionService.showMissions());
    }

}
