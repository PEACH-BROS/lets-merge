package com.peachbros.letsmerge.mission.service.dto;

import com.peachbros.letsmerge.mission.model.domain.Mission;

import java.time.LocalDateTime;

public class MissionResponse {
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    private MissionResponse() {
    }

    private MissionResponse(String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
    }

    public static MissionResponse of(Mission mission) {
        return new MissionResponse(mission.getName(), mission.getStartDateTime(), mission.getDueDateTime());
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
}
