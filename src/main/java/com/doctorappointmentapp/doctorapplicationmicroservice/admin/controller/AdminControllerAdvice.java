package com.doctorappointmentapp.doctorapplicationmicroservice.admin.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.ClientDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.DoctorDeactivationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminControllerAdvice {
    @ExceptionHandler(value = {DoctorDeactivationException.class})
    public ResponseEntity<String> doctorAppointmentsFetchingExceptionHandler(DoctorDeactivationException doctorDeactivationException) {
        return new ResponseEntity<>(doctorDeactivationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ClientDeactivationException.class})
    public ResponseEntity<String> doctorAppointmentsFetchingExceptionHandler(ClientDeactivationException clientDeactivationException) {
        return new ResponseEntity<>(clientDeactivationException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
