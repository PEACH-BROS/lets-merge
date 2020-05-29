package com.peachbros.letsmerge.mission.service.dto;

import com.peachbros.letsmerge.mission.model.domain.Mission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MissionCreateRequest {
    @NotBlank(message = "미션 이름을 입력해주세요.")
    private String name;
    @NotNull(message = "미션 시작일을 입력해주세요.")
    private LocalDateTime startDateTime;
    @NotNull(message = "미션 종료일을 입력해주세요.")
    private LocalDateTime dueDateTime;

    private MissionCreateRequest() {
    }

    public MissionCreateRequest(String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
    }

    public Mission toMission() {
        return new Mission(name, startDateTime, dueDateTime);
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
