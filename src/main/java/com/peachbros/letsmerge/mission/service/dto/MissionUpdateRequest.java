package com.peachbros.letsmerge.mission.service.dto;

import java.time.LocalDateTime;

public class MissionUpdateRequest {
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    private MissionUpdateRequest() {
    }

    public MissionUpdateRequest(String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
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
