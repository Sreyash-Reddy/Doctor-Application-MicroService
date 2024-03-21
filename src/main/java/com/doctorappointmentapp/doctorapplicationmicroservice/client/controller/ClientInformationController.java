package com.doctorappointmentapp.doctorapplicationmicroservice.client.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.ClientService;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientLoginDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientRegistrationDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
@RestController
public class ClientInformationController {
    @Autowired
    private ClientService clientService;



    @GetMapping("client/{id}")
    public Client getClientById(@RequestBody Integer clientId) throws ClientFetchingException{
        return this.clientService.getClientById(clientId);
    }

    @PostMapping("sign-up/client")
    public Client registerNewClientAccountIntoApplication(@Valid @RequestBody ClientRegistrationDTO clientRegistrationDetails) throws ClientRegistrationException {
        return this.clientService.registerNewClientAccountIntoApplication(Client.builder().name(clientRegistrationDetails.getName())
                .email(clientRegistrationDetails.getEmail())
                .password(clientRegistrationDetails.getPassword())
                .dateOfBirth(clientRegistrationDetails.getDateOfBirth())
                .mobileNumber(clientRegistrationDetails.getMobileNumber())
                .build());
    }

    @PostMapping("login/client")
    public Client loginClientAccountIntoApplication(@Valid @RequestBody ClientLoginDTO clientLoginInformation) throws ClientLoginException {
        return this.clientService.loginClientAccountIntoApplication(clientLoginInformation.getEmail(),clientLoginInformation.getPassword());
    }

    @PutMapping("update-account/client")
    public Client updateClientAccountIntoApplication(@Valid @RequestBody ClientRegistrationDTO clientUpdatedInformation) throws ClientUpdationException{
        return this.clientService.updateClientAccountIntoApplication(Client.builder().name(clientUpdatedInformation.getName())
                .email(clientUpdatedInformation.getEmail())
                .password(clientUpdatedInformation.getPassword())
                .dateOfBirth(clientUpdatedInformation.getDateOfBirth())
                .mobileNumber(clientUpdatedInformation.getMobileNumber())
                .build());
    }

    @DeleteMapping("delete-account/client")
    public Client deleteClientAccountFromApplication(@Valid @RequestBody ClientLoginDTO clientVerificationInformation) throws ClientDeletionException{
        return this.clientService.deleteClientAccountFromApplication(clientVerificationInformation.getEmail(),clientVerificationInformation.getPassword());
    }

}
