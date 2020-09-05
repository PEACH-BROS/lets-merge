package com.peachbros.letsmerge.mission.model.service.dto;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.domain.MissionStatus;

import java.util.HashMap;
import java.util.Map;

public class MissionWithStatusResponse {
    private Map<MissionResponse, String> missionWithStatus;

    protected MissionWithStatusResponse() {
    }

    private MissionWithStatusResponse(Map<MissionResponse, String> missionWithStatus) {
        this.missionWithStatus = missionWithStatus;
    }

    public static MissionWithStatusResponse of(Map<Mission, MissionStatus> missionWithStatus) {
        Map<MissionResponse, String> dto = new HashMap<>();
        for (Map.Entry<Mission, MissionStatus> entry : missionWithStatus.entrySet()) {
            dto.put(MissionResponse.of(entry.getKey()), entry.getValue().getMessage());
        }
        return new MissionWithStatusResponse(dto);
    }

}
