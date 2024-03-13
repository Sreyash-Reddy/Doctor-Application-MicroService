package com.doctorappointmentapp.doctorapplicationmicroservice.client.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.ClientService;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientBookAppointmentDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientLoginDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientRegistrationDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.*;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ClientAppointmentController {
    @Autowired
    private ClientService clientService;

    @GetMapping("client/available-doctors")
    public List<Doctor> getAvailableDoctors(){
        return this.clientService.getAvailableDoctors();
    }
    @GetMapping ("client/available-doctors/doctor-name={doctorName}")
    public List<Doctor> getAvailableDoctorsByName(@PathVariable String doctorName) throws ClientDoctorSearchingException{
        return this.clientService.getAvailableDoctorsByName(doctorName);
    }

    @GetMapping("client/available-doctors/specialization={specialization}")
    public List<Doctor> getAllAvailableDoctorsBySpecialization(@PathVariable String specialization) throws ClientDoctorSearchingException {
        return this.clientService.getAllAvailableDoctorsBySpecialization(specialization);
    }

    @GetMapping("client/available-doctors/sort-by={attribute}")
    public List<Doctor> getAllAvailableDoctorsSortedBy(@PathVariable String attribute) throws ClientDoctorSearchingException {
        return this.clientService.getAllAvailableDoctorsSortedBy(attribute);
    }

    @PostMapping("client/new-appointment")
    public Appointment bookAppointmentInClientApplication(@Valid @RequestBody ClientBookAppointmentDTO clientBookAppointmentDTO) throws ClientAppointmentBookingException {
        Appointment appointment = Appointment.builder()
                .appointmentDescription(clientBookAppointmentDTO.getAppointmentDescription())
                .paymentStatus(false)
                .doctorConfirmationStatus(false)
                .appointmentDate(clientBookAppointmentDTO.getAppointmentDate())
                .appointmentSlot(clientBookAppointmentDTO.getAppointmentSlot())
                .clientID(clientBookAppointmentDTO.getClientID())
                .doctorID(clientBookAppointmentDTO.getDoctorID())
                .build();
        return this.clientService.bookAppointmentInClientApplication(appointment , clientBookAppointmentDTO.getBookingDate());
    }
    @PatchMapping("client/appointment/payment/appointmentID={appointmentID}")
    public Appointment makePaymentForAppointment(@PathVariable  Integer appointmentID ) throws ClientAppointmentPaymentException{
        return this.clientService.makePaymentForAppointment(appointmentID);
    }

    @GetMapping("client/all-appointments/clientID={clientID}")
    public List<Appointment> getAllAppointments(@PathVariable Integer clientID) throws ClientAppointmentsFetchingException {
        return this.clientService.getAllAppointments(clientID);
    }

    @GetMapping("client/previous-appointments/clientID={clientID}-year={year}-month={month}-day={day}")
    public List<Appointment> getAllPreviousAppointments(@PathVariable Integer clientID, @PathVariable Integer year, @PathVariable Integer month , @PathVariable Integer day) throws ClientAppointmentsFetchingException {
        return this.clientService.getAllPreviousAppointments(clientID,LocalDate.of(year,month,day));
    }

    @GetMapping("client/future-appointments/clientID={clientID}-year={year}-month={month}-day={day}")
    public List<Appointment> getAllFutureAppointments(@PathVariable Integer clientID, @PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) throws ClientAppointmentsFetchingException {
        return this.clientService.getAllFutureAppointments(clientID,LocalDate.of(year,month,day));
    }

}
