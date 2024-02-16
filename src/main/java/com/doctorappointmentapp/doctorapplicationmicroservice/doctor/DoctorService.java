package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;

public interface DoctorService {
    Doctor registerNewDoctorAccountIntoApplication(Doctor doctor) throws DoctorRegistrationException;

    Doctor loginDoctorAccountIntoApplication(String email, String password) throws DoctorLoginException;
}
