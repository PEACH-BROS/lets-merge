package com.peachbros.letsmerge.mission.service.dto;

import com.peachbros.letsmerge.mission.model.domain.Mission;

import java.time.LocalDateTime;

public class MissionCreateRequest {
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    private MissionCreateRequest() {
    }

    public MissionCreateRequest(String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
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

    public Mission toMission() {
        return new Mission(name, startDateTime, dueDateTime);
    }
}
