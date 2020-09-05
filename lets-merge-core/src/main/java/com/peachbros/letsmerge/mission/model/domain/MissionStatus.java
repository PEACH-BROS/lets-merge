package com.peachbros.letsmerge.mission.model.domain;

import com.peachbros.letsmerge.common.exception.NoSuchValueException;

import java.util.Arrays;

public enum MissionStatus {
    ASSIGNED("신청완료", true, true),         //dueDateTime 지나지 않음, assignInfo 존재(ASSIGNED)
    ASSIGNABLE("신청가능", true, false),      //dueDateTime 지나지 않음, assignInfo 미존재 or CANCELED
    //    NO_VACANCY("정원초과"),                                         //dueDateTime 지나지 않음, mission.maxUserCount 초과
    SHOW_RESULT("결과보기", false, true),    //dueDateTime 지남, assignInfo 존재(ASSIGNED)
    CLOSED("모집마감", false, false);         //dueDateTime 지남, assignInfo 미존재 or CANCELED

    private final String message;
    private final boolean isOpened;
    private final boolean isAssigned;

    MissionStatus(String message, boolean isOpened, boolean isAssigned) {
        this.message = message;
        this.isOpened = isOpened;
        this.isAssigned = isAssigned;
    }

    public static MissionStatus of(boolean isOpened, boolean isAssigned) {
        return Arrays.stream(values())
                .filter(status -> status.isOpened == isOpened)
                .filter(status -> status.isAssigned == isAssigned)
                .findFirst()
                .orElseThrow(() -> new NoSuchValueException("존재하지 않는 정보입니다."));
    }

    public String getMessage() {
        return message;
    }
}
