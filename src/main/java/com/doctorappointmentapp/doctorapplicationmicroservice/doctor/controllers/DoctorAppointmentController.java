package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.controllers;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorAppointmentsFetchingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorAppointmentController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("doctor/get-all-appointments/doctorID={doctorID}")
    public List<Appointment> getAllAppointments(@PathVariable Integer doctorID) throws DoctorAppointmentsFetchingException {
        return this.doctorService.getAllAppointments(doctorID);
    }
}
