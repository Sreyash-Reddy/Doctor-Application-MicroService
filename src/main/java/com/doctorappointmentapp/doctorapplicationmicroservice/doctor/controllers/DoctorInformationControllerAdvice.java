package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.controllers;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorUpdationException;
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
public class DoctorInformationControllerAdvice {

    @ExceptionHandler(value = {DoctorRegistrationException.class})
    public ResponseEntity<String> doctorRegistrationExceptionHandler(DoctorRegistrationException doctorRegistrationException){
        return new ResponseEntity<>(doctorRegistrationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DoctorLoginException.class})
    public ResponseEntity<String> doctorLoginExceptionHandler(DoctorLoginException doctorLoginException){
        return new ResponseEntity<>(doctorLoginException.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value = {DoctorUpdationException.class})
    public ResponseEntity<String> doctorUpdationExceptionHandler(DoctorUpdationException doctorUpdationException){
        return new ResponseEntity<>(doctorUpdationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DoctorDeletionException.class})
    public ResponseEntity<String> doctorDeletionExceptionHandler(DoctorDeletionException doctorDeletionException){
        return new ResponseEntity<>(doctorDeletionException.getMessage(), HttpStatus.BAD_REQUEST);
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
