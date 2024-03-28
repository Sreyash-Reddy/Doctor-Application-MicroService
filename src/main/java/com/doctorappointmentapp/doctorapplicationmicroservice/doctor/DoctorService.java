package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.*;

import java.time.LocalDate;
import java.util.List;

public interface DoctorService {


    Doctor getDoctorById(Integer doctorId) throws DoctorFetchingException;
    Doctor registerNewDoctorAccountIntoApplication(Doctor doctor) throws DoctorRegistrationException;

    Doctor loginDoctorAccountIntoApplication(String email, String password) throws DoctorLoginException;

    Doctor updateDoctorAccountIntoApplication(Doctor doctorUpdatedDoctorInformation) throws DoctorUpdationException;

    Doctor deleteDoctorAccountFromApplication(String email, String password) throws DoctorDeletionException;

    void deleteAllDoctors();

    Appointment confirmAppointment(Integer appointmentID) throws DoctorAppointmentConfirmationException;

    List<Appointment> getAllAppointments(Integer doctorID) throws DoctorAppointmentsFetchingException;

    List<Appointment> getAllPreviousAppointments(Integer doctorID, LocalDate currentDate) throws DoctorAppointmentsFetchingException;

    List <Appointment> getAllFutureAppointments(Integer id, LocalDate of) throws DoctorAppointmentsFetchingException;



}
