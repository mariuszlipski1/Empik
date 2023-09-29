package com.mlipski.empik.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
        NoFoundException.class
    })
    public ResponseEntity<ErrorResponse> generateNoFoundException(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse
                .builder()
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler({
            BadRequestException.class,
            IllegalArgumentException.class,
            DataIntegrityViolationException.class
    })
    public ResponseEntity<ErrorResponse> generateBadRequestException(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse
                .builder()
                .message(ex.getMessage())
                .build());
    }
}