package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientUpdationException;

public interface ClientService {
    Client registerNewClientAccountIntoApplication(Client client) throws ClientRegistrationException;

    Client loginClientAccountIntoApplication(String email, String password) throws ClientLoginException;

    Client updateClientAccountIntoApplication(Client build) throws ClientUpdationException;

    Client deleteClientAccountFromApplication(String email, String password) throws ClientDeletionException;

    void deleteAllClients();


}
