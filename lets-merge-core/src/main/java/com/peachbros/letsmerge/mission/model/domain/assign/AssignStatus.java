package com.peachbros.letsmerge.mission.model.domain.assign;

public enum AssignStatus {
    ASSIGN("신청완료"),
    CANCEL("신청취소");

    private final String message;

    AssignStatus(String message) {
        this.message = message;
    }
}
