package com.peachbros.letsmerge.mission.service.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MissionUpdateRequest {
    @NotNull(message = "수정할 미션이 없습니다.")
    private Long id;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    private MissionUpdateRequest() {
    }

    public MissionUpdateRequest(@NotNull(message = "수정할 미션이 없습니다.") Long id, String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        this.id = id;
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
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
