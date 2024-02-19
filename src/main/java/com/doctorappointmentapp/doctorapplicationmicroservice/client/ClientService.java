package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientBookAppointmentDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.*;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {
    Client registerNewClientAccountIntoApplication(Client client) throws ClientRegistrationException;

    Client loginClientAccountIntoApplication(String email, String password) throws ClientLoginException;

    Client updateClientAccountIntoApplication(Client build) throws ClientUpdationException;

    Client deleteClientAccountFromApplication(String email, String password) throws ClientDeletionException;

    void deleteAllClients();


    Appointment bookAppointmentInClientApplication(Appointment appointment , LocalDate bookingDate) throws ClientAppointmentBookingException;

    List<Appointment> getAllAppointments(Integer clientID) throws ClientAppointmentsFetchingException;
}
