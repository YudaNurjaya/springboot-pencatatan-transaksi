package com.enigma.livecodeecommerce.controller;

import com.enigma.livecodeecommerce.exception.DataEmptyException;
import com.enigma.livecodeecommerce.exception.DuplicateException;
import com.enigma.livecodeecommerce.exception.NotFoundException;
import com.enigma.livecodeecommerce.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(exception.getMessage(),"00"));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> HandleDataNotFoundException(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage(),"00"));
    }
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> HandleDuplicateException(DuplicateException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(exception.getMessage(),"400"));
    }
    @ExceptionHandler(DataEmptyException.class)
    public ResponseEntity<ErrorResponse> HandleDataEmptyException(DataEmptyException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage(), "400"));
    }
}
