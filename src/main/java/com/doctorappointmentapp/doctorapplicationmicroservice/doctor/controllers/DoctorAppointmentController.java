package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.controllers;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorAppointmentsFetchingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DoctorAppointmentController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("doctor/get-all-appointments/doctorID={doctorID}")
    public List<Appointment> getAllAppointments(@PathVariable Integer doctorID) throws DoctorAppointmentsFetchingException {
        return this.doctorService.getAllAppointments(doctorID);
    }
    @GetMapping("doctor/get-all-previous-appointments/doctorID={doctorID}-year={year}-month={month}-day={day}")
    public List<Appointment>getAllPreviousAppointments(@PathVariable Integer doctorID,@PathVariable Integer year,@PathVariable Integer month,@PathVariable Integer day) throws DoctorAppointmentsFetchingException{
        return this.doctorService.getAllPreviousAppointments(doctorID, LocalDate.of(year,month,day));}

    @GetMapping("doctor/get-all-future-appointments/doctorID={doctorID}-year={year}-month={month}-day={day}")
    public List<Appointment>getAllFutureAppointments(@PathVariable Integer doctorID,@PathVariable Integer year,@PathVariable Integer month,@PathVariable Integer day) throws DoctorAppointmentsFetchingException{
        return this.doctorService.getAllFutureAppointments(doctorID, LocalDate.of(year,month,day));}

}
