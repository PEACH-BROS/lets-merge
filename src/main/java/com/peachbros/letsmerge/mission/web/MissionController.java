package com.peachbros.letsmerge.mission.web;

import com.peachbros.letsmerge.mission.service.MissionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MissionController {
    private MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }
}
