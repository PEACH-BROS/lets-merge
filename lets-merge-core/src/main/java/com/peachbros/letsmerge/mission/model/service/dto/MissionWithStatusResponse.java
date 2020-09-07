package com.peachbros.letsmerge.mission.model.service.dto;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.domain.MissionStatus;

import java.time.LocalDateTime;

public class MissionWithStatusResponse {
    private Long id;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;
    private String status;

    protected MissionWithStatusResponse() {
    }

    public MissionWithStatusResponse(Mission mission, MissionStatus missionStatus) {
        this.id = mission.getId();
        this.name = mission.getName();
        this.startDateTime = mission.getStartDateTime();
        this.dueDateTime = mission.getDueDateTime();
        this.status = missionStatus.getMessage();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public String getStatus() {
        return status;
    }
}
