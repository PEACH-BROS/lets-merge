package com.peachbros.letsmerge.core.dto;

import com.peachbros.letsmerge.core.ErrorCode;

public class ErrorResponse {
    private Integer code;
    private String message;

    private ErrorResponse() {
    }

    private ErrorResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code.getValue(), code.getMessage());
    }

    public static ErrorResponse of(ErrorCode code, String errorMessage) {
        return new ErrorResponse(code.getValue(), errorMessage);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
