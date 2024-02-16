package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

public interface DoctorService {
    Doctor registerNewDoctorAccountIntoApplication(Doctor doctor) throws DoctorRegistrationException;
}
