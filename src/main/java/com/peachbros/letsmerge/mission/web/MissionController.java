package com.peachbros.letsmerge.mission.web;

import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.mission.service.MissionService;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<StandardResponse<Void>> addMission(@Valid MissionCreateRequest request) {
        MissionResponse response = missionService.addMission(request);
        return ResponseEntity.created(URI.create("/admin/missions/" + response.getId())).body(StandardResponse.empty());
    }
}
