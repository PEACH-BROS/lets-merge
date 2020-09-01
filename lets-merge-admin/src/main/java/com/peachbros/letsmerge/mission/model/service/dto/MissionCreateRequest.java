package com.peachbros.letsmerge.mission.model.service.dto;

import com.peachbros.letsmerge.mission.model.domain.Mission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MissionCreateRequest {
    @NotBlank(message = "미션 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "미션 시작일을 입력해주세요.")
    private String startDateTime;

    @NotNull(message = "미션 종료일을 입력해주세요.")
    private String dueDateTime;

    private MissionCreateRequest() {
    }

    public MissionCreateRequest(String name, String startDateTime, String dueDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
    }

    public Mission toMission() {
        LocalDateTime start = LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime dueDate = LocalDateTime.parse(dueDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new Mission(name, start, dueDate);
    }

    public String getName() {
        return name;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getDueDateTime() {
        return dueDateTime;
    }
}
