package com.peachbros.letsmerge.mission.web;

import com.peachbros.letsmerge.core.dto.StandardResponse;
import com.peachbros.letsmerge.mission.service.MissionService;
import com.peachbros.letsmerge.mission.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionResponse;
import com.peachbros.letsmerge.mission.service.dto.MissionUpdateRequest;
import com.peachbros.letsmerge.mission.service.dto.MissionsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/admin/missions")
public class MissionController {
    private MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    public ResponseEntity<Void> addMission(@RequestBody @Valid MissionCreateRequest request) {
        MissionResponse response = missionService.addMission(request);
        return ResponseEntity.created(URI.create("/admin/missions/" + response.getId())).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public StandardResponse<MissionsResponse> showMissions() {
        return StandardResponse.of(missionService.showMissions());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public StandardResponse<Void> updateMission(@PathVariable Long id, @RequestBody MissionUpdateRequest missionUpdateRequest) {
        missionService.updateMission(id, missionUpdateRequest);
        return StandardResponse.empty();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public StandardResponse<Void> deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
        return StandardResponse.empty();
    }
}
