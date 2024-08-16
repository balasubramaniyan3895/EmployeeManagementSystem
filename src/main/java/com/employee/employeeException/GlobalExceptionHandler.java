package com.employee.employeeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Object> IdException(IdNotFoundException ie){
        return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<Object> NameException(NameNotFoundException ne){
        return new ResponseEntity<>(ne.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<Object> AgeException(InvalidAgeException ae){
        return new ResponseEntity<>(ae.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(GenderException.class)
    public ResponseEntity<Object> GenderException(GenderException ge){
        return new ResponseEntity<>(ge.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Object> DepartmentException(DepartmentNotFoundException de){
        return new ResponseEntity<>(de.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
