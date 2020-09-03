package com.peachbros.letsmerge.mission.model.domain;

public enum MissionStatus {
    ASSIGNED("신청완료"),
    ASSIGNABLE("신청가능"),
    NO_VACANCY("정원초과"),
    CLOSED("모집마감");

    private final String message;

    MissionStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
