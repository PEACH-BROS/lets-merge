package com.peachbros.letsmerge.mission.service.dto;

import com.peachbros.letsmerge.mission.model.domain.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionsResponse {
    private List<MissionResponse> missions;

    private MissionsResponse() {
    }

    public MissionsResponse(List<MissionResponse> missions) {
        this.missions = missions;
    }

    public static MissionsResponse of(List<Mission> missions) {
        return new MissionsResponse(missions.stream()
                .map(MissionResponse::of).
                        collect(Collectors.toList()));
    }

    public List<MissionResponse> getMissions() {
        return missions;
    }
}
