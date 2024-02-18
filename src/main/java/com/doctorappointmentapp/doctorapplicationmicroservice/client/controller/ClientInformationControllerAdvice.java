package com.doctorappointmentapp.doctorapplicationmicroservice.client.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientUpdationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientInformationControllerAdvice {

    @ExceptionHandler(value={ClientRegistrationException.class})
    public ResponseEntity<String> clientRegistrationExceptionHandler(ClientRegistrationException clientRegistrationException){
        return new ResponseEntity<>(clientRegistrationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={ClientLoginException.class})
    public ResponseEntity<String> clientLoginExceptionHandler(ClientLoginException clientLoginException){
        return new ResponseEntity<>(clientLoginException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value={ClientUpdationException.class})
    public ResponseEntity<String> clientUpdationExceptionHandler(ClientUpdationException clientUpdationException){
        return new ResponseEntity<>(clientUpdationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={ClientDeletionException.class})
    public ResponseEntity<String> clientDeletionExceptionHandler(ClientDeletionException clientDeletionException){
        return new ResponseEntity<>(clientDeletionException.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
