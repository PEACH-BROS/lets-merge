package com.peachbros.letsmerge.mission.model.web;

import com.peachbros.letsmerge.common.dto.StandardResponse;
import com.peachbros.letsmerge.mission.model.service.AdminMissionService;
import com.peachbros.letsmerge.mission.model.service.dto.MissionCreateRequest;
import com.peachbros.letsmerge.mission.model.service.dto.MissionResponse;
import com.peachbros.letsmerge.mission.model.service.dto.MissionUpdateRequest;
import com.peachbros.letsmerge.mission.model.service.dto.MissionsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin/missions")
public class AdminMissionController {
    private final AdminMissionService adminMissionService;

    public AdminMissionController(AdminMissionService adminMissionService) {
        this.adminMissionService = adminMissionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StandardResponse<Void> addMission(@RequestBody @Valid MissionCreateRequest request, HttpServletResponse response) {
        MissionResponse missionResponse = adminMissionService.addMission(request);
        response.setHeader(HttpHeaders.LOCATION, "/admin/missions/" + missionResponse.getId());
        return StandardResponse.empty();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public StandardResponse<MissionsResponse> showMissions() {
        return StandardResponse.of(adminMissionService.showMissions());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public StandardResponse<Void> updateMission(@PathVariable Long id, @RequestBody MissionUpdateRequest missionUpdateRequest) {
        adminMissionService.updateMission(id, missionUpdateRequest);
        return StandardResponse.empty();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public StandardResponse<Void> deleteMission(@PathVariable Long id) {
        adminMissionService.deleteMission(id);
        return StandardResponse.empty();
    }
}
