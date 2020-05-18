package com.peachbros.letsmerge.mission.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Mission {
    public static final DateTimeFormatter MISSION_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final String name;
    private final LocalDateTime startDateTime;
    private final LocalDateTime dueDateTime;

    public Mission(String name, String dueDateTime) {
        this(name, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString(), dueDateTime);
    }

    public Mission(String name, String startDateTime, String dueDateTime) {
        this.name = name;
        this.startDateTime = LocalDateTime.parse(startDateTime, MISSION_DATE_FORMAT);
        this.dueDateTime = LocalDateTime.parse(dueDateTime, MISSION_DATE_FORMAT);
    }

    public boolean isActive(LocalDateTime now) {
        return startDateTime.isBefore(now) && dueDateTime.isAfter(now);
    }

    public boolean isNotActive(LocalDateTime now) {
        return !isActive(now);
    }
}