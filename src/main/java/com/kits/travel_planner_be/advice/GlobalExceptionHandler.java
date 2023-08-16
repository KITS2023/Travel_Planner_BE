package com.kits.travel_planner_be.advice;

import com.kits.travel_planner_be.exception.BadRequestException;
import com.kits.travel_planner_be.exception.ResourceNotFoundException;
import com.kits.travel_planner_be.exception.UnauthenticatedException;
import com.kits.travel_planner_be.payload.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ResponseError<Map<String, String>> errorResponse = new ResponseError<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errorResponse.setError(errors);
        errorResponse.setMessage("Validation failed.");

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseError<Map<String, String>> handleUnauthenticatedException(
            UnauthenticatedException ex) {
        ResponseError<Map<String, String>> errorResponse = new ResponseError<>();
        Map<String, String> errors = new HashMap<>();

        errors.put("error_message", ex.getMessage());

        errorResponse.setError(errors);
        errorResponse.setMessage("Unauthenticated.");

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseError<Map<String, String>> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        ResponseError<Map<String, String>> errorResponse = new ResponseError<>();
        Map<String, String> errors = new HashMap<>();

        errors.put("error_message", ex.getMessage());

        errorResponse.setError(errors);
        errorResponse.setMessage("Can not find the resource.");

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseError<Map<String, String>> handleBadRequestException(
            BadRequestException ex) {
        ResponseError<Map<String, String>> errorResponse = new ResponseError<>();
        Map<String, String> errors = new HashMap<>();

        errors.put("error_message", ex.getMessage());

        errorResponse.setError(errors);
        errorResponse.setMessage("Bad request.");

        return errorResponse;
    }

}
