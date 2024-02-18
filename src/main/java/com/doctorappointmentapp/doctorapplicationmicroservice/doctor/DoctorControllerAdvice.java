package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorUpdationException;
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



}
