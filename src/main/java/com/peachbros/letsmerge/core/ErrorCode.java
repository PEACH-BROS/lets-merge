package com.peachbros.letsmerge.core;

/**
 * 에러코드
 * 1000        범용코드
 * 2000        Mission
 * 3000        User
 * 4000        Matcher
 */
public enum ErrorCode {
    UNEXPECTED_EXCEPTION(1000, "예기치 않은 에러가 발생했습니다."),
    VALIDATION_EXCEPTION(1100, "올바른 형식이 아닙니다."),
    MISSION_EXCEPTION(2000, "미션 익셉션"),
    USER_EXCEPTION(3000, "유저 익셉션"),
    MATCHER_EXCEPTION(4000, "매쳐 익셉션");

    private final Integer value;
    private final String message;

    ErrorCode(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}