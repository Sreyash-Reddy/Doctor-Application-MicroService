package com.doctorappointmentapp.doctorapplicationmicroservice.client.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientUpdationException;
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
