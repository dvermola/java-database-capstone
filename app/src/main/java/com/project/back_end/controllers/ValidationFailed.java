package com.project.back_end.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
/**
 * Global exception handler for validation errors in request bodies.
 * This class intercepts validation exceptions and formats error responses.
 */
public class ValidationFailed {

    /**
     * Handles MethodArgumentNotValidException, which is thrown when validation on an argument annotated with @Valid fails.
     *
     * @param ex the exception containing validation errors
     * @return a ResponseEntity with a map of error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Iterate through all the validation errors
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {

            // Extracts and collects default error messages (e.g., "Email is required", "Invalid phone number").
            String errorMessage = error.getDefaultMessage();

            // Constructs a response map containing the error message under the `"message"` key.
            errors.put("message", "" + errorMessage);
        }

        // Return a ResponseEntity with the error messages and HTTP status 400 Bad Request
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
