package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.controllers;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorAppointmentConfirmationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorAppointmentsFetchingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DoctorAppointmentController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("doctor/all-appointments/doctorID={doctorID}")
    public List<Appointment> getAllAppointments(@PathVariable Integer doctorID) throws DoctorAppointmentsFetchingException {
        return this.doctorService.getAllAppointments(doctorID);
    }
    @GetMapping("doctor/previous-appointments/doctorID={doctorID}-year={year}-month={month}-day={day}")
    public List<Appointment>getAllPreviousAppointments(@PathVariable Integer doctorID,@PathVariable Integer year,@PathVariable Integer month,@PathVariable Integer day) throws DoctorAppointmentsFetchingException{
        return this.doctorService.getAllPreviousAppointments(doctorID, LocalDate.of(year,month,day));}

    @GetMapping("doctor/future-appointments/doctorID={doctorID}-year={year}-month={month}-day={day}")
    public List<Appointment>getAllFutureAppointments(@PathVariable Integer doctorID,@PathVariable Integer year,@PathVariable Integer month,@PathVariable Integer day) throws DoctorAppointmentsFetchingException{
        return this.doctorService.getAllFutureAppointments(doctorID, LocalDate.of(year,month,day));}

    @PatchMapping("doctor/appointment/confirmation/appointmentID={appointmentID}")
    public Appointment confirmAppointment(@PathVariable Integer appointmentID) throws DoctorAppointmentConfirmationException {
        return this.doctorService.confirmAppointment(appointmentID);
    }

}
