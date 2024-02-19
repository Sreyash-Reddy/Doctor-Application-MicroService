package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.*;

import java.util.List;

public interface DoctorService {
    Doctor registerNewDoctorAccountIntoApplication(Doctor doctor) throws DoctorRegistrationException;

    Doctor loginDoctorAccountIntoApplication(String email, String password) throws DoctorLoginException;

    Doctor updateDoctorAccountIntoApplication(Doctor doctorUpdatedDoctorInformation) throws DoctorUpdationException;

    Doctor deleteDoctorAccountFromApplication(String email, String password) throws DoctorDeletionException;

    void deleteAllDoctors();

    List<Appointment> getAllAppointments(Integer doctorID) throws DoctorAppointmentsFetchingException;
}
