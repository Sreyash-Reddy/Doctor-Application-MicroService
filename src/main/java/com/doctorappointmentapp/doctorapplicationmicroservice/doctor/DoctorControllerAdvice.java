package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DoctorControllerAdvice {

    @ExceptionHandler(value = {DoctorRegistrationException.class})
    public ResponseEntity<String> doctorRegistrationExceptionHandler(DoctorRegistrationException doctorRegistrationException){
        return new ResponseEntity<>(doctorRegistrationException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
