package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientLoginDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientRegistrationDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientUpdationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("register/client")
    public Client registerNewClientAccountIntoApplication(@RequestBody ClientRegistrationDTO clientRegistrationDetails) throws ClientRegistrationException {
        return this.clientService.registerNewClientAccountIntoApplication(Client.builder().name(clientRegistrationDetails.getName())
                .email(clientRegistrationDetails.getEmail())
                .password(clientRegistrationDetails.getPassword())
                .dateOfBirth(clientRegistrationDetails.getDateOfBirth())
                .mobileNumber(clientRegistrationDetails.getMobileNumber())
                .build());
    }

    @PostMapping("login/client")
    public Client loginClientAccountIntoApplication(@RequestBody ClientLoginDTO clientLoginInformation) throws ClientLoginException {
        return this.clientService.loginClientAccountIntoApplication(clientLoginInformation.getEmail(),clientLoginInformation.getPassword());
    }

    @PutMapping("update/client")
    public Client updateClientAccountIntoApplication(@RequestBody ClientRegistrationDTO clientUpdatedInformation) throws ClientUpdationException{
        return this.clientService.updateClientAccountIntoApplication(Client.builder().name(clientUpdatedInformation.getName())
                .email(clientUpdatedInformation.getEmail())
                .password(clientUpdatedInformation.getPassword())
                .dateOfBirth(clientUpdatedInformation.getDateOfBirth())
                .mobileNumber(clientUpdatedInformation.getMobileNumber())
                .build());
    }

    @DeleteMapping("delete/client")
    public Client deleteClientAccountFromApplication(@RequestBody ClientLoginDTO clientVerificationInformation) throws ClientDeletionException{
        return this.clientService.deleteClientAccountFromApplication(clientVerificationInformation.getEmail(),clientVerificationInformation.getPassword());
    }
}
