package com.hostmdy.food.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ControllerAdvice
public class CustomExceptionResponseHandler {

    // Method to handle UsernameNotFoundException
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        // Return a response entity with a custom error message and HTTP status code
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Additional methods to handle other exceptions...
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    
    @ExceptionHandler(DatabaseRecordNotFoundException.class)
    public ResponseEntity<String> handleDatabaseRecordNotFoundException(DatabaseRecordNotFoundException ex) {
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}