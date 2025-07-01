package dev.hiwa.itask.controllers;

import dev.hiwa.itask.domain.dto.ValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        ex
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));


        ValidationErrorDto errorResponseRecord = ValidationErrorDto
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Invalid input data")
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(errorResponseRecord);
    }
}
