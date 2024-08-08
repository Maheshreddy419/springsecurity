package com.spring.security.advice;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class APIError {

    private HttpStatus code;
    private String errorMessage;
    private LocalDateTime timestamp;

    public APIError(){
        this.timestamp = LocalDateTime.now();
    }
    public APIError(HttpStatus code, String errorMessage) {
        this();
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
