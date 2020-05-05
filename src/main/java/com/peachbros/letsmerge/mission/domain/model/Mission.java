package com.peachbros.letsmerge.mission.domain.model;

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
}