package com.peachbros.letsmerge.mission.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Mission {
    private final String name;
    private final LocalDateTime startDateTime;
    private final LocalDateTime dueDateTime;

    public Mission(String name, String dueDateTime) {
        this.name = name;
        this.startDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.dueDateTime = LocalDateTime.parse(dueDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public Mission(String name, String startDateTime, String dueDateTime) {
        this.name = name;
        this.startDateTime = LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.dueDateTime = LocalDateTime.parse(dueDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public boolean isActive(LocalDateTime now) {
        return startDateTime.isBefore(now) && dueDateTime.isAfter(now);
    }

    public boolean isNotActive(LocalDateTime now) {
        return !isActive(now);
    }
}