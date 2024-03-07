package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.controllers;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorAppointmentConfirmationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorAppointmentsFetchingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
