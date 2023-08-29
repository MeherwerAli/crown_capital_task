package org.crown.capital.simpletask.controller;

import org.crown.capital.simpletask.util.exceptions.CannotCreateException;
import org.crown.capital.simpletask.util.exceptions.CannotUpdateException;
import org.crown.capital.simpletask.util.exceptions.ErrorResponse;
import org.crown.capital.simpletask.util.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), Collections.singletonList("Details about the error can be added here"));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CannotCreateException.class)
    public final ResponseEntity<ErrorResponse> handleCannotCreateException(CannotCreateException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), Collections.singletonList("Failed to create the resource"));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotUpdateException.class)
    public final ResponseEntity<ErrorResponse> handleCannotUpdateException(CannotUpdateException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), Collections.singletonList("Failed to update the resource"));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
