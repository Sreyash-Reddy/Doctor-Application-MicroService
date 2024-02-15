package com.doctorappointmentapp.doctorapplicationmicroservice.service;

import com.doctorappointmentapp.doctorapplicationmicroservice.entities.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.exceptions.DoctorRegistrationException;

public interface DoctorApplicationService {

    Doctor registerNewDoctorAccountIntoApplication(Doctor doctor) throws DoctorRegistrationException;

}
