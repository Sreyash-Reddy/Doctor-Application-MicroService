package com.doctorappointmentapp.doctorapplicationmicroservice.client.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.ClientService;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientBookAppointmentDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientLoginDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.dto.ClientRegistrationDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ClientAppointmentController {
    @Autowired
    private ClientService clientService;

    @PostMapping("client/book-appointment")
    public Appointment bookAppointmentInClientApplication(@RequestBody ClientBookAppointmentDTO clientBookAppointmentDTO) throws ClientAppointmentBookingException {
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

    @GetMapping("client/get-all-appointments/clientID={clientID}")
    public List<Appointment> getAllAppointments(@PathVariable Integer clientID) throws ClientAppointmentsFetchingException {
        return this.clientService.getAllAppointments(clientID);
    }

    @GetMapping("client/get-all-previous-appointments/clientID={clientID}-year={year}-month={month}-day={day}")
    public List<Appointment> getAllPreviousAppointments(@PathVariable Integer clientID, @PathVariable Integer year, @PathVariable Integer month , @PathVariable Integer day) throws ClientAppointmentsFetchingException {
        return this.clientService.getAllPreviousAppointments(clientID,LocalDate.of(year,month,day));
    }



}
