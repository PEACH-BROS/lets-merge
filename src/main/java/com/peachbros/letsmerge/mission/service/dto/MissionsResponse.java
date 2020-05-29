package com.peachbros.letsmerge.mission.service.dto;

import java.util.List;

public class MissionsResponse {
    private List<MissionResponse> missions;

    public MissionsResponse(List<MissionResponse> missions) {
        this.missions = missions;
    }

    public List<MissionResponse> getMissions() {
        return missions;
    }
}
