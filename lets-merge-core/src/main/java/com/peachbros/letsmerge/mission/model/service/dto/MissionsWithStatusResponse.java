package com.peachbros.letsmerge.mission.model.service.dto;

import java.util.List;

public class MissionsWithStatusResponse {
    private List<MissionWithStatusResponse> missionWithStatusResponse;

    protected MissionsWithStatusResponse() {
    }

    public MissionsWithStatusResponse(List<MissionWithStatusResponse> missionWithStatusResponse) {
        this.missionWithStatusResponse = missionWithStatusResponse;
    }

    public static MissionsWithStatusResponse of(List<MissionWithStatusResponse> responses) {
        return new MissionsWithStatusResponse(responses);
    }

    public List<MissionWithStatusResponse> getMissionWithStatusResponse() {
        return missionWithStatusResponse;
    }
}
