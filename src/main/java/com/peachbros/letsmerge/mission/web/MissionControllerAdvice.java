package com.peachbros.letsmerge.mission.web;

import com.peachbros.letsmerge.core.dto.StandardResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class MissionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public StandardResponse<Void> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(System.lineSeparator()));
        return StandardResponse.error(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }
}
