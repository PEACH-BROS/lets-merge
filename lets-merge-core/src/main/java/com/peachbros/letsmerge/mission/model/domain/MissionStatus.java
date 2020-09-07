package com.peachbros.letsmerge.mission.model.domain;

import com.peachbros.letsmerge.common.exception.NoSuchValueException;

import java.util.Arrays;

public enum MissionStatus {
    ASSIGNED("신청완료", IsOpened.TRUE, IsAssigned.TRUE),         //dueDateTime 지나지 않음, assignInfo 존재(ASSIGNED)
    ASSIGNABLE("신청가능", IsOpened.TRUE, IsAssigned.FALSE),      //dueDateTime 지나지 않음, assignInfo 미존재 or CANCELED
    //    NO_VACANCY("정원초과"),                                         //dueDateTime 지나지 않음, mission.maxUserCount 초과
    SHOW_RESULT("결과보기", IsOpened.FALSE, IsAssigned.TRUE),     //dueDateTime 지남, assignInfo 존재(ASSIGNED)
    CLOSED("모집마감", IsOpened.FALSE, IsAssigned.FALSE);         //dueDateTime 지남, assignInfo 미존재 or CANCELED

    private final String message;
    private final IsOpened isOpened;
    private final IsAssigned isAssigned;

    MissionStatus(String message, IsOpened isOpened, IsAssigned isAssigned) {
        this.message = message;
        this.isOpened = isOpened;
        this.isAssigned = isAssigned;
    }

    public static MissionStatus of(boolean isOpened, boolean isAssigned) {
        return Arrays.stream(values())
                .filter(status -> status.isOpened.value == isOpened)
                .filter(status -> status.isAssigned.value == isAssigned)
                .findFirst()
                .orElseThrow(() -> new NoSuchValueException("존재하지 않는 정보입니다."));
    }

    public String getMessage() {
        return message;
    }

    private enum IsOpened {
        TRUE(true),
        FALSE(false);

        private final boolean value;

        IsOpened(boolean value) {
            this.value = value;
        }
    }

    private enum IsAssigned {
        TRUE(true),
        FALSE(false),
        ;

        private final boolean value;

        IsAssigned(boolean value) {
            this.value = value;
        }
    }
}
