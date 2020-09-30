package com.github.kai9026.archunit.poc.exception;

import java.util.HashMap;
import java.util.Map;

import com.github.kai9026.archunit.poc.exception.model.ErrorDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidEx(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((err) ->
            errors.put(((FieldError) err).getField(), err.getDefaultMessage())
        );
        ErrorDTO error = ErrorDTO.builder()
                                    .message("Bad request. Invalid parameters")
                                    .errors(errors)
                                    .build();
        return ResponseEntity.badRequest().body(error);
    }
    
}
