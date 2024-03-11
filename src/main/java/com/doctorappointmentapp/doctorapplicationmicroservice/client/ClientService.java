package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientBookAppointmentDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.*;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {
    Client registerNewClientAccountIntoApplication(Client client) throws ClientRegistrationException;

    Client loginClientAccountIntoApplication(String email, String password) throws ClientLoginException;

    Client updateClientAccountIntoApplication(Client build) throws ClientUpdationException;

    Client deleteClientAccountFromApplication(String email, String password) throws ClientDeletionException;

    void deleteAllClients();
    List<Doctor> getAvailableDoctors();
    List<Doctor> getAvailableDoctorsByName(String doctorName) throws ClientDoctorSearchingException;
    List<Doctor> getAllAvailableDoctorsBySpecialization(String specialization) throws ClientDoctorSearchingException;
    List<Doctor> getAllAvailableDoctorsSortedBy(String attribute) throws ClientDoctorSearchingException;
    Appointment bookAppointmentInClientApplication(Appointment appointment , LocalDate bookingDate) throws ClientAppointmentBookingException;
    Appointment makePaymentForAppointment(Integer appointmentID) throws ClientAppointmentPaymentException;
    List<Appointment> getAllAppointments(Integer clientID) throws ClientAppointmentsFetchingException;
    List<Appointment> getAllPreviousAppointments(Integer clientID, LocalDate currentDate) throws ClientAppointmentsFetchingException;
    List<Appointment> getAllFutureAppointments(Integer clientID, LocalDate currentDate) throws ClientAppointmentsFetchingException;

    void deleteAllAppointments();

}
