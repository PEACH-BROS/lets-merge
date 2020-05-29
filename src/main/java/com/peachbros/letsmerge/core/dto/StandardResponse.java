package com.peachbros.letsmerge.core.dto;

import org.springframework.http.HttpStatus;

public class StandardResponse<T> {
    private Integer statusCode;
    private T data;
    private String message;

    private StandardResponse() {
    }

    private StandardResponse(Integer statusCode, T data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }

    public static <T> StandardResponse<T> of(Integer statusCode, T data) {
        return new StandardResponse<>(statusCode, data, null);
    }

    public static <T> StandardResponse<T> error(Integer statusCode, String message) {
        return new StandardResponse<>(statusCode, null, message);
    }

    public static <T> StandardResponse<T> empty() {
        return new StandardResponse<>(HttpStatus.NO_CONTENT.value(), null, null);
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
