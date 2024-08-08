package com.spring.security.advice;

import com.spring.security.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> handleResourceNotFoundException(ResourceNotFoundException e){
        APIError apiError = new APIError(HttpStatus.NOT_FOUND,e.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
