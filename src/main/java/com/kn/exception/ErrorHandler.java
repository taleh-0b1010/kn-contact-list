package com.kn.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error("Unexpected exception ", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse responseBody = ExceptionResponse.builder()
                .code("unexpected_internal_error")
                .message("Unexpected internal error").build();
        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException {}", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String field = ex.getBindingResult().getFieldErrors().get(0).getField();
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ExceptionResponse responseBody = ExceptionResponse.builder()
                .code("parameter_invalid")
                .message(field + ". " + message).build();
        return ResponseEntity.status(status).body(responseBody);
    }
}
