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



}
