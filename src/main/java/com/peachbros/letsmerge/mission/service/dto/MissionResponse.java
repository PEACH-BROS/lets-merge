package com.peachbros.letsmerge.mission.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class MissionResponse {
    private Long id;
    private String name;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "Asia/Seoul")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDateTime;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "Asia/Seoul")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
