package com.doctorappointmentapp.doctorapplicationmicroservice.admin.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.AdminLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.ClientDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.DoctorDeactivationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdminControllerAdvice {
    @ExceptionHandler(value = {DoctorDeactivationException.class})
    public ResponseEntity<String> doctorDeactivationExceptionHandler(DoctorDeactivationException doctorDeactivationException) {
        return new ResponseEntity<>(doctorDeactivationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ClientDeactivationException.class})
    public ResponseEntity<String> clientDeactivationExceptionHandler(ClientDeactivationException clientDeactivationException) {
        return new ResponseEntity<>(clientDeactivationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AdminLoginException.class})
    public ResponseEntity<String> adminLoginExceptionHandler(AdminLoginException adminLoginException){
        return new ResponseEntity<>(adminLoginException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}