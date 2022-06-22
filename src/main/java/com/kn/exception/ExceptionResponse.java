package com.kn.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {

    private String code;
    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
