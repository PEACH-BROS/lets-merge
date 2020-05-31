package com.peachbros.letsmerge.core.dto;

import com.peachbros.letsmerge.core.ErrorCode;

public class StandardResponse<T> {
    private T data;
    private String message;

    private StandardResponse() {
    }

    public StandardResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public static <T> StandardResponse<T> of(Integer statusCode, T data) {
        return new StandardResponse<>(data, null);
    }

    public static StandardResponse<ErrorResponse> error(ErrorCode errorCode) {
        return new StandardResponse<>(ErrorResponse.of(errorCode), null);
    }

    public static StandardResponse<ErrorResponse> error(ErrorCode errorCode, String errorMessage) {
        return new StandardResponse<>(ErrorResponse.of(errorCode, errorMessage), null);
    }

    public static <T> StandardResponse<T> empty() {
        return new StandardResponse<>(null, null);
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
