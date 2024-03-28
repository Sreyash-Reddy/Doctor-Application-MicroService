package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.controllers;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorAppointmentConfirmationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorAppointmentsFetchingException;
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
public class DoctorAppointmentControllerAdvice {

    @ExceptionHandler(value = {DoctorAppointmentsFetchingException.class})
    public ResponseEntity<String> doctorAppointmentsFetchingExceptionHandler(DoctorAppointmentsFetchingException doctorAppointmentsFetchingException) {
        return new ResponseEntity<>(doctorAppointmentsFetchingException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DoctorAppointmentConfirmationException.class})
    public ResponseEntity<String> doctorAppointmentConfirmationExceptionHandler(DoctorAppointmentConfirmationException doctorAppointmentConfirmationException) {
        return new ResponseEntity<>(doctorAppointmentConfirmationException.getMessage(), HttpStatus.BAD_REQUEST);
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
