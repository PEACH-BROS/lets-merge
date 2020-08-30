package com.peachbros.letsmerge.core.dto;

import com.peachbros.letsmerge.core.ErrorCode;

public class StandardResponse<T> {
    private T data;

    private StandardResponse() {
    }

    public StandardResponse(T data) {
        this.data = data;
    }

    public static <T> StandardResponse<T> of(T data) {
        return new StandardResponse<>(data);
    }

    public static StandardResponse<ErrorResponse> error(ErrorCode errorCode) {
        return new StandardResponse<>(ErrorResponse.of(errorCode));
    }

    public static StandardResponse<ErrorResponse> error(ErrorCode errorCode, String errorMessage) {
        return new StandardResponse<>(ErrorResponse.of(errorCode, errorMessage));
    }

    public static StandardResponse<Void> empty() {
        return new StandardResponse<>(null);
    }

    public T getData() {
        return data;
    }
}
