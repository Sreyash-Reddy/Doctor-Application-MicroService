package com.doctorappointmentapp.doctorapplicationmicroservice.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.exceptions.DoctorRegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DoctorApplicationControllerAdvice {

    @ExceptionHandler(value = {DoctorRegistrationException.class})
    public ResponseEntity<String> doctorRegistrationExceptionHandlerMethod(DoctorRegistrationException doctorRegistrationException){
        return new ResponseEntity<>(doctorRegistrationException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
