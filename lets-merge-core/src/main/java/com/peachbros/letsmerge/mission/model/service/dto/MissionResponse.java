package com.peachbros.letsmerge.mission.model.service.dto;

import com.peachbros.letsmerge.mission.model.domain.Mission;

import java.time.LocalDateTime;

public class MissionResponse {
    private Long id;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    private MissionResponse() {
    }

    private MissionResponse(Long id, String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        this.id = id;
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
    }

    public static MissionResponse of(Mission mission) {
        return new MissionResponse(mission.getId(), mission.getName(), mission.getStartDateTime(), mission.getDueDateTime());
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
}
