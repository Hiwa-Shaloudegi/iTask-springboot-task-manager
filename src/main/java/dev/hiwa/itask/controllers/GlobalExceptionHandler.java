package dev.hiwa.itask.controllers;

import dev.hiwa.itask.domain.dto.ErrorDto;
import dev.hiwa.itask.domain.dto.ValidationErrorDto;
import dev.hiwa.itask.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
}
