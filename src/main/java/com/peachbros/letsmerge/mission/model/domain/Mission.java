package com.peachbros.letsmerge.mission.model.domain;

import java.time.LocalDateTime;

public class Mission {
    private final String name;
    private final LocalDateTime startDateTime;
    private final LocalDateTime dueDateTime;

    public Mission(String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
    }

    public Mission(String name, LocalDateTime dueDateTime) {
        this(name, LocalDateTime.now(), dueDateTime);
    }

    public boolean isActive(LocalDateTime now) {
        return startDateTime.isBefore(now) && dueDateTime.isAfter(now);
    }

    public boolean isNotActive(LocalDateTime now) {
        return !isActive(now);
    }
}