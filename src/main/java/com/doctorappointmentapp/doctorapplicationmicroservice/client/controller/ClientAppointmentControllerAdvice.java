package com.doctorappointmentapp.doctorapplicationmicroservice.client.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientAppointmentBookingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientAppointmentControllerAdvice {

    @ExceptionHandler(value = {ClientAppointmentBookingException.class})
    public ResponseEntity<String> clientAppointmentBookingExceptionHandler(ClientAppointmentBookingException clientAppointmentBookingException){
        return new ResponseEntity<>(clientAppointmentBookingException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
