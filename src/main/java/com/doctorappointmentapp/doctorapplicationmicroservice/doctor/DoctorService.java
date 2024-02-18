package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorUpdationException;

public interface DoctorService {
    Doctor registerNewDoctorAccountIntoApplication(Doctor doctor) throws DoctorRegistrationException;

    Doctor loginDoctorAccountIntoApplication(String email, String password) throws DoctorLoginException;

    Doctor updateDoctorAccountIntoApplication(Doctor doctorUpdatedDoctorInformation) throws DoctorUpdationException;

    Doctor deleteDoctorAccountFromApplication(String email, String password) throws DoctorDeletionException;

    void deleteAllDoctors();
}
